package be.rmammouth.innovation.model.cards.p9;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Collaboration extends Card
{
  public Collaboration()
  {
    super("Collaboration", Period.NINE, Color.GREEN,
          null,
          Resource.CROWN, Resource.CLOCK, Resource.CROWN);

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
