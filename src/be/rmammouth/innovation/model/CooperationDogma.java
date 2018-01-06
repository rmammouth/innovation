package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.moves.*;

public abstract class CooperationDogma extends Dogma 
{
  public CooperationDogma(Resource resource)
  {
    super(resource);
  }

  @Override
  public final void activate(CardActivationState cas)
  {    
    boolean stateChanged=false;  //if true, the activating player will get a free draw action
    
    //activate dogma on other players
    Iterator<Player> itr=cas.getModel().getOtherPlayersIterator(cas.getActivatingPlayer());
    while (itr.hasNext())
    {
      Player otherPlayer=itr.next();
      if (cas.getResourceCount(cas.getActivatingPlayer(),resource) <= cas.getResourceCount(otherPlayer,resource))
      {
        Innovation.getViewer().log("Activating "+card.getName()+" cooperation dogma on "+otherPlayer.getName());
        stateChanged|=activateOnPlayer(cas, otherPlayer);
      }
    }
    
    //activate dogma on the player who played the card
    Innovation.getViewer().log("Activating "+card.getName()+" cooperation dogma on "+cas.getActivatingPlayer().getName());
    stateChanged|=activateOnPlayer(cas, cas.getActivatingPlayer());
    
    //the free draw action
    if (stateChanged)
    {
      new DrawCard(cas.getActivatingPlayer()).resolve();
    }
  }
  
  /**
   * 
   * @param cas
   * @param player
   * @return true is state has changed (which would trigger a free draw action for the activating player)
   */
  public abstract boolean activateOnPlayer(CardActivationState cas, Player player);
}
