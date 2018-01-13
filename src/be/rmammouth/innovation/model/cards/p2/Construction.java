package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.achievements.*;
import be.rmammouth.innovation.model.moves.*;

public class Construction extends Card
{
  public Construction()
  {
    super("Construction", Period.TWO, Color.RED,
        Resource.TOWER,
        null, Resource.TOWER, Resource.TOWER);
    
    addDogma(new SupremacyDogma(Resource.TOWER)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
        for (int i=0;i<2;i++)
        {
          List<Card> hand=affectedPlayer.getHand();
          if (!hand.isEmpty())
          {
            List<Move> moves=TransferCard.getAllTransferCardMoves(hand, affectedPlayer, CardLocation.HAND, cas.getActivatingPlayer(), CardLocation.HAND);
            affectedPlayer.getController().getAndResolveNextMove(moves);
          }
        }
        new DrawCard(affectedPlayer, Period.TWO).resolve();        
      }
    });
    
    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {        
        if (player.getActiveCardsOnBoard().size()==5)
        {
          boolean onlyPlayerWith5TopCards=true;
          Iterator<Player> itr=player.getGameModel().getOtherPlayersIterator(player);
          while (itr.hasNext() && onlyPlayerWith5TopCards)
          {
            Player otherPlayer=itr.next();
            if (otherPlayer.getActiveCardsOnBoard().size()==5)
            {
              onlyPlayerWith5TopCards=false;
            }
          }
          if (onlyPlayerWith5TopCards)
          {
            Innovation.getViewer().log(player.getName()+" is the only one with 5 colors on board and dominates the Empire achievement");
            player.dominate(Achievements.get("Empire"));
            return true;
          }
          else return false;
        }
        else return false;
      }
    });
  }
}
