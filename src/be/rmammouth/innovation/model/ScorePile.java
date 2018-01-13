package be.rmammouth.innovation.model;

import java.util.*;

public class ScorePile
{
  private List<Card> cards=new ArrayList<Card>();
  private int score=0;
  
  public ScorePile()
  {    
  }
  
  public List<Card> getCards()
  {
    return Collections.unmodifiableList(cards);
  }

  public int getScore()
  {
    return score;
  }
  
  public void addCard(Card card)
  {
    cards.add(card);
    score+=card.getPeriod().asInt();
    sortCards();
  }
  
  public void removeCard(Card card)
  {
    cards.remove(card);
    score-=card.getPeriod().asInt();
  }
  
  public boolean canDominate(Period period)
  {
    return score>=(period.asInt()*5);
  }
  
  public boolean isEmpty()
  {
    return cards.isEmpty();
  }
  
  public int getSize()
  {
    return cards.size();
  }
  
  private void sortCards()
  {
    Collections.sort(cards, new Comparator<Card>()
    {
      @Override
      public int compare(Card c1, Card c2)
      {
        return c1.getPeriod().compareTo(c2.getPeriod());
      }
    });
  }
}
