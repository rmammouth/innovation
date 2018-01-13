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
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
        new DrawCard(affectedPlayer, Period.ONE).resolve();
        Period maxPeriodInHand=affectedPlayer.getHighestPeriod(CardLocation.HAND);
        List<Card> maxPeriodCardsInHand=affectedPlayer.getFilteredHand(new CardPeriodFilter(maxPeriodInHand));
        if (maxPeriodCardsInHand.isEmpty()) return;
        else
        {
          List<Move> moves=TransferCard.getAllTransferCardMoves(maxPeriodCardsInHand, affectedPlayer, CardLocation.HAND, cas.getActivatingPlayer(), CardLocation.HAND);
          affectedPlayer.getController().getAndResolveNextMove(moves);
        }
      }       
    });
	}  
}
