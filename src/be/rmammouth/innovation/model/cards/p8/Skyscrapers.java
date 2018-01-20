package be.rmammouth.innovation.model.cards.p8;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Skyscrapers extends Card
{
  public Skyscrapers()
  {
    super("Skyscrapers", Period.EIGHT, Color.YELLOW,
          null,
          Resource.FACTORY, Resource.CROWN, Resource.CROWN);

    addDogma(new SupremacyDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });
  }
}
