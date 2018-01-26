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
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getNumberOfResolvedMoves()==0)
        {
          List<Card> transferableCards=das.getAffectedPlayer().getFilteredCards(CardLocation.BOARD, new CardFilter()
          {          
            @Override
            public boolean isFiltered(Card card)
            {
              return cas.getActivatingPlayer().hasColorOnBoard(card.getColor());
            }
          });
          if (transferableCards.isEmpty()) return null;
          List<Move> moves=TransferCard.getAllTransferCardMoves(transferableCards, das.getAffectedPlayer(), CardLocation.BOARD, cas.getActivatingPlayer(), CardLocation.SCORE_PILE);
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else
        {
          DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.ONE);
          draw.resolve();
          new ArchiveCard(das.getAffectedPlayer(), draw.getCard()).resolve();
          return null;
        }
      }
    });

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.ONE);
        draw.resolve();
        new ArchiveCard(das.getAffectedPlayer(), draw.getCard()).resolve();
        return null;
      }
    });
  }
}
