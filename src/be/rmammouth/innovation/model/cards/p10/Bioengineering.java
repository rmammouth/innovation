package be.rmammouth.innovation.model.cards.p10;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Bioengineering extends Card
{
  public Bioengineering()
  {
    super("Bioengineering", Period.TEN, Color.BLUE,
          Resource.BULB,
          Resource.CLOCK, Resource.CLOCK, null);

    addDogma(new CooperationDogma(Resource.CLOCK)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.CLOCK)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });
  }
}
