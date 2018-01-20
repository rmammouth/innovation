package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class TheWheel extends Card
{
  public TheWheel()
  {
    super("TheWheel", Period.ONE, Color.GREEN,
          null,
          Resource.TOWER, Resource.TOWER, Resource.TOWER);

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        for (int n=0;n<2;n++)
        {
          new DrawCard(das.getAffectedPlayer(), Period.ONE).resolve();
        }
        return null;
      }
    });
  }
}
