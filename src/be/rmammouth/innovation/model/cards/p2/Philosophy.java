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
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        List<Color> splayableLeft=player.getSplayableColors(Splaying.LEFT);
        if (splayableLeft.isEmpty()) return false;
        List<Move> moves=SplayPile.getSplayPileMoves(player, splayableLeft, Splaying.LEFT);
        Pass pass=new Pass(player);
        moves.add(pass);
        Move chosenMove=player.getController().getAndResolveNextMove(moves);
        if (chosenMove==pass) return false;
        else return true;
      }
    });

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        if (player.getHand().isEmpty()) return false;
        List<Move> scoreMoves=ScoreCard.getAllScoreCardMoves(player);
        Pass pass=new Pass(player);
        scoreMoves.add(pass);
        Move chosenMove=player.getController().getAndResolveNextMove(scoreMoves);
        return (chosenMove!=pass);
      }
    });
  }
}
