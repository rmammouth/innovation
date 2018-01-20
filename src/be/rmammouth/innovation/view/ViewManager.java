package be.rmammouth.innovation.view;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class ViewManager
{
  private Map<Player, GameViewer> viewers=new HashMap<>();
  
  public void registerViewer(Player player, GameViewer viewer)
  {
    viewers.put(player, viewer);
  }
  
  public void init()
  {
    for (Player player : viewers.keySet())
    {
      GameModel model=Innovation.getModel().cloneForPlayer(player);
      viewers.get(player).init(model);
    }
  }
  
  public void modelChanged()
  {
    for (Player player : viewers.keySet())
    {
      GameModel model=Innovation.getModel().cloneForPlayer(player);
      viewers.get(player).modelChanged(model);
    }
  }
  
  public void log(String message)
  {
    for (GameViewer viewer : viewers.values())
    {
      viewer.log(message);
    }
  }
}
