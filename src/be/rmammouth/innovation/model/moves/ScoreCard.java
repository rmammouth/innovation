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
    return "Score "+card.getNamePrefixedWithPeriod();
  }

  @Override
  protected void doResolve()
  {
    Innovation.getViewer().log(player.getName()+" scores "+card.getNamePrefixedWithPeriod());
    player.removeFromHand(card);
    player.addToScorePile(card);
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
