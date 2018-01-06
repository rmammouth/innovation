package be.rmammouth.innovation.model.cards;

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
		
		dogmas.add(new SupremacyDogma(Resource.TOWER)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
        new DrawCard(affectedPlayer, Period.ONE).resolveAndLog();
        Period maxPeriodInHand=affectedPlayer.getHighestPeriodInHand();
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
