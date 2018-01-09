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
    return "Archive "+card.getNamePrefixedWithPeriod();
  }

  @Override
  public void doResolve()
  {
    Innovation.getViewer().log(player.getName()+" has archived "+card.getNamePrefixedWithPeriod());
    player.getCardsPile(card.getColor()).archive(card);
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
}
