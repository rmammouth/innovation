package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.achievements.*;
import be.rmammouth.innovation.model.moves.*;

public class Translation extends Card
{
  public Translation()
  {
    super("Translation", Period.THREE, Color.BLUE,
          null,
          Resource.CROWN, Resource.CROWN, Resource.CROWN);

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getAffectedPlayer().getScorePile().isEmpty()) return null;
        if (das.getResolutionStep()==0)
        {          
          das.setResolutionStep(1);
          List<Move> moves=PlayCard.getAllPlayableCardMoves(das.getAffectedPlayer(), CardLocation.SCORE_PILE);
          moves.add(new Pass(das.getAffectedPlayer()));
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else if (das.getResolutionStep()==1)
        {
          if (das.getLastResolvedMove().isPass()) return null;
          return new PlayerInteraction(das.getAffectedPlayer(), PlayCard.getAllPlayableCardMoves(das.getAffectedPlayer(), CardLocation.SCORE_PILE));
        }
        return null;
      }
    });

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        boolean allCrownTopCard=true;
        for (Card card : das.getAffectedPlayer().getActiveCardsOnBoard())
        {
          if (!card.containsResource(Resource.CROWN))
          {
            allCrownTopCard=false;
            break;
          }
        }
        if (allCrownTopCard)
        {
          SpecialAchievement world=Achievements.get("World");
          das.getAffectedPlayer().getGameModel().log("Each top card of "+das.getAffectedPlayer().getName()+" has a [CROWN], so he claims the World achievment");
          das.getAffectedPlayer().dominate(world);
        }
        return null;
      }
    });
  }
}
