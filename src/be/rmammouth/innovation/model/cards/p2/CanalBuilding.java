package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class CanalBuilding extends Card
{
  public CanalBuilding()
  {
    super("Canal Building", Period.TWO, Color.YELLOW,
        null,
        Resource.CROWN, Resource.LEAF, Resource.CROWN);
    
    addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        if (player.getHand().isEmpty() && player.getScorePile().isEmpty()) return false;
        
        Option exchange=new Option(player, "Exchange all the highest cards in your hand with all the highest cards in your score pile", 
                                   player.getName()+" exchanges all the highest cards in his hand with all the highest cards in his score pile");
        Pass pass=new Pass(player);
        Move chosenMove=player.getController().getAndResolveNextMove(Arrays.asList(exchange, pass));
        if (chosenMove==exchange)
        {
          Period highestInHand=player.getHighestPeriod(CardLocation.HAND);
          if (highestInHand!=null)
          {
            List<Move> moveToScore=TransferCard.getAllTransferCardMoves(player.getFilteredCards(CardLocation.HAND, new CardPeriodFilter(highestInHand)), player, CardLocation.HAND, player, CardLocation.SCORE_PILE);
            Move.resolveAll(moveToScore);
          }
          
          Period highestInScore=player.getHighestPeriod(CardLocation.SCORE_PILE);
          if (highestInScore!=null)
          {
            List<Move> moveToHand=TransferCard.getAllTransferCardMoves(player.getFilteredCards(CardLocation.SCORE_PILE, new CardPeriodFilter(highestInHand)), player, CardLocation.SCORE_PILE, player, CardLocation.HAND);
            Move.resolveAll(moveToHand);
          }
          return true;
        }
        return false;
      }
    });
  }
}
