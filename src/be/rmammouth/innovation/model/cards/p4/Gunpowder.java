package be.rmammouth.innovation.model.cards.p4;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Gunpowder extends Card
{
  public Gunpowder()
  {
    super("Gunpowder", Period.FOUR, Color.RED,
          null,
          Resource.FACTORY, Resource.CROWN, Resource.FACTORY);

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
