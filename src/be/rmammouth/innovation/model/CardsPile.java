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
  
  public void archive(Card card)
  {
    cards.add(0, card);
  }
  
  public void remove(Card card)
  {
    cards.remove(card);
    if (cards.size()<=1)
    {
      splaying=Splaying.NONE;
    }
  }
  
  public boolean isEmpty()
  {
    return cards.isEmpty();
  }
  
  public int getSize()
  {
    return cards.size();
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

  public Splaying getSplaying()
  {
    return splaying;
  }
  
  public void setSplaying(Splaying splaying) 
  {
    this.splaying = splaying;
  }
  
  public boolean isSplayable(Splaying newSplaying)
  {
    return (cards.size()>=2) && (newSplaying!=splaying);
  }

  public List<Card> getCards()
  {
    return Collections.unmodifiableList(cards);
  }

  public CardsPile clonePile()
  {
    CardsPile clone=new CardsPile();
    clone.splaying=splaying;
    for (Card card : cards)
    {
      clone.cards.add(card);
    }
    return clone;
  }
}
