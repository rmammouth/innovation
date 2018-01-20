package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class ScoreCard extends CardMove
{
  public ScoreCard(Player player, Card card)
  {
    super(player, card);
  }

  @Override
  public String getLabel()
  {
    return "Score "+card.getLabelPrefixedWithPeriod();
  }

  @Override
  protected void doResolve()
  {    
    player.removeFromHand(card);
    player.addToScorePile(card);
    Innovation.getViewManager().log(player.getName()+" scores "+card.getLabelPrefixedWithPeriod()+" and has now "+player.getScorePile().getScore()+" pts");
  }
  
  public static List<Move> getAllScoreCardMoves(Player player)
  {
    List<Move> moves=new ArrayList<>();
    for (Card card : player.getHand())
    {
      moves.add(new ScoreCard(player, card));  
    }
    return moves;
  }
}
