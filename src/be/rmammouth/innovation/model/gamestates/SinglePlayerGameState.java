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
	  PlayerInteraction interaction=getNextInteraction();
    Move chosenMove=interaction.getPlayer().getController().getNextMove(interaction.getAvailableMoves());
    chosenMove.resolve();
    moveResolved(chosenMove);
  }
	
  public abstract PlayerInteraction getNextInteraction();
  
  public abstract void moveResolved(Move move);
}
