package be.rmammouth.innovation.model.cards.p7;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Publications extends Card
{
  public Publications()
  {
    super("Publications", Period.SEVEN, Color.BLUE,
          null,
          Resource.BULB, Resource.CLOCK, Resource.BULB);

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
