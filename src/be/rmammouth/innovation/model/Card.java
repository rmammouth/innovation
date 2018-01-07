package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.model.moves.*;

public abstract class Card extends PeriodCard
{
  private String name;
  private Color color;
  private Map<ResourceLocation, Resource> resources=new EnumMap<>(ResourceLocation.class);
  private List<Dogma> dogmas=new ArrayList<>();
  
  protected Card(String name, Period period, Color color, Resource topLeft, Resource bottomLeft, Resource bottomCentre, Resource bottomRight)
  {
    super(period);
  	this.name=name;
  	this.color=color;
  	resources.put(ResourceLocation.TOP_LEFT, topLeft);
  	resources.put(ResourceLocation.BOTTOM_LEFT, bottomLeft);
  	resources.put(ResourceLocation.BOTTOM_CENTER, bottomCentre);
  	resources.put(ResourceLocation.BOTTOM_RIGHT, bottomRight);
  }
  
  public String getName()
	{
		return name;
	}
  
  public String getNamePrefixedWithPeriod()
  {
  	return "["+period.getLabel()+"]"+name;
  }

	public Color getColor()
  {
    return color;
  }
	
	public Resource getResource(ResourceLocation loc)
	{
	  return resources.get(loc);
	}
	
	protected void addDogma(Dogma dogma)
	{
	  dogma.card=this;
	  dogmas.add(dogma);
	}

  public void activate(GameModel model, Player activatingPlayer)
  {
    CardActivationState cas=buildActivationState(model, activatingPlayer);
    boolean freeDraw=false;
    for (Dogma dogma : dogmas)
    {
      freeDraw|=dogma.activate(cas);
    }
    
    if (freeDraw)
    {
      new DrawCard(activatingPlayer).resolve();
    }
  }
  
  protected CardActivationState buildActivationState(GameModel model, Player activatingPlayer)
  {
    return new CardActivationState(model, activatingPlayer);
  }
  
  public void addAllResourcesToCount(ResourcesCount count)
  {
    for (Resource resource : resources.values())
    {
      if (resource!=null)
      {
        count.increment(resource);
      }
    }
  }
  
  public void addSplayedResourcesToCount(ResourcesCount count, Splaying splay)
  {
    ResourceLocation[] revealedLocations=splay.getRevealedLocations();
    for (ResourceLocation location : revealedLocations)
    {
      Resource resource=resources.get(location);
      if (resource!=null)
      {
        count.increment(resource);
      }
    }
  }

  @Override
  public String toString()
  {
    return name;
  }
}
