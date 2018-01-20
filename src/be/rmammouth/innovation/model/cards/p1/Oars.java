package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Oars extends Card
{
  public Oars()
  {
    super("Oars", Period.ONE, Color.RED,
          Resource.TOWER,
          Resource.CROWN, null, Resource.TOWER);

    addDogma(new SupremacyDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          List<Card> crownCards=das.getAffectedPlayer().getFilteredHand(new CardResourceFilter(Resource.CROWN));
          if (crownCards.isEmpty()) return null;
          List<Move> transferMoves=TransferCard.getAllTransferCardMoves(crownCards, das.getAffectedPlayer(), CardLocation.HAND, cas.getActivatingPlayer(), CardLocation.SCORE_PILE);
          das.setResolutionStep(1);          
          return new PlayerInteraction(das.getAffectedPlayer(), transferMoves);
        }
        else
        {
          ((OarsActivationStatus)cas).cardTransferred=true;
          new DrawCard(das.getAffectedPlayer(), Period.ONE).resolve();
          return null;
        }        
      }
    });

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (!((OarsActivationStatus)cas).cardTransferred)
        {
          new DrawCard(das.getAffectedPlayer(), Period.ONE).resolve();
        }
        return null;
      }
    });
  }
  
  @Override
  protected CardActivationStatus buildActivationStatus(Player activatingPlayer)
  {
    return new OarsActivationStatus(activatingPlayer, this);
  }
}

class OarsActivationStatus extends CardActivationStatus
{
  boolean cardTransferred=false;
  
  public OarsActivationStatus(Player activatingPlayer, Card card) 
  {
    super(activatingPlayer, card);
  }  
}
