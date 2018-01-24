package be.rmammouth.innovation.view.swing;

import javax.swing.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.view.*;

public class GUIViewer extends GameViewer
{
  private GUIViewerFrame frame;
  
  public GUIViewer(Player pointOfView)
  {
    super(pointOfView);
    frame=new GUIViewerFrame();
  }

  @Override
  protected void updateView(GameModel model)
  {
    frame.updateView(model);
  }

  @Override
  public void log(String message)
  {
    frame.log(message);
  }

  public JPanel getInputPanel()
  {
    return frame.getInputPanel();
  }
}
