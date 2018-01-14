package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Mapmaking extends Card
{
  public Mapmaking()
  {
    super("Mapmaking", Period.TWO, Color.GREEN,
          null,
          Resource.CROWN, Resource.CROWN, Resource.TOWER);

    addDogma(new SupremacyDogma(Resource.CROWN)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
