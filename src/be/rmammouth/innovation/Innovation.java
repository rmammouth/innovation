package be.rmammouth.innovation;

import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.gamestates.*;
import be.rmammouth.innovation.view.*;

public class Innovation
{
  //private static GameViewer viewer=new ConsoleDisplay();
  private static GUIViewer viewer=new GUIViewer();
  private static GameModel model=new GameModel();

	public static void main(String[] args)
	{		
		//Player p1=new Player("Seb", new ConsoleController());
		Player p1=new Player("Seb", new GUIController(viewer.getInputPanel()));
		Player p2=new Player("CPU", new AIController());
		Player[] players=new Player[]{p1, p2};
		model.startNewGame(players);
		viewer.init(model);
		
		try
		{
      while (true)
      {
      	model.getCurrentState().nextStep();
      }
		}
		catch (GameOverException ex)
		{
		  viewer.log("Game over : "+ex.getMessage());
		}
	}
	
	public static GameViewer getViewer()
	{
	  return viewer;
	}

}
