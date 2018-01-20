package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class ChoosingAction extends SinglePlayerGameState
{
	public ChoosingAction(GameModel model)
	{
		super(model);
	}

	@Override
	public PlayerInteraction getNextInteraction()
	{
	  Player player=model.getCurrentTurnPlayer();
	  Innovation.getViewManager().log(player.getName()+" has "+model.getCurrentTurnActionsLeft()+" action(s) left to play");
	  
		List<ActionMove> moves=new ArrayList<>();
		moves.add(new DrawCard(player));
		moves.addAll(PlayCard.getAllPlayableCardMoves(player));
		moves.addAll(ActivateCard.getAllActivableCardMoves(player));
		moves.addAll(DominatePeriod.getAllDominablePeriodMoves(player));
		
		for (ActionMove move : moves)
		{
		  move.setTurnAction(true);
		}
		
		return new PlayerInteraction(player, new ArrayList<Move>(moves));
	}

	@Override
	public void moveResolved(Move move)
	{	  
	}
}
