package be.rmammouth.innovation;

import be.rmammouth.innovation.controller.ai.*;
import be.rmammouth.innovation.controller.swing.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.view.*;
import be.rmammouth.innovation.view.swing.*;

public class Innovation
{
	public static void main(String[] args)
	{	
	  GameViewer.setViewer(new GUIViewer(null));
	  GameModel model=new GameModel();
	  
		Player p1=new Player("Seb", new GUIController(((GUIViewer)GameViewer.getViewer()).getInputPanel()));
//	  Player p1=new Player("RandomCPU", new RandomAIController());
//	  Player p2=new Player("SimpleEvalCPU", new SimpleEvalAIController());
		Player p2=new Player("NeuralNetCPU", new NeuralNetworkAIController());

//		((GUIViewer)GameViewer.getViewer()).setPointOfView(p1);
		Player[] players=new Player[]{p1, p2};
		model.startNewGame(players);
				
    while (!model.isGameOver())
    {
    	model.getCurrentState().nextStep();
    }
    
    GameViewer.getViewer().log("Game over at turn "+model.getTurnNumber());
    String winners="";
    for (Player winner : model.getWinners())
    {
      if (winners.length()>0) winners+=", ";
      winners+=winner.getName();
    }
    GameViewer.getViewer().log("Winner(s) "+model.getVictoryType()+" : "+winners);
	}
}
