package be.rmammouth.innovation.model.cards.p6;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Industrialization extends Card
{
  public Industrialization()
  {
    super("Industrialization", Period.SIX, Color.RED,
          Resource.CROWN,
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
  }
}
