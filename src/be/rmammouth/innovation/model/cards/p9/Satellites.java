package be.rmammouth.innovation.model.cards.p9;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Satellites extends Card
{
  public Satellites()
  {
    super("Satellites", Period.NINE, Color.GREEN,
          null,
          Resource.CLOCK, Resource.CLOCK, Resource.CLOCK);

    addDogma(new CooperationDogma(Resource.CLOCK)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
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
