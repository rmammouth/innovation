package be.rmammouth.innovation.model.cards.p7;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Lighting extends Card
{
  public Lighting()
  {
    super("Lighting", Period.SEVEN, Color.PURPLE,
          null,
          Resource.LEAF, Resource.CLOCK, Resource.LEAF);

    addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });
  }
}
