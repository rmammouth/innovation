package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Medicine extends Card
{
  public Medicine()
  {
    super("Medicine", Period.THREE, Color.YELLOW,
          Resource.CROWN,
          Resource.LEAF, Resource.LEAF, null);

    addDogma(new SupremacyDogma(Resource.LEAF)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });
  }
}
