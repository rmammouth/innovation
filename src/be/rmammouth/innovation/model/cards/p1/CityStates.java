package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class CityStates extends Card
{
  public CityStates()
  {
    super("CityStates", Period.ONE, Color.PURPLE,
          null,
          Resource.CROWN, Resource.CROWN, Resource.TOWER);

    addDogma(new SupremacyDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          if (cas.getResourceCount(das.getAffectedPlayer(), Resource.TOWER)<4) return null;
          List<Card> topCardsWithTower=Card.getFilteredList(das.getAffectedPlayer().getActiveCardsOnBoard(), new CardResourceFilter(Resource.TOWER));
          if (topCardsWithTower.isEmpty()) return null;
          List<Move> transferMoves=TransferCard.getAllTransferCardMoves(topCardsWithTower, das.getAffectedPlayer(), CardLocation.BOARD, cas.getActivatingPlayer(), CardLocation.BOARD);
          das.setResolutionStep(1);
          return new PlayerInteraction(das.getAffectedPlayer(), transferMoves);          
        }
        else
        {
          new DrawCard(das.getAffectedPlayer(), Period.ONE).resolve();    
          return null;
        }
      }
    });
  }
}
