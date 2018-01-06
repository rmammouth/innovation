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
	
	public List<Player> getActivePlayers()
	{
	  return Arrays.asList(model.getPlayers());
	}
	  
	public List<Move> getAvailableMoves(Player player)
	{
	  return new ArrayList<>(PlayCard.getAllPlayableCardMoves(player));
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
			String playedCardName=playCard.getCard().getName();
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
