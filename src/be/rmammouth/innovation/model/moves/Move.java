package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.*;

public abstract class Move
{
	protected Player player;
	
	public Move(Player player)
	{
		this.player = player;
	}

	public Player getPlayer()
	{
		return player;
	}

	public abstract String getLabel();
	
	public abstract void resolve();
	
	public abstract String getResolvedLabel();
}
