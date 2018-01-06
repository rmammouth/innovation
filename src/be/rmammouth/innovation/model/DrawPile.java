package be.rmammouth.innovation.model;

import java.util.*;

public class DrawPile
{
  private LinkedList<Card> cards=new LinkedList<>();
  
  public DrawPile()
  {  	
  }
  
  public void add(Card card)
  {
  	cards.add(card);
  }
  
  public void shuffle()
  {
  	Collections.shuffle(cards);
  }
  
  public boolean isEmpty()
  {
  	return cards.isEmpty();
  }
  
  public Card draw()
  {
  	return cards.removeLast();
  }

  public void returnCard(Card card)
  {
    cards.addFirst(card);    
  }
}
