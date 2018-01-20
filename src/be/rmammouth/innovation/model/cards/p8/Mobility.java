package be.rmammouth.innovation.model.cards.p8;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Mobility extends Card
{
  public Mobility()
  {
    super("Mobility", Period.EIGHT, Color.RED,
          null,
          Resource.FACTORY, Resource.CLOCK, Resource.FACTORY);

    addDogma(new SupremacyDogma(Resource.FACTORY)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });
  }
}
