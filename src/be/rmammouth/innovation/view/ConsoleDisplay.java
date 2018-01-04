package be.rmammouth.innovation.view;

import be.rmammouth.innovation.model.moves.*;

public class ConsoleDisplay implements GameViewer
{
	@Override
	public void moveResolved(Move move)
	{
		System.out.println(move.getResolvedLabel());
	}

}
