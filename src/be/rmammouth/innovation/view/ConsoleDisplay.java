package be.rmammouth.innovation.view;

import be.rmammouth.innovation.model.*;

public class ConsoleDisplay extends GameViewer
{
  public ConsoleDisplay()
  {
    super(null);
  }

  @Override
  public void log(String message)
  {
    System.out.println(message);
  }

  @Override
  protected void updateView(GameModel model)
  {    
  }
}
