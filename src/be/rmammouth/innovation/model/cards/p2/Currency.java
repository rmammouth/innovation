package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Currency extends Card
{
  public Currency()
  {
    super("Currency", Period.TWO, Color.GREEN,
          Resource.LEAF,
          Resource.CROWN, null, Resource.CROWN);

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (!das.getAffectedPlayer().getHand().isEmpty() && ((das.getLastResolvedMove()==null) || !das.getLastResolvedMove().isPass()))
        {
          List<Move> moves=RecycleCard.getAllRecycleCardMoves(das.getAffectedPlayer(), CardLocation.HAND);
          moves.add(new Pass(das.getAffectedPlayer()));
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else
        {
          List<Move> recycles=das.getAllResolvedMoves(RecycleCard.class);
          Set<Period> periods=new HashSet<>();
          for (Move move : recycles)
          {
            periods.add(((RecycleCard)move).getCard().getPeriod());
          }
          for (int i=0;i<periods.size();i++)
          {
            DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.TWO);
            draw.resolve();
            new ScoreCard(das.getAffectedPlayer(), draw.getCard()).resolve();
          }
          return null;
        }
      }
    });
  }
}
