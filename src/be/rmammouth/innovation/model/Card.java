package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.cards.*;

/**
 * Base class for all cards.
 * Only "unrevealed" cards will be an instance of this class.
 * @author Seb
 *
 */
public class Card implements Dominable 
{
  private static Random random=new Random();
  
  /**
   * Id randomized at each game and used to match unrevealed cards between client and server
   */
  private int id;
  private String name;
  private String label;
  private Period period;
  private Color color;
  private Map<ResourceLocation, Resource> resources=new EnumMap<>(ResourceLocation.class);
  private List<Dogma> dogmas=new ArrayList<>();
  
  public Card(int id, Period period)
  {
    this.id=id;
    this.period=period;
  }  

  protected Card(String name, Period period, Color color, Resource topLeft, Resource bottomLeft, Resource bottomCentre, Resource bottomRight)
  {
    //generate a random (and unused) id
    do
    {
      id=random.nextInt();
    }
    while (Cards.get(id)!=null);
    
  	this.name=name;
  	this.color=color;
  	this.period=period;
  	resources.put(ResourceLocation.TOP_LEFT, topLeft);
  	resources.put(ResourceLocation.BOTTOM_LEFT, bottomLeft);
  	resources.put(ResourceLocation.BOTTOM_CENTER, bottomCentre);
  	resources.put(ResourceLocation.BOTTOM_RIGHT, bottomRight);
  }
  
  public int getId()
  {
    return id;
  }
  
  public String getName()
	{
		return name;
	}
  
  public boolean isRevealed()
  {
    return name!=null;
  }
  
  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getLabelPrefixedWithPeriod()
  {
  	return "["+period.asString()+"]"+label;
  }
  
  public Period getPeriod()
  {
    return period;
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

  public CardActivationStatus activate(Player activatingPlayer)
  {
    Innovation.getViewManager().log(activatingPlayer.getName()+" has activated "+getLabelPrefixedWithPeriod());
    CardActivationStatus cas=buildActivationStatus(activatingPlayer);
    cas.nextStep();
    return cas;
  }
  
  protected CardActivationStatus buildActivationStatus(Player activatingPlayer)
  {
    return new CardActivationStatus(activatingPlayer, this);
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
  
  public static Period getHighestPeriod(List<? extends Card> cards)
  {
    Period highest=null;
    for (Card card : cards)
    {
      if (highest==null) highest=card.getPeriod();
      else
      {
        if (highest.isLower(card.getPeriod()))
        {
          highest=card.getPeriod();
        }
      }
    }
    return highest;
  }
  
  public static Period getLowestPeriod(List<? extends Card> cards)
  {
    Period lowest=null;
    for (Card card : cards)
    {
      if (lowest==null) lowest=card.getPeriod();
      else
      {
        if (lowest.isHigher(card.getPeriod()))
        {
          lowest=card.getPeriod();
        }
      }
    }
    return lowest;
  }

  @Override
  public String toString()
  {
    return name;
  }
  
  public Card cloneCard()
  {
    Card clone=new Card(id, period);
    return clone;
  }

}
