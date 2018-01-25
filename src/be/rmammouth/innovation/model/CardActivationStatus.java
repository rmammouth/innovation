package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.moves.*;

public class CardActivationStatus 
{
  private static boolean AUTO_RESOLVE_SINGLE_CHOICE=false;
  
  protected Map<Player, ResourcesCount> counts=new HashMap<>();
  protected Player activatingPlayer;
  protected Card card;
  protected PlayerInteraction nextInteraction;
  protected ListIterator<Dogma> itrDogmas;
  protected DogmaActivationStatus dogmaActivationStatus;
  protected boolean freeDraw;
  
  public CardActivationStatus(Player activatingPlayer, Card card)
  {
    this.activatingPlayer=activatingPlayer;
    this.card=card;
    for (Player player : activatingPlayer.getGameModel().getPlayers())
    {
      counts.put(player, player.getResourcesCount());
    }
    itrDogmas=card.getDogmas().listIterator();
    freeDraw=false;
  }
  
  private CardActivationStatus()
  {    
  }
  
  public int getResourceCount(Player player, Resource resource)
  {
    return counts.get(player).getCount(resource);
  }

  public GameModel getModel()
  {
    return activatingPlayer.getGameModel();
  }

  public Player getActivatingPlayer()
  {
    return activatingPlayer;
  }
  
  public Card getCard()
  {
    return card;
  }

  public void nextStep()
  {
    if (dogmaActivationStatus==null)
    {
      if (itrDogmas.hasNext())
      {
        dogmaActivationStatus=new DogmaActivationStatus(itrDogmas.next(),this);
        dogmaActivationStatus.startResolution(this);
      }
      else
      {
        //no dogma left, card resolution is over
        nextInteraction=null;
        activatingPlayer.getGameModel().log(activatingPlayer.getName()+" has finished resolving "+card.getLabelPrefixedWithPeriod());
        if (freeDraw)
        {
          activatingPlayer.getGameModel().log(activatingPlayer.getName()+" gets a free draw action");
          new DrawCard(activatingPlayer).resolve();
        }
        return;
      }
    }
    
    nextInteraction=dogmaActivationStatus.getNextInteraction();
    if (nextInteraction==null)
    {
      //this dogma is over, switch to the next one
      dogmaActivationStatus=null;
      nextStep();
    }
    else if (AUTO_RESOLVE_SINGLE_CHOICE && nextInteraction.getAvailableMoves().size()==1)
    {
      //if there's only one option available to the player, resolve it immediately
      Move onlyMove=nextInteraction.getAvailableMoves().get(0);
      onlyMove.resolve();
      addResolvedMove(onlyMove);
      nextStep();
    }
  }
  
  public PlayerInteraction getNextInteraction()
  {
    return nextInteraction;
  }
  
  void giveFreeDraw()
  {
    freeDraw=true;
  }
  
  public void addResolvedMove(Move move)
  {
    dogmaActivationStatus.addResolvedMove(move);
  }

  public CardActivationStatus cloneStatus(GameModel cloneModel)
  {
    CardActivationStatus clone=new CardActivationStatus();
    for (Player player : counts.keySet())
    {
      clone.counts.put(cloneModel.getPlayers()[player.getIndex()], counts.get(player).cloneCount());
    }
    clone.activatingPlayer=cloneModel.getPlayers()[activatingPlayer.getIndex()];
    clone.card=card;
    //we probably don't have to clone PlayerInteraction
    clone.itrDogmas=card.getDogmas().listIterator(itrDogmas.nextIndex());
    if (dogmaActivationStatus!=null)
    {
      clone.dogmaActivationStatus=dogmaActivationStatus.cloneStatus(cloneModel, clone);
    }
    clone.freeDraw=freeDraw;
    return clone;
  }
}
