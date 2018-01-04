package be.rmammouth.innovation.model;

import be.rmammouth.innovation.model.effects.*;

public class Dogma
{
  private DogmaType type;
  private Resource resource;
  private Effect effect;
  
	public Dogma(DogmaType type, Resource resource, Effect effect)
	{
		this.type = type;
		this.resource = resource;
		this.effect = effect;
	}
  
  
}
