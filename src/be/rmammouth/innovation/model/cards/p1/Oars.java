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
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer) 
      {
        List<Card> crownCards=affectedPlayer.getFilteredHand(new CardResourceFilter(Resource.CROWN));
        if (!crownCards.isEmpty())
        {
          List<Move> transferMoves=TransferCard.getAllTransferCardMoves(crownCards, affectedPlayer, CardLocation.HAND, cas.getActivatingPlayer(), CardLocation.SCORE_PILE);
          affectedPlayer.getController().getAndResolveNextMove(transferMoves);
          new DrawCard(affectedPlayer, Period.ONE).resolve();
          ((OarsActivationState)cas).cardTransferred=true;
        }
      }
    });
    
    addDogma(new CooperationDogma(Resource.TOWER)
    {      
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player) 
      {
        if (!((OarsActivationState)cas).cardTransferred)
        {
          new DrawCard(player, Period.ONE).resolve();
          return true;
        }
        else return false;
      }
    });
  }

  @Override
  protected CardActivationState buildActivationState(GameModel model, Player activatingPlayer) 
  {
    return new OarsActivationState(model, activatingPlayer);
  }
}

class OarsActivationState extends CardActivationState
{
  boolean cardTransferred=false;
  
  public OarsActivationState(GameModel model, Player activatingPlayer) 
  {
    super(model, activatingPlayer);
  }  
}

