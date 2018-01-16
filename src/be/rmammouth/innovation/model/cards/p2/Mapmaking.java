package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Mapmaking extends Card
{
  public Mapmaking()
  {
    super("Mapmaking", Period.TWO, Color.GREEN,
          null,
          Resource.CROWN, Resource.CROWN, Resource.TOWER);

    addDogma(new SupremacyDogma(Resource.CROWN)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
        List<Card> period1InScore=affectedPlayer.getFilteredCards(CardLocation.SCORE_PILE, new CardPeriodFilter(Period.ONE));
        if (period1InScore.isEmpty()) return;
        List<Move> moves=TransferCard.getAllTransferCardMoves(period1InScore, affectedPlayer, CardLocation.SCORE_PILE, cas.getActivatingPlayer(), CardLocation.SCORE_PILE);
        affectedPlayer.getController().getAndResolveNextMove(moves);
        ((MapmakingActivationState)cas).cardTransfered=true;
      }
    });

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        if (((MapmakingActivationState)cas).cardTransfered)
        {
          DrawCard draw=new DrawCard(player, Period.ONE);
          draw.resolve();
          new ScoreCard(player, draw.getCard()).resolve();
          return true;
        }
        else return false;
      }
    });
  }

  @Override
  protected CardActivationState buildActivationState(GameModel model, Player activatingPlayer)
  {
    return new MapmakingActivationState(model, activatingPlayer);
  }
}

class MapmakingActivationState extends CardActivationState
{
  boolean cardTransfered=false;
  
  public MapmakingActivationState(GameModel model, Player activatingPlayer)
  {
    super(model, activatingPlayer);
  }
}