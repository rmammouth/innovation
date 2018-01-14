package be.rmammouth.innovation.model.cards.p7;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Railroad extends Card
{
  public Railroad()
  {
    super("Railroad", Period.SEVEN, Color.PURPLE,
          Resource.CLOCK,
          Resource.FACTORY, Resource.CLOCK, null);

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
