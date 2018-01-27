package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Engineering extends Card
{
  public Engineering()
  {
    super("Engineering", Period.THREE, Color.RED,
          Resource.TOWER,
          null, Resource.BULB, Resource.TOWER);

    addDogma(new SupremacyDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        List<Card> topTowerCards=das.getAffectedPlayer().getFilteredCards(CardLocation.BOARD, new CardResourceFilter(Resource.TOWER));
        List<Move> moves=TransferCard.getAllTransferCardMoves(topTowerCards, das.getAffectedPlayer(), CardLocation.BOARD, cas.getActivatingPlayer(), CardLocation.SCORE_PILE);
        Move.resolveAll(moves);
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getNumberOfResolvedMoves()==0)
        {
          if (das.getAffectedPlayer().getCardsPile(Color.RED).getSplaying()==Splaying.LEFT) return null;        
          else return new PlayerInteraction(das.getAffectedPlayer(), new SplayPile(das.getAffectedPlayer(), Color.RED, Splaying.LEFT),
                                                                     new Pass(das.getAffectedPlayer()));
        }
        else return null;
      }
    });
  }
}
