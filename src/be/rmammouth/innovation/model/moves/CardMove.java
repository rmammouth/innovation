package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.model.*;

public abstract class CardMove extends Move
{
  protected Card card;
  
  public CardMove(Player player, Card card)
  {
    super(player);
    this.card=card;
  }

  public Card getCard()
  {
    return card;
  }
}
