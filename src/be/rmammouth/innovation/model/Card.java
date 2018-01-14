package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.model.moves.*;

public abstract class Card extends PeriodCard
{
  private String id;
  private String name;
  private Color color;
  private Map<ResourceLocation, Resource> resources=new EnumMap<>(ResourceLocation.class);
  private List<Dogma> dogmas=new ArrayList<>();
  
  protected Card(String id, Period period, Color color, Resource topLeft, Resource bottomLeft, Resource bottomCentre, Resource bottomRight)
  {
    super(period);
  	this.id=id;
  	this.color=color;
  	resources.put(ResourceLocation.TOP_LEFT, topLeft);
  	resources.put(ResourceLocation.BOTTOM_LEFT, bottomLeft);
  	resources.put(ResourceLocation.BOTTOM_CENTER, bottomCentre);
  	resources.put(ResourceLocation.BOTTOM_RIGHT, bottomRight);
  }
  
  public String getId()
	{
		return id;
	}
  
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getNamePrefixedWithPeriod()
  {
  	return "["+period.asString()+"]"+name;
  }

	public Color getColor()
  {
    return color;
  }
	
	public Resource getResource(ResourceLocation loc)
	{
	  return resources.get(loc);
	}	

  public boolean containsResource(Resource resource) 
  {
    return resources.values().contains(resource);
  }
	
	protected void addDogma(Dogma dogma)
	{	  
	  dogma.index=dogmas.size();
	  dogma.card=this;
	  dogmas.add(dogma);	  
	}
	
	public List<Dogma> getDogmas()
	{
	  return Collections.unmodifiableList(dogmas);
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
  
  public static List<Card> getFilteredList(List<Card> cards, CardFilter filter)
  {
    List<Card> newList=new ArrayList<>();
    for (Card card : cards)
    {
      if (!filter.isFiltered(card))
      {
        newList.add(card);
      }
    }
    return cards;
  }

  @Override
  public String toString()
  {
    return id;
  }

}
