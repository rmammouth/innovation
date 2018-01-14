package be.rmammouth.innovation.model.cards.p6;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Emancipation extends Card
{
  public Emancipation()
  {
    super("Emancipation", Period.SIX, Color.PURPLE,
          Resource.FACTORY,
          Resource.BULB, Resource.FACTORY, null);

    addDogma(new SupremacyDogma(Resource.FACTORY)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });

    addDogma(new CooperationDogma(Resource.FACTORY)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
