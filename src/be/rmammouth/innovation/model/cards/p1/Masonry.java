package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.achievements.*;
import be.rmammouth.innovation.model.moves.*;

public class Masonry extends Card
{
  public Masonry()
  {
    super("Masonry", Period.ONE, Color.YELLOW,
          Resource.TOWER,
          null, Resource.TOWER, Resource.TOWER);

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        CardFilter towerFilter=new CardResourceFilter(Resource.TOWER);        
        List<Card> towerCards=das.getAffectedPlayer().getFilteredHand(towerFilter);
        if (towerCards.isEmpty() || ((das.getLastResolvedMove()!=null) && das.getLastResolvedMove().isPass()))
        {
          //test for achievement
          SpecialAchievement monument=Achievements.get("Monument");
          if ((das.getNumberOfResolvedMoves(PlayCard.class)>=4) && das.getAffectedPlayer().getGameModel().isSpecialAchievementAvailable(monument))
          {
            das.getAffectedPlayer().getGameModel().log(das.getAffectedPlayer().getName()+" has put 4 or more [TOWER] cards into play and dominates the Monument achievement");
            das.getAffectedPlayer().dominate(monument);
          }
          return null;
        }
        else
        {
          List<Move> playMoves=new ArrayList<Move>(PlayCard.getPlayCardMoves(das.getAffectedPlayer(), towerCards));
          playMoves.add(new Pass(das.getAffectedPlayer()));
          return new PlayerInteraction(das.getAffectedPlayer(), playMoves);
        }     
      }
    });
  }
}
