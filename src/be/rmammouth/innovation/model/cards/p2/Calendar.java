package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Calendar extends Card
{
  public Calendar()
  {
    super("Calendar", Period.TWO, Color.BLUE,
          null,
          Resource.LEAF, Resource.LEAF, Resource.BULB);

    addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getAffectedPlayer().getScorePile().getSize()>das.getAffectedPlayer().getHand().size())
        {
          for (int i=0;i<2;i++)
          {
            new DrawCard(das.getAffectedPlayer(), Period.THREE).resolve();
          }
        }
        return null;
      }
    });
  }
}
