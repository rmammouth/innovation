package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Paper extends Card
{
  public Paper()
  {
    super("Paper", Period.THREE, Color.GREEN,
          null,
          Resource.BULB, Resource.BULB, Resource.CROWN);

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          das.setResolutionStep(1);
          List<Move> moves=new ArrayList<>();
          if (das.getAffectedPlayer().getCardsPile(Color.GREEN).isSplayable(Splaying.LEFT))
          {
            moves.add(new SplayPile(das.getAffectedPlayer(), Color.GREEN, Splaying.LEFT));
          }
          if (das.getAffectedPlayer().getCardsPile(Color.BLUE).isSplayable(Splaying.LEFT))
          {
            moves.add(new SplayPile(das.getAffectedPlayer(), Color.BLUE, Splaying.LEFT));
          }
          if (moves.isEmpty()) return null;
          moves.add(new Pass(das.getAffectedPlayer()));
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        for (Color color : Color.values())
        {
          if (das.getAffectedPlayer().getCardsPile(color).getSplaying()==Splaying.LEFT)
          {
            new DrawCard(das.getAffectedPlayer(), Period.FOUR).resolve();
          }
        }
        return null;
      }
    });
  }
}
