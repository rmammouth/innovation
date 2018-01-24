package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Tools extends Card
{
  public Tools()
  {
    super("Tools", Period.ONE, Color.BLUE,
          null,
          Resource.BULB, Resource.BULB, Resource.TOWER);

    addDogma(new CooperationDogma(Resource.BULB)
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
          if (returnedCards==3)
          {
            DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.THREE);
            draw.resolve();
          }
          else if (!das.getAffectedPlayer().getHand().isEmpty() && !das.getLastResolvedMove().isPass())
          {
            return getRecycleCards(das);
          }
          return null;
        }
      }
      
      private PlayerInteraction getRecycleCards(DogmaActivationStatus das)
      {
        List<Move> moves=RecycleCard.getAllRecycleCardMoves(das.getAffectedPlayer(), CardLocation.HAND);
        moves.add(new Pass(das.getAffectedPlayer()));
        return new PlayerInteraction(das.getAffectedPlayer(), moves);
      }
    });

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          List<Move> moves=RecycleCard.getRecycleCardMoves(das.getAffectedPlayer(), das.getAffectedPlayer().getFilteredHand(new CardPeriodFilter(Period.THREE)), CardLocation.HAND);
          if (moves.isEmpty()) return null;
          moves.add(new Pass(das.getAffectedPlayer()));
          das.setResolutionStep(1);
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else
        {
          if (!das.getLastResolvedMove().isPass())
          {
            for (int i=0;i<3;i++)
            {
              new DrawCard(das.getAffectedPlayer(), Period.ONE).resolve();
            }
          }
          return null;          
        }
      }
    });
  }
}
