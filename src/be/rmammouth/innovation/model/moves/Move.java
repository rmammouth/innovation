package be.rmammouth.innovation.model.moves;

import java.util.*;

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
	
	public final void resolve()
	{
	  doResolve();
	  Innovation.getViewer().moveResolved(this);
	}
	
	protected abstract void doResolve();

  @Override
  public String toString()
  {
    return getLabel();
  }
	
	public static void resolveAll(List<Move> moves)
	{
	  for (Move move : moves)
	  {
	    move.resolve();
	  }
	}
}
