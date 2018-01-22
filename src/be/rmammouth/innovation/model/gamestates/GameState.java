package be.rmammouth.innovation.model.gamestates;

import be.rmammouth.innovation.model.*;

public abstract class GameState
{
	protected GameModel model;
		
	public GameState(GameModel model)
	{
		this.model = model;
	}
	
	public final void nextStep()
	{
	  try
	  {
	    doNextStep();
	  }
	  catch (GameOverException ex)
	  {
	    model.setGameOver(ex.getWinners(), ex.getVictoryType());
	  }
	}

	protected abstract void doNextStep();

  public abstract GameState cloneState(GameModel cloneModel);
}
