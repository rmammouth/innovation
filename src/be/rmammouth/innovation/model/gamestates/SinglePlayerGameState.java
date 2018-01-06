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
	
	public final void nextStep()
  {
    List<Move> availableMoves=getAvailableMoves();
    Move chosenMove=getActivePlayer().getController().getNextMove(availableMoves);
    chosenMove.resolveAndLog();
    moveResolved(chosenMove);
  }
	
  public abstract Player getActivePlayer();
  
  public abstract List<Move> getAvailableMoves();
  
  public abstract void moveResolved(Move move);
}
