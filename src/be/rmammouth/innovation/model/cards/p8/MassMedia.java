package be.rmammouth.innovation.model.cards.p8;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class MassMedia extends Card
{
  public MassMedia()
  {
    super("MassMedia", Period.EIGHT, Color.GREEN,
          Resource.BULB,
          null, Resource.CLOCK, Resource.BULB);

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });
  }
}
