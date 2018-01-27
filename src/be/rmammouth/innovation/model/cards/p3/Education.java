package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Education extends Card
{
  public Education()
  {
    super("Education", Period.THREE, Color.PURPLE,
          Resource.BULB,
          Resource.BULB, Resource.BULB, null);

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getAffectedPlayer().getScorePile().isEmpty()) return null;
        if (das.getResolutionStep()==0)
        {
          das.setResolutionStep(1);
          Period highestPeriodInScore=das.getAffectedPlayer().getHighestPeriod(CardLocation.SCORE_PILE);
          List<Card> highestCardsInScore=das.getAffectedPlayer().getFilteredCards(CardLocation.SCORE_PILE, new CardPeriodFilter(highestPeriodInScore));
          List<Move> moves=RecycleCard.getRecycleCardMoves(das.getAffectedPlayer(), highestCardsInScore, CardLocation.SCORE_PILE);
          moves.add(new Pass(das.getAffectedPlayer()));
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else if (das.getResolutionStep()==1)
        {
          if (das.getLastResolvedMove().isPass()) return null;
          RecycleCard recycle=(RecycleCard)das.getLastResolvedMove();
          Period recycledPeriod=recycle.getCard().getPeriod();
          if (recycledPeriod.compareTo(Period.EIGHT)>0) cas.getModel().gameOverByLastPileEmpty();
          else
          {
            new DrawCard(das.getAffectedPlayer(), recycledPeriod.next().next()).resolve();
          }
        }
        return null;
      }
    });
  }
}
