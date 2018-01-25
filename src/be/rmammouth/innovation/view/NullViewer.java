package be.rmammouth.innovation.view;

import be.rmammouth.innovation.model.*;

public class NullViewer extends GameViewer
{
  public NullViewer()
  {
    super(null);
  }

  @Override
  protected void updateView(GameModel model)
  {
  }

  @Override
  public void log(String message)
  {
  }

}
