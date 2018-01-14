package be.rmammouth.innovation.model.cards.p4;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Colonialism extends Card
{
  public Colonialism()
  {
    super("Colonialism", Period.FOUR, Color.RED,
          null,
          Resource.FACTORY, Resource.BULB, Resource.FACTORY);

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
