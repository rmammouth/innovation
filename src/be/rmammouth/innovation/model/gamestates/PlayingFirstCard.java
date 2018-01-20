package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class PlayingFirstCard extends MultiPlayerGameState
{
	public PlayingFirstCard(GameModel model)
	{
		super(model);
	}

	public List<PlayerInteraction> getNextInteractions()
	{
	  List<PlayerInteraction> interactions=new ArrayList<>();
	  for (Player player : model.getPlayers())
	  {
	    interactions.add(new PlayerInteraction(player, new ArrayList<Move>(PlayCard.getAllPlayableCardMoves(player))));
	  }
	  return interactions;
	}

	@Override
	public void movesResolved(List<Move> moves)
	{
	  //the player who played the first card in alpha order will play the first turn
	  Player firstPlayer=null;
		String firstCardName=null;
		for (Move move : moves)
		{
			PlayCard playCard=(PlayCard)move;
			String playedCardName=playCard.getCard().getLabel();
			if ((firstCardName==null) || (playedCardName.compareToIgnoreCase(firstCardName)<0))
		  {
				firstPlayer=move.getPlayer();
				firstCardName=playedCardName;
		  }
		}
		
		model.setFirstPlayer(firstPlayer);
		model.setCurrentTurn(firstPlayer);
		model.setCurrentState(new ChoosingAction(model));
	}
}
