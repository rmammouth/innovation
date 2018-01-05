package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class PlayFirstCard extends GameState
{
	public PlayFirstCard(GameModel model)
	{
		super(model);
	}

	@Override
	public Map<Player, List<Move>> getNextPlayers()
	{
		Map<Player, List<Move>> map=new HashMap<>();
		for (Player player : model.getPlayers())
		{
			List<Move> moves=new ArrayList<>(PlayCard.getAllPlayCardsForHand(player));
			map.put(player,moves) ;
		}
		return map;
	}

	@Override
	public void movesDone(List<Move> moves)
	{
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
		
		model.setCurrentTurn(firstPlayer);
		model.setCurrentState(new ChooseAction(model));
	}
}
