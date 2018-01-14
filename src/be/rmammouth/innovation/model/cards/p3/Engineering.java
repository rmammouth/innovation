package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Engineering extends Card
{
  public Engineering()
  {
    super("Engineering", Period.THREE, Color.RED,
          Resource.TOWER,
          null, Resource.BULB, Resource.TOWER);

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
