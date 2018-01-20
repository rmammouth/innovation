package be.rmammouth.innovation.model.gamestates;

import be.rmammouth.innovation.model.*;

public abstract class GameState
{
	protected GameModel model;
	private GameState previousState;
		
	public GameState(GameModel model)
	{
		this.model = model;
	}

	public abstract void nextStep();

  public GameState getPreviousState()
  {
    return previousState;
  }

  public void setPreviousState(GameState previousState)
  {
    this.previousState = previousState;
  }
	
	
}
