package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;

public abstract class SupremacyDogma extends Dogma
{  
  public SupremacyDogma(Resource resource)
  {
    super(resource);
  }

  @Override
  public final boolean activate(CardActivationState cas)
  {
    Iterator<Player> itr=cas.getModel().getOtherPlayersIterator(cas.getActivatingPlayer());
    while (itr.hasNext())
    {
      Player otherPlayer=itr.next();
      if (cas.getResourceCount(cas.getActivatingPlayer(),resource) > cas.getResourceCount(otherPlayer,resource))
      {
        Innovation.getViewer().log("Activating "+card.getName()+" supremacy dogma on "+otherPlayer.getName());
        activateOnPlayer(cas, otherPlayer);
      }
      else
      {
        Innovation.getViewer().log(otherPlayer.getName()+" has more "+resource+" and is not affected by "+card.getName()+" supremacy dogma");
      }
    }
    
    return false;  //supremacy dogmas never trigger free draw action
  }
  
  public abstract void activateOnPlayer(CardActivationState cas, Player affectedPlayer);
}
