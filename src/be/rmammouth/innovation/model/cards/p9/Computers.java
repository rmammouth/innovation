package be.rmammouth.innovation.model.cards.p9;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Computers extends Card
{
  public Computers()
  {
    super("Computers", Period.NINE, Color.BLUE,
          Resource.CLOCK,
          null, Resource.CLOCK, Resource.FACTORY);

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
