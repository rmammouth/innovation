package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Archery extends Card
{
	public Archery()
	{
		super("Archery", Period.ONE, Color.RED,
				  Resource.TOWER,
				  Resource.BULB, null, Resource.TOWER);
		
		addDogma(new SupremacyDogma(Resource.TOWER)
    {      
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          new DrawCard(das.getAffectedPlayer(), Period.ONE).resolve();
          Period maxPeriodInHand=das.getAffectedPlayer().getHighestPeriod(CardLocation.HAND);
          List<Card> maxPeriodCardsInHand=das.getAffectedPlayer().getFilteredHand(new CardPeriodFilter(maxPeriodInHand));
          List<Move> moves=TransferCard.getAllTransferCardMoves(maxPeriodCardsInHand, das.getAffectedPlayer(), CardLocation.HAND, cas.getActivatingPlayer(), CardLocation.HAND);
          das.setResolutionStep(1);
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else return null;
      }
    });
	}  
}
