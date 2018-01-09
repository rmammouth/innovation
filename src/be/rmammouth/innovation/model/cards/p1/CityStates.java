package be.rmammouth.innovation.model.cards.p1;


import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class CityStates extends Card
{
  public CityStates()
  {
    super("City States", Period.ONE, Color.PURPLE,
        null,
        Resource.CROWN, Resource.CROWN, Resource.TOWER);   
    
    addDogma(new SupremacyDogma(Resource.CROWN)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
        if (cas.getResourceCount(affectedPlayer, Resource.TOWER)<4) return;
        List<Card> topCardsWithTower=Card.getFilteredList(affectedPlayer.getActiveCardsOnBoard(), new CardResourceFilter(Resource.TOWER));
        if (topCardsWithTower.isEmpty()) return;
        List<Move> transferMoves=TransferCard.getAllTransferCardMoves(topCardsWithTower, affectedPlayer, CardLocation.BOARD, cas.getActivatingPlayer(), CardLocation.BOARD);
        affectedPlayer.getController().getAndResolveNextMove(transferMoves);
        new DrawCard(affectedPlayer, Period.ONE).resolve();        
      }
    });
  }
}
