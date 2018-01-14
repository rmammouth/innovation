package be.rmammouth.innovation.model.cards.p6;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Vaccination extends Card
{
  public Vaccination()
  {
    super("Vaccination", Period.SIX, Color.YELLOW,
          Resource.LEAF,
          Resource.FACTORY, Resource.LEAF, null);

    addDogma(new SupremacyDogma(Resource.LEAF)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });

    addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
