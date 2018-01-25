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
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          if (das.getAffectedPlayer().getHand().isEmpty()) return null;
          List<Move> moves=new ArrayList<>(RecycleCard.getAllRecycleCardMoves(das.getAffectedPlayer(), CardLocation.HAND));
          moves.add(new Pass(das.getAffectedPlayer()));
          das.setResolutionStep(1);
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else
        {
          if (!das.getLastResolvedMove().isPass()) 
          {
            Card returnedCard=((RecycleCard)das.getLastResolvedMove()).getCard();
            if (returnedCard.getPeriod()==Period.TEN) cas.getModel().gameOverByLastPileEmpty();
            DrawCard drawCard=new DrawCard(das.getAffectedPlayer(), returnedCard.getPeriod().next());
            drawCard.resolve();
            ScoreCard scoreCard=new ScoreCard(das.getAffectedPlayer(), drawCard.getCard());
            scoreCard.resolve();            
          }
          return null;
        }
      }
    });
  }
}
