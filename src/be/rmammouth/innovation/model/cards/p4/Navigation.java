package be.rmammouth.innovation.model.cards.p4;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Navigation extends Card
{
  public Navigation()
  {
    super("Navigation", Period.FOUR, Color.GREEN,
          null,
          Resource.CROWN, Resource.CROWN, Resource.CROWN);

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
