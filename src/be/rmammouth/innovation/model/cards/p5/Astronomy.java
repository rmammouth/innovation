package be.rmammouth.innovation.model.cards.p5;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Astronomy extends Card
{
  public Astronomy()
  {
    super("Astronomy", Period.FIVE, Color.PURPLE,
          Resource.CROWN,
          Resource.BULB, Resource.BULB, null);

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
