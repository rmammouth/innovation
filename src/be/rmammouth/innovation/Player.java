package be.rmammouth.innovation;

import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.model.*;

public class Player
{
  private String name;
  private PlayerModel model;
  private PlayerController controller;  
  
	public Player(String name, PlayerController controller)
	{
		super();
		this.name = name;
		this.model=new PlayerModel(this);
		this.controller = controller;
	}

	public String getName()
	{
		return name;
	}

	public PlayerModel getModel()
	{
		return model;
	}
	
	public PlayerController getController()
	{
		return controller;
	}
	
	public void setController(PlayerController controller)
	{
		this.controller = controller;
	}
}
