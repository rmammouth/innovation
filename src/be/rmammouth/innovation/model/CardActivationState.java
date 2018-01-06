package be.rmammouth.innovation.model;

import java.util.*;

public class CardActivationState 
{
  protected Map<Player, ResourcesCount> counts=new HashMap<>();
  protected GameModel model;
  protected Player activatingPlayer;
  
  public CardActivationState(GameModel model, Player activatingPlayer)
  {
    this.model = model;
    this.activatingPlayer=activatingPlayer;
    for (Player player : model.getPlayers())
    {
      counts.put(player, player.getResourcesCount());
    }
  }
  
  public int getResourceCount(Player player, Resource resource)
  {
    return counts.get(player).getCount(resource);
  }

  public GameModel getModel()
  {
    return model;
  }

  public Player getActivatingPlayer()
  {
    return activatingPlayer;
  }
  
  
}
