package be.rmammouth.innovation.model.cards.p10;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class SelfService extends Card
{
  public SelfService()
  {
    super("SelfService", Period.TEN, Color.GREEN,
          null,
          Resource.CROWN, Resource.CROWN, Resource.CROWN);

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });
  }
}
