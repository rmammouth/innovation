package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Metalworking extends Card
{
  public Metalworking()
  {
    super("Metalworking", Period.ONE, Color.RED,
          Resource.TOWER,
          Resource.TOWER, null, Resource.TOWER);

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        boolean cardHasTower;
        do
        {
          DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.ONE);
          draw.resolve();
          cardHasTower=draw.getCard().containsResource(Resource.TOWER);
          if (cardHasTower)
          {
            new ScoreCard(das.getAffectedPlayer(), draw.getCard()).resolve();            
          }
        }
        while (cardHasTower);
        return null;
      }
    });
  }
}
