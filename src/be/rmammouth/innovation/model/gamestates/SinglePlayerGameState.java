package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public abstract class SinglePlayerGameState extends GameState
{
	public SinglePlayerGameState(GameModel model)
	{
		super(model);
	}

	@Override
	public final Map<Player, List<Move>> getNextPlayers()
	{
		Map<Player, List<Move>> map=new HashMap<Player, List<Move>>();
		map.put(getActivePlayer(), getAvailableMoves());
		return map;
	}

	@Override
	public void movesDone(List<Move> moves)
	{
		moveDone(moves.get(0));		
	}

	public abstract Player getActivePlayer();
	
	public abstract List<Move> getAvailableMoves();
	
	public abstract void moveDone(Move move);
}
