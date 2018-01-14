package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Machinery extends Card
{
  public Machinery()
  {
    super("Machinery", Period.THREE, Color.YELLOW,
          Resource.LEAF,
          Resource.LEAF, null, Resource.TOWER);

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
