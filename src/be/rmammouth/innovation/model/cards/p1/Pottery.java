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
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          if (das.getAffectedPlayer().getHand().isEmpty()) return null;
          das.setResolutionStep(1);
          return getRecycleCards(das);
        }
        else
        {
          int returnedCards=das.getNumberOfResolvedMoves(RecycleCard.class);
          if (das.getAffectedPlayer().getHand().isEmpty() || das.getLastResolvedMove().isPass() || (returnedCards==3))
          {
            if (returnedCards>0)
            {
              DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.fromInt(returnedCards));
              draw.resolve();
              ScoreCard score=new ScoreCard(das.getAffectedPlayer(), draw.getCard());
              score.resolve();
              return null;
            }
            else return null;
          }
          else
          {
            return getRecycleCards(das);
          }
        }
      }
      
      private PlayerInteraction getRecycleCards(DogmaActivationStatus das)
      {
        List<Move> moves=RecycleCard.getAllRecycleCardMoves(das.getAffectedPlayer(), CardLocation.HAND);
        moves.add(new Pass(das.getAffectedPlayer()));
        return new PlayerInteraction(das.getAffectedPlayer(), moves);
      }
    });

    addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        new DrawCard(das.getAffectedPlayer(), Period.ONE).resolve();
        return null;
      }
    });
  }
}
