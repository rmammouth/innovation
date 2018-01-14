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
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        int playedCards;
        for (playedCards=0;playedCards<2;playedCards++)
        {
          List<Move> playMoves=PlayCard.getAllPlayableCardMoves(player);
          Pass passPlay=new Pass(player);
          if (playedCards>0) playMoves.add(passPlay);
          Move chosenMove=player.getController().getAndResolveNextMove(playMoves);
          if (chosenMove==passPlay) break;
        }
        if (playedCards==2)
        {
          List<Move> transferMoves=new ArrayList<>();
          Iterator<Player> otherPlayers=cas.getModel().getOtherPlayersIterator(player);
          while (otherPlayers.hasNext())
          {
            TransferCard transferRed=new TransferCard(player.getCardsPile(Color.RED).getTopCard(), player, CardLocation.BOARD, otherPlayers.next(), CardLocation.BOARD);
            transferMoves.add(transferRed);
          }
          Pass passTransfer=new Pass(player);
          transferMoves.add(passTransfer);
          Move chosenMove=player.getController().getAndResolveNextMove(transferMoves);
          if (chosenMove!=passTransfer)
          {
            Player toPlayer=((TransferCard)chosenMove).getToPlayer();
            if (!toPlayer.getCardsPile(Color.GREEN).isEmpty())
            {
              TransferCard transferGreen=new TransferCard(toPlayer.getCardsPile(Color.GREEN).getTopCard(), toPlayer, CardLocation.BOARD, player, CardLocation.BOARD);
              transferGreen.resolve();
            }
          }
        }
        return false;
      }
    });
  }
}
