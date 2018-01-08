package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

/**
 * Recycle
 * @author Seb
 *
 */
public class ReturnCard extends CardMove
{
  private CardLocation location;
  
  public ReturnCard(Player player, Card card, CardLocation location)
  {
    super(player,card);
    this.location=location;
  }

  @Override
  public String getLabel()
  {
    return "Return "+card.getNamePrefixedWithPeriod()+" from "+location.getLabel(player);
  }

  @Override
  public void doResolve()
  {
    Innovation.getViewer().log(player.getName()+" returns "+card.getNamePrefixedWithPeriod()+" from his "+location.getLabel());
    player.returnCard(card, location);
  }

  public static List<Move> getAllReturnCardMoves(Player player, CardLocation location)
  {
    List<Move> moves=new ArrayList<>();
    List<Card> cards=player.getCards(location);
    for (Card card : cards)
    {
      moves.add(new ReturnCard(player, card, location));  
    }
    return moves;
  }

}
