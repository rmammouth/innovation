package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Monotheism extends Card
{
  public Monotheism()
  {
    super("Monotheism", Period.TWO, Color.PURPLE,
      null,
      Resource.TOWER, Resource.TOWER, Resource.TOWER);
    
    addDogma(new SupremacyDogma(Resource.TOWER)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
        List<Card> transferableCards=affectedPlayer.getFilteredCards(CardLocation.BOARD, new CardFilter()
        {          
          @Override
          public boolean isFiltered(Card card)
          {
            return cas.getActivatingPlayer().getColorsOnBoard().contains(card.getColor());
          }
        });
        if (transferableCards.isEmpty()) return;
        List<Move> moves=TransferCard.getAllTransferCardMoves(transferableCards, affectedPlayer, CardLocation.BOARD, cas.getActivatingPlayer(), CardLocation.SCORE_PILE);
        affectedPlayer.getController().getAndResolveNextMove(moves);
        
        DrawCard draw=new DrawCard(affectedPlayer, Period.ONE);
        draw.resolve();
        new ArchiveCard(affectedPlayer, draw.getCard()).resolve();
      }
    });
    
    addDogma(new CooperationDogma(Resource.TOWER)
    { 
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        DrawCard draw=new DrawCard(player, Period.ONE);
        draw.resolve();
        new ArchiveCard(player, draw.getCard()).resolve();
        return true;
      }
    });
  }
}
