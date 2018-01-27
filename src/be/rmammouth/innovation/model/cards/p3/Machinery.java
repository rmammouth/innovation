package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Machinery extends Card
{
  public Machinery()
  {
    super("Machinery", Period.THREE, Color.YELLOW,
          Resource.LEAF,
          Resource.LEAF, null, Resource.TOWER);

    addDogma(new SupremacyDogma(Resource.LEAF)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        List<Move> toYourHand=new ArrayList<>();
        Period highestPeriodInMyHand=cas.getActivatingPlayer().getHighestPeriod(CardLocation.HAND);
        if (highestPeriodInMyHand!=null)
        {
          List<Card> cardsToYourHand=cas.getActivatingPlayer().getFilteredHand(new CardPeriodFilter(highestPeriodInMyHand));
          toYourHand=TransferCard.getAllTransferCardMoves(cardsToYourHand, cas.getActivatingPlayer(), CardLocation.HAND, das.getAffectedPlayer(), CardLocation.HAND);
        }
        
        List<Move> toMyHand=new ArrayList<>();
        if (!das.getAffectedPlayer().getHand().isEmpty())
        {
          List<Card> cardsToMyHand=das.getAffectedPlayer().getHand();
          toMyHand=TransferCard.getAllTransferCardMoves(cardsToMyHand, das.getAffectedPlayer(), CardLocation.HAND, cas.getActivatingPlayer(), CardLocation.HAND);
        }
        
        Move.resolveAll(toYourHand);
        Move.resolveAll(toMyHand);
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          List<Card> towerCards=das.getAffectedPlayer().getFilteredHand(new CardResourceFilter(Resource.TOWER));
          das.setResolutionStep(1);
          if (!towerCards.isEmpty())
          {
            return new PlayerInteraction(das.getAffectedPlayer(), ScoreCard.getAllScoreCardMoves(das.getAffectedPlayer(), towerCards));
          }
        }
        if ((das.getResolutionStep()==1) && das.getAffectedPlayer().getCardsPile(Color.RED).isSplayable(Splaying.LEFT))
        {
          das.setResolutionStep(2);
          return new PlayerInteraction(das.getAffectedPlayer(), new SplayPile(das.getAffectedPlayer(), Color.RED, Splaying.LEFT),
                                                                new Pass(das.getAffectedPlayer()));
        }
        return null;
      }
    });
  }
}
