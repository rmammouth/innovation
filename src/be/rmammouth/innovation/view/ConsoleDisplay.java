package be.rmammouth.innovation.view;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class ConsoleDisplay implements GameViewer
{
  
  @Override
  public void init(GameModel model)
  {
  }
  
	@Override
	public void modelChanged(GameModel model)
	{		
	}

  @Override
  public void log(String message)
  {
    System.out.println(message);
  }

}
