package be.rmammouth.innovation.model.cards.p9;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Fission extends Card
{
  public Fission()
  {
    super("Fission", Period.NINE, Color.RED,
          null,
          Resource.CLOCK, Resource.CLOCK, Resource.CLOCK);

    addDogma(new SupremacyDogma(Resource.CLOCK)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });

    addDogma(new CooperationDogma(Resource.CLOCK)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
