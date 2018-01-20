package be.rmammouth.innovation.model.cards.p10;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Databases extends Card
{
  public Databases()
  {
    super("Databases", Period.TEN, Color.GREEN,
          null,
          Resource.CLOCK, Resource.CLOCK, Resource.CLOCK);

    addDogma(new SupremacyDogma(Resource.CLOCK)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });
  }
}
