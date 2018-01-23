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
	
	protected final void doNextStep()
  {
	  PlayerInteraction interaction=getNextInteraction();
	  GameModelClone pgm=new GameModelClone(interaction.getPlayer(), model, interaction.getAvailableMoves());
    Move cloneMove=interaction.getPlayer().getController().getNextMove(pgm.getModel(), pgm.getMoves());
    Move originalMove=interaction.getAvailableMoves().get(pgm.getMoves().indexOf(cloneMove));
    originalMove.resolve();
    moveResolved(originalMove);
  }
	
  public abstract PlayerInteraction getNextInteraction();
  
  public abstract void moveResolved(Move move);
}
