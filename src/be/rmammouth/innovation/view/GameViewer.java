package be.rmammouth.innovation.view;

import be.rmammouth.innovation.model.*;

public abstract class GameViewer
{
  protected Player pointOfView;
  
  protected GameViewer(Player pointOfView)
  {
    this.pointOfView=pointOfView;
  }

  public void modelChanged(GameModel model)
  {
    if (pointOfView==null) updateView(model);
    else updateView(model.cloneForPlayer(pointOfView));
  }
  
  protected abstract void updateView(GameModel model);
  
  public abstract void log(String message);
  
  public final Player getPointOfView()
  {
    return pointOfView;
  }

  public final void setPointOfView(Player pointOfView)
  {
    this.pointOfView = pointOfView;
  }
}
