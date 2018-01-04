package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.*;
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
		for (PlayerModel playerModel : model.getPlayerModels())
		{
			List<Move> moves=new ArrayList<>(PlayCard.getAllPlayCardsForHand(playerModel));
			map.put(playerModel.getPlayer(),moves) ;
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
		
		model.setCurrentTurn(firstPlayer.getModel());
		model.setCurrentState(new ChooseAction(model));
	}
}
