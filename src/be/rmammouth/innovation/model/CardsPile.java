package be.rmammouth.innovation.model;

import java.util.*;

public class CardsPile
{
	private Splaying splaying=Splaying.NONE;
  private LinkedList<Card> cards=new LinkedList<>();
  
  public Card getTopCard()
  {
  	if (cards.isEmpty()) return null;
  	else return cards.getLast();
  }
}
