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
    player.getGameModel().log(player.getName()+" scores "+card.getLabelPrefixedWithPeriod()+" and has now "+player.getScorePile().getScore()+" pts");
  }
  
  public static List<Move> getAllScoreFromHandCardMoves(Player player)
  {
    return getAllScoreCardMoves(player, player.getHand());
  }
  
  public static List<Move> getAllScoreCardMoves(Player player, List<Card> cards)
  {
    List<Move> moves=new ArrayList<>();
    for (Card card : cards)
    {
      moves.add(new ScoreCard(player, card));  
    }
    return moves;
  }

  @Override
  public Move cloneMove(GameModel cloneModel)
  {
    return new ScoreCard(cloneModel.getPlayers()[player.getIndex()], card);
  }
}
