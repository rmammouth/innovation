package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Feudalism extends Card
{
  public Feudalism()
  {
    super("Feudalism", Period.THREE, Color.PURPLE,
          null,
          Resource.TOWER, Resource.LEAF, Resource.TOWER);

    addDogma(new SupremacyDogma(Resource.TOWER)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
