package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Mysticism extends Card
{
  public Mysticism()
  {
    super("Mysticism", Period.ONE, Color.PURPLE,
          null,
          Resource.TOWER, Resource.TOWER, Resource.TOWER);

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.ONE);
        draw.resolve();
        if (das.getAffectedPlayer().getColorsOnBoard().contains(draw.getCard().getColor()))
        {
          new PlayCard(das.getAffectedPlayer(), draw.getCard()).resolve();
          new DrawCard(das.getAffectedPlayer(), Period.ONE).resolve();
        }
        return null;
      }
    });
  }
}
