package be.rmammouth.innovation.controller;

import java.util.*;

import be.rmammouth.innovation.model.moves.*;

public class AIController extends PlayerController
{
	private Random random=new Random();
	
	public AIController()
	{
		super();
	}

	@Override
	public Move getNextMove(List<Move> availableMoves)
	{
		return availableMoves.get(random.nextInt(availableMoves.size()));
	}
}
