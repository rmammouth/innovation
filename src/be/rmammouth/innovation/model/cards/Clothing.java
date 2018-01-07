package be.rmammouth.innovation.model.cards;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Clothing extends Card
{
	public Clothing()
	{
		super("Clothing", Period.ONE, Color.GREEN,
			  null,
			  Resource.CROWN, Resource.LEAF, Resource.LEAF);
		
		addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        final Set<Color> colorsOnBoard=player.getColorsOnBoard();
        List<Card> playableCards=player.getFilteredHand(new CardFilter()
        {          
          @Override
          public boolean isFiltered(Card card)
          {
            return colorsOnBoard.contains(card.getColor());
          }
        });
        if (!playableCards.isEmpty())
        {
          List<Move> playCardMoves=PlayCard.getPlayCardMoves(player, playableCards);
          player.getController().getAndResolveNextMove(playCardMoves);
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
        Set<Color> colorsOnBoard=player.getColorsOnBoard();
        for (Player otherPlayer : player.getGameModel().getPlayers())
        {
          if (otherPlayer!=player)
          {
            colorsOnBoard.removeAll(otherPlayer.getColorsOnBoard());
          }
        }
        
        for (int n=0;n<colorsOnBoard.size();n++)
        {
          DrawCard draw=new DrawCard(player, Period.ONE);
          draw.resolve();
          ScoreCard score=new ScoreCard(player, draw.getCard());
          score.resolve();
        }
        
        return !colorsOnBoard.isEmpty();
      }
    });
	}
}
