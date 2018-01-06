package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

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
	
	public final void resolveAndLog()
	{
	  resolve();
	  Innovation.getViewer().moveResolved(this);
	}
	
	public abstract void resolve();
	
	public abstract String getResolvedLabel();

  @Override
  public String toString()
  {
    return getLabel();
  }
	
	
}
