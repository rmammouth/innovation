package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class DominatePeriod extends ActionMove
{
  private Period period;  
  
  public DominatePeriod(Player player, Period period)
  {
    super(player, (Card)player.getGameModel().getPeriodAchievement(period));
    this.period = period;
  }

  @Override
  public String getLabel()
  {
    return "Dominate ["+period.asString()+"]";
  }

  @Override
  protected boolean doResolveAction()
  {    
    player.dominate(card);
    Innovation.getViewManager().log(player.getName()+" dominates period "+period.asString()+" and has now "+player.getDominations().size()+" dominations");
    return true;
  }
  
  public static List<DominatePeriod> getAllDominablePeriodMoves(Player player)
  {
    List<DominatePeriod> moves=new ArrayList<>();
    for (Period period : Period.values())
    {
      if (player.getGameModel().isPeriodAchievementAvailable(period) && player.getScorePile().canDominate(period))
      {
        moves.add(new DominatePeriod(player, period));
      }
    }
    return moves;
  }
}
