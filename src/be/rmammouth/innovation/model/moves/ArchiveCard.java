package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

/**
 * Archive (tuck)
 * @author Seb
 *
 */
public class ArchiveCard extends CardMove
{
  public ArchiveCard(Player player, Card card)
  {
    super(player, card);
  }

  @Override
  public String getLabel()
  {
    return "Archive "+card.getLabelPrefixedWithPeriod();
  }

  @Override
  protected void doResolve()
  {
    player.getGameModel().log(player.getName()+" has archived "+card.getLabelPrefixedWithPeriod());
    player.removeFromHand(card);
    if (card.getColor()!=null)
    {
      player.getCardsPile(card.getColor()).archive(card);
    }
  }
  
  public static List<Move> getAllArchiveCardMoves(Player player)
  {
    return getArchiveCardMoves(player, player.getHand());
  }

  public static List<Move> getArchiveCardMoves(Player player, List<Card> cards)
  {
    List<Move> moves=new ArrayList<>();
    for (Card card : cards)
    {
      moves.add(new ArchiveCard(player, card));  
    }
    return moves;
  }

  @Override
  public Move cloneMove(GameModel cloneModel)
  {
    return new ArchiveCard(cloneModel.getPlayers()[player.getIndex()], card);
  }
}
