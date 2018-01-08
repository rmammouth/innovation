package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Pottery extends Card
{
	public Pottery()
	{
		super("Pottery", Period.ONE, Color.BLUE,
			  null,
			  Resource.LEAF, Resource.LEAF, Resource.LEAF);
		
		addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        int returnedCards;
        for (returnedCards=0;returnedCards<3;returnedCards++)
        {
          if (player.getHand().isEmpty()) break;
          else
          {
            List<Move> moves=ReturnCard.getAllReturnCardMoves(player, CardLocation.HAND);
            Pass pass=new Pass(player);
            moves.add(pass);
            Move chosenMove=player.getController().getAndResolveNextMove(moves);
            if (chosenMove==pass) break;
          }
        }
        
        if (returnedCards>0)
        {
          DrawCard draw=new DrawCard(player, Period.fromInt(returnedCards));
          draw.resolve();
          ScoreCard score=new ScoreCard(player, draw.getCard());
          score.resolve();
          return true;
        }
        else return false;
      }
    });
		
		addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        new DrawCard(player, Period.ONE).resolve();
        return true;
      }
    });
	}
}
