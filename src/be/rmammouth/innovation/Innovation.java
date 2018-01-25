package be.rmammouth.innovation;

import be.rmammouth.innovation.controller.ai.*;
import be.rmammouth.innovation.controller.swing.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.view.*;
import be.rmammouth.innovation.view.swing.*;

public class Innovation
{
  private static GameViewer viewer=new ConsoleDisplay();
  //private static GUIViewer viewer=new GUIViewer(null);
  private static GameModel model=new GameModel();

	public static void main(String[] args)
	{	
//		Player p1=new Player("Seb", new GUIController(viewer.getInputPanel()));
	  Player p1=new Player("RandomCPU", new RandomAIController());
	  Player p2=new Player("SimpleEvalCPU", new SimpleEvalAIController());

	//	viewer.setPointOfView(p1);
		Player[] players=new Player[]{p1, p2};
		model.startNewGame(players);
				
    while (!model.isGameOver())
    {
    	model.getCurrentState().nextStep();
    }
    
    Innovation.getViewer().log("Game over at turn "+model.getTurnNumber());
    String winners="";
    for (Player winner : model.getWinners())
    {
      if (winners.length()>0) winners+=", ";
      winners+=winner.getName();
    }
    Innovation.getViewer().log("Winner(s) "+model.getVictoryType()+" : "+winners);
	}
	
	public static GameViewer getViewer()
	{
	  return viewer;
	}

  public static GameModel getModel()
  {
    return model;
  }
}
