package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Alchemy extends Card
{
  public Alchemy()
  {
    super("Alchemy", Period.THREE, Color.BLUE,
          null,
          Resource.LEAF, Resource.TOWER, Resource.TOWER);

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        int cardsToDraw=cas.getResourceCount(das.getAffectedPlayer(), Resource.TOWER) / 3;
        boolean redCardDrawn=false;
        for (int i=0;i<cardsToDraw;i++)
        {
          DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.FOUR);
          draw.resolve();
          Color colorDrawn=draw.getCard().getColor();
          if ((colorDrawn!=null) && (colorDrawn==Color.RED)) redCardDrawn=true;
        }
        if (redCardDrawn)
        {
          //return everything
          Move.resolveAll(RecycleCard.getAllRecycleCardMoves(das.getAffectedPlayer(), CardLocation.HAND));
        }
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          das.setResolutionStep(1);
          if (!das.getAffectedPlayer().getHand().isEmpty())
          {
            return new PlayerInteraction(das.getAffectedPlayer(), PlayCard.getAllPlayableCardMoves(das.getAffectedPlayer()));
          }
        }
        if (das.getResolutionStep()==1)
        {
          das.setResolutionStep(2);
          if (!das.getAffectedPlayer().getHand().isEmpty())
          {
            return new PlayerInteraction(das.getAffectedPlayer(), ScoreCard.getAllScoreFromHandCardMoves(das.getAffectedPlayer()));
          }
        }
        return null;
      }
    });
  }
}
