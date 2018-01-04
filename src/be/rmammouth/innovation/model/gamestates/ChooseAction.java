package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class ChooseAction extends SinglePlayerGameState
{
	private Player player;
	
	public ChooseAction(GameModel model)
	{
		super(model);
		player=model.getCurrentTurn().getPlayer();
	}

	@Override
	public Player getActivePlayer()
	{
		return player;
	}

	@Override
	public List<Move> getAvailableMoves()
	{
		List<Move> moves=new ArrayList<>();
		moves.add(new DrawCard(player));
		moves.addAll(PlayCard.getAllPlayCardsForHand(player.getModel()));
		return moves;
	}

	@Override
	public void moveDone(Move move)
	{
		// TODO Auto-generated method stub

	}

}
