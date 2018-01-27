package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Feudalism extends Card
{
  public Feudalism()
  {
    super("Feudalism", Period.THREE, Color.PURPLE,
          null,
          Resource.TOWER, Resource.LEAF, Resource.TOWER);

    addDogma(new SupremacyDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        List<Card> transferableCards=das.getAffectedPlayer().getFilteredHand(new CardResourceFilter(Resource.TOWER));
        if (transferableCards.isEmpty()) return null;
        else return new PlayerInteraction(das.getAffectedPlayer(), 
                                          TransferCard.getAllTransferCardMoves(transferableCards, das.getAffectedPlayer(), CardLocation.HAND, cas.getActivatingPlayer(), CardLocation.HAND));
      }
    });

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          das.setResolutionStep(1);
          List<Move> moves=new ArrayList<>();
          if (das.getAffectedPlayer().getCardsPile(Color.YELLOW).isSplayable(Splaying.LEFT))
          {
            moves.add(new SplayPile(das.getAffectedPlayer(), Color.YELLOW, Splaying.LEFT));
          }
          if (das.getAffectedPlayer().getCardsPile(Color.PURPLE).isSplayable(Splaying.LEFT))
          {
            moves.add(new SplayPile(das.getAffectedPlayer(), Color.PURPLE, Splaying.LEFT));
          }
          if (moves.isEmpty()) return null;
          moves.add(new Pass(das.getAffectedPlayer()));
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        return null;
      }
    });
  }
}
