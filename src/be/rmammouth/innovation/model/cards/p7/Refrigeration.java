package be.rmammouth.innovation.model.cards.p7;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Refrigeration extends Card
{
  public Refrigeration()
  {
    super("Refrigeration", Period.SEVEN, Color.YELLOW,
          null,
          Resource.LEAF, Resource.LEAF, Resource.CROWN);

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
