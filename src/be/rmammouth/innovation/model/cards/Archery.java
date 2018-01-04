package be.rmammouth.innovation.model.cards;

import be.rmammouth.innovation.model.*;

public class Archery extends Card
{
	public Archery()
	{
		super("Archery", Period.ONE, Color.RED,
				  Resource.TOWER,
				  Resource.BULB, null, Resource.TOWER);
		
		dogmas.add(new Dogma(DogmaType.SUPREMACY, Resource.TOWER, null));

	}
  
}
