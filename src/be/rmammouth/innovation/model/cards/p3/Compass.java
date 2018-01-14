package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Compass extends Card
{
  public Compass()
  {
    super("Compass", Period.THREE, Color.GREEN,
          null,
          Resource.CROWN, Resource.CROWN, Resource.LEAF);

    addDogma(new SupremacyDogma(Resource.CROWN)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });
  }
}
