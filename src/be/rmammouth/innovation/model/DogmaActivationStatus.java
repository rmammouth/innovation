package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.moves.*;

public class DogmaActivationStatus
{  
  private Dogma dogma;
  private CardActivationStatus cardActivationStatus;
  private List<Player> affectedPlayers;
  private ListIterator<Player> itrPlayers;
  private Player currentlyAffectedPlayer;
  private PlayerInteraction nextInteraction;
  private int resolutionStep;
  private LinkedList<Move> resolvedMoves=new LinkedList<Move>();
  
  public DogmaActivationStatus(Dogma dogma, CardActivationStatus cardActivationStatus)
  {
    super();
    this.dogma = dogma;
    this.cardActivationStatus=cardActivationStatus;
  }

  public Dogma getDogma()
  {
    return dogma;
  }
  
  public void startResolution(CardActivationStatus cardActivationStatus)
  {
    affectedPlayers=dogma.getAffectedPlayers(cardActivationStatus);
    itrPlayers=affectedPlayers.listIterator();
    if (!itrPlayers.hasNext())
    {
      cardActivationStatus.getModel().log("Activating "+cardActivationStatus.getCard().getLabel()+" "+dogma.getDogmaTypeLabel()+" dogma "+dogma.getIndex()+" : no one gets affected");
    }
  }
  
  public final PlayerInteraction getNextInteraction()
  {
    if (currentlyAffectedPlayer==null)
    {
      if (itrPlayers.hasNext())
      {
        currentlyAffectedPlayer=itrPlayers.next();
        cardActivationStatus.getModel().log("Activating "+cardActivationStatus.getCard().getLabel()+" "+dogma.getDogmaTypeLabel()+" dogma "+dogma.getIndex()+" on "+currentlyAffectedPlayer.getName());        
      }
      else return null;
    }
    
    nextInteraction=dogma.getNextPlayerInteraction(cardActivationStatus, this);
    if (nextInteraction==null)
    {
      //we're done with this player
      //check if the activating player will get a free card draw
      if (dogma.enablesFreeDraw() && (currentlyAffectedPlayer.getIndex()!=cardActivationStatus.getActivatingPlayer().getIndex()))
      {
        for (Move move : resolvedMoves)
        {
          if (move.modifiesGameModel())
          {
            cardActivationStatus.giveFreeDraw();
            break;
          }
        }
      }
      //switch to the next player affected by this dogma 
      resolutionStep=0;
      resolvedMoves.clear();
      currentlyAffectedPlayer=null;
      return getNextInteraction();
    }
    else return nextInteraction;
  }

  public int getResolutionStep()
  {
    return resolutionStep;
  }

  public void setResolutionStep(int resolutionStep)
  {
    this.resolutionStep = resolutionStep;
  }

  public Player getAffectedPlayer()
  {
    return currentlyAffectedPlayer;
  }
  
  public void addResolvedMove(Move move)
  {
    resolvedMoves.addLast(move);
  }
  
  public Move getLastResolvedMove()
  {
    return resolvedMoves.peekLast();
  }
  
  public int getNumberOfResolvedMoves(Class clazz)
  {
    int nbr=0;
    for (Move move : resolvedMoves)
    {
      if (clazz.isAssignableFrom(move.getClass())) nbr++;
    }
    return nbr;
  }

  public DogmaActivationStatus cloneStatus(GameModel cloneModel, CardActivationStatus cloneCAS)
  {
    DogmaActivationStatus clone=new DogmaActivationStatus(dogma, cloneCAS);
    if (affectedPlayers!=null)
    {
      clone.affectedPlayers=new ArrayList<Player>();
      for (Player player : affectedPlayers)
      {
        clone.affectedPlayers.add(cloneModel.getPlayers()[player.getIndex()]);
      }  
      if (itrPlayers!=null)
      {
        clone.itrPlayers=clone.affectedPlayers.listIterator(itrPlayers.nextIndex());
      }
    }
    if (currentlyAffectedPlayer!=null)
    {
      clone.currentlyAffectedPlayer=cloneModel.getPlayers()[currentlyAffectedPlayer.getIndex()];
    }
    //don't have to clone nextInteraction as it will be generated on next step
    clone.resolutionStep=resolutionStep;
    for (Move move : resolvedMoves)
    {
      clone.resolvedMoves.add(move.cloneMove(cloneModel));
    }
    return clone;
  }
}
