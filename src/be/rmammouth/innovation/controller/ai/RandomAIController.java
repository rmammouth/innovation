package be.rmammouth.innovation.controller.ai;

import java.util.*;

import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class RandomAIController extends PlayerController
{
	private Random random=new Random();
	
	public RandomAIController()
	{
		super();
	}

	@Override
	public Move getNextMove(GameModel model, List<? extends Move> availableMoves)
	{
		return availableMoves.get(random.nextInt(availableMoves.size()));
	}
}
