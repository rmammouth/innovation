package be.rmammouth.innovation.model.cards.p10;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class AI extends Card
{
  public AI()
  {
    super("AI", Period.TEN, Color.PURPLE,
          Resource.BULB,
          Resource.BULB, Resource.CLOCK, null);

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
