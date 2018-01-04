package be.rmammouth.innovation.model.cards;

import be.rmammouth.innovation.model.*;

public class Archery extends Card
{
	public Archery()
	{
		super("Archery", Period.ONE, Color.RED,
				  Resource.TOWER,
				  Resource.BULB, null, Resource.TOWER);
		
		dogmas.add(new SupremacyDogma(Resource.TOWER)
    {
      @Override
      public void activateOnPlayer(GameModel gs, CardActivationState cas, PlayerModel player) 
      {
      }		  
    });
	}  
}
