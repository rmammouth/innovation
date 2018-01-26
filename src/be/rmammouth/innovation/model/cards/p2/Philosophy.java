package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Philosophy extends Card
{
  public Philosophy()
  {
    super("Philosophy", Period.TWO, Color.PURPLE,
          null,
          Resource.BULB, Resource.BULB, Resource.BULB);

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getNumberOfResolvedMoves()==0)
        {
          List<Move> moves=SplayPile.getAllSplayablePileMoves(das.getAffectedPlayer(), Splaying.LEFT);
          if (moves.isEmpty()) return null;
          moves.add(new Pass(das.getAffectedPlayer()));
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else return null;
      }
    });

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getNumberOfResolvedMoves()==0)
        {
          List<Move> moves=ScoreCard.getAllScoreFromHandCardMoves(das.getAffectedPlayer());
          if (moves.isEmpty()) return null;
          moves.add(new Pass(das.getAffectedPlayer()));
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else return null;
      }
    });
  }
}
