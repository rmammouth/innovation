package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Writing extends Card
{
  public Writing()
  {
    super("Writing", Period.ONE, Color.BLUE,
          null,
          Resource.BULB, Resource.BULB, Resource.CROWN);

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        new DrawCard(das.getAffectedPlayer(), Period.TWO).resolve();
        return null;
      }
    });
  }
}
