package be.rmammouth.innovation.view;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class ConsoleDisplay implements GameViewer
{
  @Override
  public void turnStarted(Player player)
  {
    System.out.println("It's "+player.getName()+"'s turn");    
  }
  
	@Override
	public void moveResolved(Move move)
	{
		System.out.println(move.getResolvedLabel());
	}

  @Override
  public void log(String message)
  {
    System.out.println(message);
  }
}
