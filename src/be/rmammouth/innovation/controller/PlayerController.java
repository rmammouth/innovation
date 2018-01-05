package be.rmammouth.innovation.controller;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public abstract class PlayerController
{
  protected Player player;

	public PlayerController()
	{
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}
  
  public abstract Move getNextMove(List<Move> availableMoves);  
}
