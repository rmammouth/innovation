package be.rmammouth.innovation.model.cards.p1;

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
		
		addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        if (!player.getHand().isEmpty())
        {
          List<Move> moves=new ArrayList<>(RecycleCard.getAllRecycleCardMoves(player, CardLocation.HAND));
          Pass passMove=new Pass(player);
          moves.add(passMove);
          Move chosenMove=player.getController().getAndResolveNextMove(moves);
          if (chosenMove==passMove) return false;
          else
          {
            Card returnedCard=((RecycleCard)chosenMove).getCard();
            DrawCard drawCard=new DrawCard(player, returnedCard.getPeriod().next());
            drawCard.resolve();
            ScoreCard scoreCard=new ScoreCard(player, drawCard.getCard());
            scoreCard.resolve();            
            return true;
          }
        }
        else return false;
      }
    });
	}
	
}
