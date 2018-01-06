package be.rmammouth.innovation.model.cards;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Agriculture extends Card
{
	public Agriculture()
	{
		super("Agriculture", Period.ONE, Color.YELLOW,
			  null,
			  Resource.LEAF, Resource.LEAF, Resource.LEAF);
		
		dogmas.add(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        if (!player.getHand().isEmpty())
        {
          List<Move> moves=new ArrayList<>(ReturnCard.getAllReturnCardMoves(player, CardLocation.HAND));
          Pass passMove=new Pass(player);
          moves.add(passMove);
          Move chosenMove=player.getController().getAndResolveNextMove(moves);
          if (chosenMove==passMove) return false;
          else
          {
            Card returnedCard=((ReturnCard)chosenMove).getCard();
            DrawCard drawCard=new DrawCard(player, returnedCard.getPeriod().next());
            drawCard.resolveAndLog();
            ScoreCard scoreCard=new ScoreCard(player, drawCard.getCard());
            scoreCard.resolveAndLog();            
            return true;
          }
        }
        else return false;
      }
    });
	}
	
}
