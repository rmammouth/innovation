package be.rmammouth.innovation;

import java.util.*;

import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;
import be.rmammouth.innovation.view.*;

public class Innovation
{

	public static void main(String[] args)
	{
		GameViewer view=new ConsoleDisplay();
		Player p1=new Player("Seb", new ConsoleController());
		Player p2=new Player("Ordi", new AIController());
		Player[] players=new Player[]{p1, p2};
		GameModel.getInstance().init(players);
		
    while (true)
    {
    	Map<Player,List<Move>> nextPlayers= GameModel.getInstance().getNextPlayers();
    	List<Move> chosenMoves=new ArrayList<>();
    	for (Player nextPlayer : nextPlayers.keySet())
    	{
    		Move nextMove=nextPlayer.getController().getNextMove(nextPlayers.get(nextPlayer));
    		chosenMoves.add(nextMove);
    	}
    	
    	for (Move chosenMove : chosenMoves)
    	{
    		chosenMove.resolve();
    		view.moveResolved(chosenMove);
    	}
    	GameModel.getInstance().movesDone(chosenMoves);
    }    
	}

}
