package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Domestication extends Card
{
  public Domestication()
  {
    super("Domestication", Period.ONE, Color.YELLOW,
          Resource.TOWER,
          Resource.CROWN, null, Resource.TOWER);

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          Period lowestPeriod=das.getAffectedPlayer().getLowestPeriod(CardLocation.HAND);
          if (lowestPeriod==null)
          {
            new DrawCard(das.getAffectedPlayer(), Period.ONE).resolve();
            return null;
          }
          else
          {
            List<Card> lowestCards=das.getAffectedPlayer().getFilteredHand(new CardPeriodFilter(lowestPeriod));
            List<Move> playCardMoves=new ArrayList<Move>(PlayCard.getPlayCardMoves(das.getAffectedPlayer(), lowestCards));
            das.setResolutionStep(1);
            return new PlayerInteraction(das.getAffectedPlayer(), playCardMoves);
          }
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
