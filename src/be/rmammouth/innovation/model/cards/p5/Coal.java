package be.rmammouth.innovation.model.cards.p5;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Coal extends Card
{
  public Coal()
  {
    super("Coal", Period.FIVE, Color.RED,
          Resource.FACTORY,
          Resource.FACTORY, Resource.FACTORY, null);

    addDogma(new CooperationDogma(Resource.FACTORY)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.FACTORY)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.FACTORY)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });
  }
}
