package be.rmammouth.innovation.model.cards.p6;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class AtomicTheory extends Card
{
  public AtomicTheory()
  {
    super("AtomicTheory", Period.SIX, Color.BLUE,
          Resource.BULB,
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
