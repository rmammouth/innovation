package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;

public abstract class CooperationDogma extends Dogma 
{
  public CooperationDogma(Resource resource)
  {
    super(resource);
  }

  @Override
  public final boolean activate(CardActivationState cas)
  {    
    boolean freeDraw=false;  //if true, the activating player will get a free draw action
    
    //activate dogma on other players
    Iterator<Player> itr=cas.getModel().getOtherPlayersIterator(cas.getActivatingPlayer());
    while (itr.hasNext())
    {
      Player otherPlayer=itr.next();
      if (cas.getResourceCount(cas.getActivatingPlayer(),resource) <= cas.getResourceCount(otherPlayer,resource))
      {
        Innovation.getViewer().log("Activating "+card.getName()+" cooperation dogma on "+otherPlayer.getName());
        freeDraw|=activateOnPlayer(cas, otherPlayer);
      }
    }
    
    //activate dogma on the player who played the card
    Innovation.getViewer().log("Activating "+card.getName()+" cooperation dogma on "+cas.getActivatingPlayer().getName());
    activateOnPlayer(cas, cas.getActivatingPlayer());
    
    //the free draw action
    return freeDraw;    
  }
  
  /**
   * 
   * @param cas
   * @param player
   * @return true is state has changed (which would trigger a free draw action for the activating player)
   */
  public abstract boolean activateOnPlayer(CardActivationState cas, Player player);
}
