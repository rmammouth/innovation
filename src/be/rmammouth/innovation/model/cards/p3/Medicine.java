package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Medicine extends Card
{
  public Medicine()
  {
    super("Medicine", Period.THREE, Color.YELLOW,
          Resource.CROWN,
          Resource.LEAF, Resource.LEAF, null);

    addDogma(new SupremacyDogma(Resource.LEAF)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          das.setResolutionStep(1);
          Period highestInMyScorePile=das.getAffectedPlayer().getHighestPeriod(CardLocation.SCORE_PILE);
          if (highestInMyScorePile!=null)
          {
            List<Move> moves=new ArrayList<Move>();
            List<Card> cards=das.getAffectedPlayer().getFilteredCards(CardLocation.SCORE_PILE, new CardPeriodFilter(highestInMyScorePile));
            for (Card card : cards)
            {
              moves.add(new ChooseCard(das.getAffectedPlayer(), card, "Transfer "+card.getLabel()+" to "+cas.getActivatingPlayer()+" score pile"));
            }
            return new PlayerInteraction(das.getAffectedPlayer(), moves);
          }          
        }
        if (das.getResolutionStep()==1)
        {
          das.setResolutionStep(2);
          Period lowestInHisScorePile=cas.getActivatingPlayer().getLowestPeriod(CardLocation.SCORE_PILE);
          if (lowestInHisScorePile!=null)
          {
            List<Card> cards=cas.getActivatingPlayer().getFilteredCards(CardLocation.SCORE_PILE, new CardPeriodFilter(lowestInHisScorePile));
            List<Move> moves=TransferCard.getAllTransferCardMoves(cards, cas.getActivatingPlayer(), CardLocation.SCORE_PILE, das.getAffectedPlayer(), CardLocation.SCORE_PILE);
            return new PlayerInteraction(das.getAffectedPlayer(), moves);
          }
        }
        if (das.getResolutionStep()==2)
        {
          List<Move> moves=das.getAllResolvedMoves(ChooseCard.class);
          if (!moves.isEmpty())
          {
            ChooseCard choose=(ChooseCard)moves.get(0);
            new TransferCard(choose.getCard(), das.getAffectedPlayer(), CardLocation.SCORE_PILE, cas.getActivatingPlayer(), CardLocation.SCORE_PILE).resolve();
          }
        }
        return null;
      }
    });
  }
}
