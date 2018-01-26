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
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getNumberOfResolvedMoves()>0)
        {
          ((MapMakingActivationStatus)cas).cardTransferred=true;
          return null;
        }
        List<Card> p1cards=das.getAffectedPlayer().getFilteredCards(CardLocation.SCORE_PILE, new CardPeriodFilter(Period.ONE));
        if (p1cards.isEmpty()) return null;
        else return new PlayerInteraction(das.getAffectedPlayer(), 
                                          TransferCard.getAllTransferCardMoves(p1cards, das.getAffectedPlayer(), CardLocation.SCORE_PILE, cas.getActivatingPlayer(), CardLocation.SCORE_PILE));
      }
    });

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (((MapMakingActivationStatus)cas).cardTransferred)
        {
          DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.ONE);
          draw.resolve();
          new ScoreCard(das.getAffectedPlayer(), draw.getCard()).resolve();
        }
        return null;
      }
    });
  }

  @Override
  protected CardActivationStatus buildActivationStatus(Player activatingPlayer)
  {
    return new MapMakingActivationStatus(activatingPlayer, this);
  }
  
  
}

class MapMakingActivationStatus extends CardActivationStatus
{
  boolean cardTransferred=false;
  
  public MapMakingActivationStatus(Player activatingPlayer, Card card)
  {
    super(activatingPlayer, card);
  }  
}