package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class CityStates extends Card
{
  public CityStates()
  {
    super("CityStates", Period.ONE, Color.PURPLE,
          null,
          Resource.CROWN, Resource.CROWN, Resource.TOWER);

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
