package be.rmammouth.innovation;

import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.controller.ai.*;
import be.rmammouth.innovation.controller.swing.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.gamestates.*;
import be.rmammouth.innovation.view.*;
import be.rmammouth.innovation.view.swing.*;

public class Innovation
{
  private static ViewManager viewMgr=new ViewManager();
  
  private static GameViewer viewer=new ConsoleDisplay();
  //private static GUIViewer viewer=new GUIViewer();
  private static GameModel model=new GameModel();

	public static void main(String[] args)
	{		
		//Player p1=new Player("Seb", new ConsoleController());
	//	Player p1=new Player("Seb", new GUIController(viewer.getInputPanel()));
	  Player p1=new Player("CPU1", new RandomAIController());
		Player p2=new Player("CPU2", new RandomAIController());
		Player[] players=new Player[]{p1, p2};
		model.startNewGame(players);
		viewMgr.registerViewer(p1, viewer);
		viewMgr.init();
		
    while (!model.isGameOver())
    {
    	model.getCurrentState().nextStep();
    }
    
    Innovation.getViewManager().log("Game over at turn "+model.getTurnNumber());
    String winners="";
    for (Player winner : model.getWinners())
    {
      if (winners.length()>0) winners+=", ";
      winners+=winner.getName();
    }
    Innovation.getViewManager().log("Winner(s) "+model.getVictoryType()+" : "+winners);
	}
	
	public static ViewManager getViewManager()
	{
	  return viewMgr;
	}

  public static GameModel getModel()
  {
    return model;
  }
}
