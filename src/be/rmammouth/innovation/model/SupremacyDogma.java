package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;

public abstract class SupremacyDogma extends Dogma
{  
  public SupremacyDogma(Resource resource)
  {
    super(resource);
  }
  
  public final String getDogmaTypeLabel()
  {
    return "supremacy";
  }

  public final List<Player> getAffectedPlayers(CardActivationStatus cas)
  {
    List<Player> affectedPlayers=new ArrayList<>();
    Iterator<Player> itr=cas.getModel().getOtherPlayersIterator(cas.getActivatingPlayer());
    while (itr.hasNext())
    {
      Player otherPlayer=itr.next();
      if (cas.getResourceCount(cas.getActivatingPlayer(),resource) > cas.getResourceCount(otherPlayer,resource))
      {
        affectedPlayers.add(otherPlayer);
      }
    }
    return affectedPlayers;
  }
  
  public final boolean enablesFreeDraw()
  {
    return false;
  }
}
