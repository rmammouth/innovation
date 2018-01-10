package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class DominatePeriod extends Move
{
  private Period period;  
  
  public DominatePeriod(Player player, Period period)
  {
    super(player);
    this.period = period;
  }

  @Override
  public String getLabel()
  {
    return "Dominate ["+period.asString()+"]";
  }

  @Override
  protected void doResolve()
  {
    Innovation.getViewer().log(player.getName()+" dominates period "+period.asString());
    PeriodCard card=player.getGameModel().getPeriodAchievement(period);
    player.dominate(card);
  }
  
  public static List<Move> getAllDominablePeriodMoves(Player player)
  {
    List<Move> moves=new ArrayList<>();
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
