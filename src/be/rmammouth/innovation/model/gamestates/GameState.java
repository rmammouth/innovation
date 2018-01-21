package be.rmammouth.innovation.model.gamestates;

import be.rmammouth.innovation.model.*;

public abstract class GameState
{
	protected GameModel model;
		
	public GameState(GameModel model)
	{
		this.model = model;
	}

	public abstract void nextStep();

  public abstract GameState cloneState(GameModel cloneModel);
}
