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
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        Set<Period> recycledPeriods=new HashSet<Period>();
        while (!player.getHand().isEmpty())
        {
          List<Move> moves=RecycleCard.getAllRecycleCardMoves(player, CardLocation.HAND);
          Pass pass=new Pass(player);
          moves.add(pass);
          Move chosenMove=player.getController().getAndResolveNextMove(moves);
          if (chosenMove==pass) break;
          else recycledPeriods.add(((RecycleCard)chosenMove).getCard().getPeriod());          
        }
        if (recycledPeriods.isEmpty()) return false;
        else
        {
          for (int n=0;n<recycledPeriods.size();n++)
          {
            DrawCard draw=new DrawCard(player, Period.TWO);
            draw.resolve();
            new ScoreCard(player, draw.getCard()).resolve();
          }
          return true;
        }
      }
    });
  }
}
