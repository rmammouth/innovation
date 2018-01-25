package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class RoadBuilding extends Card
{
  public RoadBuilding()
  {
    super("RoadBuilding", Period.TWO, Color.RED,
          Resource.TOWER,
          null, Resource.TOWER, Resource.TOWER);

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          if (das.getAffectedPlayer().getHand().isEmpty()) return null;
          das.setResolutionStep(1);
          return new PlayerInteraction(das.getAffectedPlayer(), PlayCard.getAllPlayableCardMoves(das.getAffectedPlayer()));
        }
        else if (das.getResolutionStep()==1)
        {
          if (das.getAffectedPlayer().getHand().isEmpty()) return null;
          List<Move> moves=PlayCard.getAllPlayableCardMoves(das.getAffectedPlayer());
          moves.add(new Pass(das.getAffectedPlayer()));
          das.setResolutionStep(2);
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else if (das.getResolutionStep()==2)
        {
          if (das.getLastResolvedMove().isPass()) return null;
          else
          {
            //propose transfer
            if (das.getAffectedPlayer().getCardsPile(Color.RED).isEmpty()) return null;
            List<Move> moves=new ArrayList<>();
            Iterator<Player> otherPlayers=cas.getModel().getOtherPlayersIterator(das.getAffectedPlayer());
            while (otherPlayers.hasNext())
            {
              Player otherPlayer=otherPlayers.next();
              TransferCard transfer=new TransferCard(das.getAffectedPlayer().getCardsPile(Color.RED).getTopCard(), das.getAffectedPlayer(), CardLocation.BOARD, otherPlayer, CardLocation.BOARD);
              moves.add(transfer);
            }
            moves.add(new Pass(das.getAffectedPlayer()));
            das.setResolutionStep(3);
            return new PlayerInteraction(das.getAffectedPlayer(), moves);
          }
        }
        else if (das.getResolutionStep()==3)
        {
          if (!das.getLastResolvedMove().isPass())
          {
            // red card has been transferred
            TransferCard transfer=(TransferCard)das.getLastResolvedMove();
            Card topGreenCard=transfer.getToPlayer().getCardsPile(Color.GREEN).getTopCard();
            if (topGreenCard!=null)
            {
              new TransferCard(topGreenCard, transfer.getToPlayer(), CardLocation.BOARD, das.getAffectedPlayer(), CardLocation.BOARD).resolve();
            }
          }
        }
        return null;
      }
    });
  }
}
