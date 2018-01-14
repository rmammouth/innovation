package be.rmammouth.innovation.model.cards.p8;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class QuantumTheory extends Card
{
  public QuantumTheory()
  {
    super("QuantumTheory", Period.EIGHT, Color.BLUE,
          Resource.CLOCK,
          Resource.CLOCK, Resource.CLOCK, null);

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
