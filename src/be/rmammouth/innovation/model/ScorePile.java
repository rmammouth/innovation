package be.rmammouth.innovation.model;

import java.util.*;

public class ScorePile
{
  private Player player;
  private List<Card> cards=new ArrayList<Card>();
  private int score=0;
  
  public ScorePile(Player player)
  {    
    this.player=player;
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

  public ScorePile cloneForPlayer(Player clonePlayer)
  {
    ScorePile clone=new ScorePile(clonePlayer);
    for (Card card : cards)
    {
      if (this.player.getIndex()==clonePlayer.getIndex())
      {
        clone.cards.add(card);
        clone.score+=card.getPeriod().asInt();
      }
      else
      {
        clone.cards.add(card.cloneCard());
        clone.score+=card.getPeriod().asInt();
      }
    }
    return clone;
  }
}
