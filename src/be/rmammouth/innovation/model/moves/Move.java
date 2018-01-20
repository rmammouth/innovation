package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.gamestates.*;

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
	  Innovation.getViewManager().modelChanged();
	}
	
	protected abstract void doResolve();	
	
	/**
	 * Returns true if this move modifies the game model (for the purpose of the free draw action of cooperation dogmas)
	 * @return
	 */
	public abstract boolean modifiesGameModel();

  @Override
  public String toString()
  {
    return getLabel();
  }
  
  public boolean isPass()
  {
    return false;
  }
	
	public static void resolveAll(List<Move> moves)
	{
	  for (Move move : moves)
	  {
	    move.resolve();
	  }
	}
}
