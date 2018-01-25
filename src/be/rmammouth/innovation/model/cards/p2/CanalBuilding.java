package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class CanalBuilding extends Card
{
  public CanalBuilding()
  {
    super("CanalBuilding", Period.TWO, Color.YELLOW,
          null,
          Resource.CROWN, Resource.LEAF, Resource.CROWN);

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          if (das.getAffectedPlayer().getHand().isEmpty() && das.getAffectedPlayer().getScorePile().isEmpty()) return null;
        
          Option exchange=new Option(das.getAffectedPlayer(), "Exchange all the highest cards in your hand with all the highest cards in your score pile", 
                                     das.getAffectedPlayer().getName()+" exchanges all the highest cards in his hand with all the highest cards in his score pile");
          Pass pass=new Pass(das.getAffectedPlayer());
          das.setResolutionStep(1);
          return new PlayerInteraction(das.getAffectedPlayer(), exchange, pass);
        }
        else
        {
          if (das.getLastResolvedMove().isPass()) return null;
          Period highestInHand=das.getAffectedPlayer().getHighestPeriod(CardLocation.HAND);
          if (highestInHand!=null)
          {
            List<Move> moveToScore=TransferCard.getAllTransferCardMoves(das.getAffectedPlayer().getFilteredCards(CardLocation.HAND, new CardPeriodFilter(highestInHand)), 
                                                                        das.getAffectedPlayer(), CardLocation.HAND, das.getAffectedPlayer(), CardLocation.SCORE_PILE);
            Move.resolveAll(moveToScore);
          }
          
          Period highestInScore=das.getAffectedPlayer().getHighestPeriod(CardLocation.SCORE_PILE);
          if (highestInScore!=null)
          {
            List<Move> moveToHand=TransferCard.getAllTransferCardMoves(das.getAffectedPlayer().getFilteredCards(CardLocation.SCORE_PILE, new CardPeriodFilter(highestInHand)), 
                                                                       das.getAffectedPlayer(), CardLocation.SCORE_PILE, das.getAffectedPlayer(), CardLocation.HAND);
            Move.resolveAll(moveToHand);
          }
          return null;
        }      
      }
    });
  }
}
