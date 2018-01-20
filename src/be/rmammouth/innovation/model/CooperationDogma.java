package be.rmammouth.innovation.model;

import java.util.*;

public abstract class CooperationDogma extends Dogma 
{
  public CooperationDogma(Resource resource)
  {
    super(resource);
  }
  
  public final String getDogmaTypeLabel()
  {
    return "cooperation";
  }
  
  public final List<Player> getAffectedPlayers(CardActivationStatus cas)
  {
    List<Player> affectedPlayers=new ArrayList<>();
    
    //activate dogma on other players
    Iterator<Player> itr=cas.getModel().getOtherPlayersIterator(cas.getActivatingPlayer());
    while (itr.hasNext())
    {
      Player otherPlayer=itr.next();
      if (cas.getResourceCount(cas.getActivatingPlayer(),resource) <= cas.getResourceCount(otherPlayer,resource))
      {
        affectedPlayers.add(otherPlayer);
      }
    }
    affectedPlayers.add(cas.getActivatingPlayer());
    
    return affectedPlayers;
  }
}
