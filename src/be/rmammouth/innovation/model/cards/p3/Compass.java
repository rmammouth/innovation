package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Compass extends Card
{
  public Compass()
  {
    super("Compass", Period.THREE, Color.GREEN,
          null,
          Resource.CROWN, Resource.CROWN, Resource.LEAF);

    addDogma(new SupremacyDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          das.setResolutionStep(1);
          List<Card> transferableCards=das.getAffectedPlayer().getFilteredCards(CardLocation.BOARD, new CardFilter()
          {            
            @Override
            public boolean isFiltered(Card card)
            {
              return (card.getColor()==Color.GREEN) || !card.containsResource(Resource.LEAF);
            }
          });
          if (!transferableCards.isEmpty())
          {
            return new PlayerInteraction(das.getAffectedPlayer(), 
                                         TransferCard.getAllTransferCardMoves(transferableCards, das.getAffectedPlayer(), CardLocation.BOARD, cas.getActivatingPlayer(), CardLocation.BOARD));
          }
        }
        if (das.getResolutionStep()==1)
        {
          das.setResolutionStep(2);
          List<Card> transferableCards=cas.getActivatingPlayer().getFilteredCards(CardLocation.BOARD, new CardFilter()
          {            
            @Override
            public boolean isFiltered(Card card)
            {
              return card.containsResource(Resource.LEAF);
            }
          });
          if (!transferableCards.isEmpty())
          {
            return new PlayerInteraction(das.getAffectedPlayer(), 
                                         TransferCard.getAllTransferCardMoves(transferableCards, cas.getActivatingPlayer(), CardLocation.BOARD, das.getAffectedPlayer(), CardLocation.BOARD));
          }
        }        
        return null;
      }
    });
  }
}
