package be.rmammouth.innovation.model.cards.p8;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Corporations extends Card
{
  public Corporations()
  {
    super("Corporations", Period.EIGHT, Color.GREEN,
          null,
          Resource.FACTORY, Resource.FACTORY, Resource.CROWN);

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
