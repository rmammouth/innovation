package be.rmammouth.innovation.model.moves;

import java.util.*;

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
  public void resolve()
  {
    player.returnCard(card, location);
  }

  @Override
  public String getResolvedLabel()
  {
    return player.getName()+" has returned "+card.getNamePrefixedWithPeriod()+" from his "+location.getLabel();
  }

  public static List<Move> getAllReturnCardMoves(Player player, CardLocation location)
  {
    List<Move> moves=new ArrayList<>();
    List<Card> cards=player.getCards(location);
    for (Card card : cards)
    {
      moves.add(new ReturnCard(player, card, CardLocation.HAND));  
    }
    return moves;
  }

}
