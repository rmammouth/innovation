package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;

public class PlayerModel
{
	private Player player;
  private List<Achievement> achievements=new ArrayList<>();
  private List<Card> hand=new ArrayList<>();
  private Map<Color, CardsPile> zone=new EnumMap<>(Color.class);
  
  public PlayerModel(Player player)
	{
		this.player=player;
		for (Color color : Color.values())
		{
			zone.put(color, new CardsPile());
		}
	}

	public Player getPlayer()
	{
		return player;
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
