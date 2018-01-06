package be.rmammouth.innovation.model;

import java.util.*;

public class CardsPile
{
	private Splaying splaying=Splaying.NONE;
  private LinkedList<Card> cards=new LinkedList<>();  //last is top card
  
  public Card getTopCard()
  {
  	if (cards.isEmpty()) return null;
  	else return cards.getLast();
  }
  
  public void addCardOnTop(Card card)
  {
    cards.add(card);
  }
  
  public boolean isEmpty()
  {
    return cards.isEmpty();
  }
  
  public void addResourcesToCount(ResourcesCount count)
  {
    Iterator<Card> itr=cards.descendingIterator();
    if (!itr.hasNext()) return;
    itr.next().addAllResourcesToCount(count);
    while (itr.hasNext())
    {
      itr.next().addSplayedResourcesToCount(count, splaying);
    }
  }
}
