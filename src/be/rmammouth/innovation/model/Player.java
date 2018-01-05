package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.controller.*;

public class Player
{
  private String name;
  private PlayerController controller; 
  private List<Achievement> achievements=new ArrayList<>();
  private List<Card> hand=new ArrayList<>();
  private Map<Color, CardsPile> zone=new EnumMap<>(Color.class);
  
  public Player(String name, PlayerController controller)
	{
		this.name=name;
		this.controller=controller;
		for (Color color : Color.values())
		{
			zone.put(color, new CardsPile());
		}
	}

	public String getName()
	{
    return name;
  }

  public PlayerController getController()
  {
    return controller;
  }

  public void addToHand(Card card)
	{
		hand.add(card);
	}
  
  public List<Card> getHand()
  {
  	return Collections.unmodifiableList(hand);
  }
  
  public Period getMaxActivePeriod()
  {
  	Period maxActivePeriod=null;
  	for (Color color : Color.values())
  	{
  		Card topCard=zone.get(color).getTopCard();
  		if (topCard!=null)
  		{
  			if ((maxActivePeriod==null) || (maxActivePeriod.compareTo(topCard.getPeriod())<0))
  			{
  				maxActivePeriod=topCard.getPeriod();
  			}
  		}
  	}
  	return maxActivePeriod;
  }
}
