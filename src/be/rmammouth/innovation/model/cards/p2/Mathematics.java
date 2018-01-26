package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Mathematics extends Card
{
  public Mathematics()
  {
    super("Mathematics", Period.TWO, Color.BLUE,
          null,
          Resource.BULB, Resource.CROWN, Resource.BULB);

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getNumberOfResolvedMoves()==0)
        {
          if (das.getAffectedPlayer().getHand().isEmpty()) return null;
          List<Move> recycles=RecycleCard.getAllRecycleCardMoves(das.getAffectedPlayer(), CardLocation.HAND);
          recycles.add(new Pass(das.getAffectedPlayer()));
          return new PlayerInteraction(das.getAffectedPlayer(), recycles);    
        }
        else
        {
          if (!das.getLastResolvedMove().isPass())
          {
            Period period=((RecycleCard)das.getLastResolvedMove()).getCard().getPeriod();
            if (period==Period.TEN) cas.getModel().gameOverByLastPileEmpty();
            DrawCard draw=new DrawCard(das.getAffectedPlayer(), period.next());
            draw.resolve();
            new PlayCard(das.getAffectedPlayer(), draw.getCard()).resolve();
          }
          return null;
        }
      }
    });
  }
}
