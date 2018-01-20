package be.rmammouth.innovation.model.cards.p1;

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
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          final Set<Color> colorsOnBoard=das.getAffectedPlayer().getColorsOnBoard();
          List<Card> playableCards=das.getAffectedPlayer().getFilteredHand(new CardFilter()
          {          
            @Override
            public boolean isFiltered(Card card)
            {
              return colorsOnBoard.contains(card.getColor());
            }
          });
          if (playableCards.isEmpty()) return null;
         
          List<Move> playCardMoves=new ArrayList<Move>(PlayCard.getPlayCardMoves(das.getAffectedPlayer(), playableCards));
          das.setResolutionStep(1);
          return new PlayerInteraction(das.getAffectedPlayer(), playCardMoves);
        }
        else return null;
      }
    });

    addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        Set<Color> colorsOnBoard=das.getAffectedPlayer().getColorsOnBoard();
        for (Player otherPlayer : das.getAffectedPlayer().getGameModel().getPlayers())
        {
          if (otherPlayer!=das.getAffectedPlayer())
          {
            colorsOnBoard.removeAll(otherPlayer.getColorsOnBoard());
          }
        }
        
        for (int n=0;n<colorsOnBoard.size();n++)
        {
          DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.ONE);
          draw.resolve();
          ScoreCard score=new ScoreCard(das.getAffectedPlayer(), draw.getCard());
          score.resolve();
        }
        
        return null;
      }
    });
  }
}
