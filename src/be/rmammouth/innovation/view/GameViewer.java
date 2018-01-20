package be.rmammouth.innovation.view;

import be.rmammouth.innovation.model.*;

public interface GameViewer
{
  public void init(GameModel model);
  public void modelChanged(GameModel model);
  public void log(String message);
}
