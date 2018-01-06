package be.rmammouth.innovation.view;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public interface GameViewer
{
  public void init(GameModel model);
  public void turnStarted(Player player);
  public void moveResolved(Move move);
  public void log(String message);
}
