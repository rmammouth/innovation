package be.rmammouth.innovation.model.cards.p3;

import be.rmammouth.innovation.model.*;

public class Medicine extends Card
{
  public Medicine()
  {
    super("Medicine", Period.THREE, Color.YELLOW,
        Resource.CROWN,
        Resource.LEAF, Resource.LEAF, null);
  }
}
