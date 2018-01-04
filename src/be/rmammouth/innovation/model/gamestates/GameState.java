package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public abstract class GameState
{
	protected GameModel model;
		
	public GameState(GameModel model)
	{
		this.model = model;
	}

	public abstract Map<Player, List<Move>> getNextPlayers();

	public abstract void movesDone(List<Move> moves);

}
