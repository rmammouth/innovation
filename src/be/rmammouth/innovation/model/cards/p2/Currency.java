package be.rmammouth.innovation.model.cards.p2;

import be.rmammouth.innovation.model.*;

public class Currency extends Card
{
  public Currency()
  {
    super("Currency", Period.TWO, Color.GREEN,
      Resource.LEAF,
      Resource.CROWN, null, Resource.CROWN);
  }
}
