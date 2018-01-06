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
  public final void activate(CardActivationState cas)
  {
    Iterator<Player> itr=cas.getModel().getOtherPlayersIterator(cas.getActivatingPlayer());
    while (itr.hasNext())
    {
      Player otherPlayer=itr.next();
      if (cas.getResourceCount(cas.getActivatingPlayer(),resource) > cas.getResourceCount(otherPlayer,resource))
      {
        Innovation.getViewer().log("Activating supremacy dogma on "+otherPlayer.getName());
        activateOnPlayer(cas, otherPlayer);
      }
      else
      {
        Innovation.getViewer().log(otherPlayer.getName()+" has more "+resource+" and is not affected by the supremacy dogma");
      }
    }
  }
  
  public abstract void activateOnPlayer(CardActivationState cas, Player player);
}
