package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

/**
 * Recycle (return)
 * @author Seb
 *
 */
public class RecycleCard extends CardMove
{
  private CardLocation location;
  
  public RecycleCard(Player player, Card card, CardLocation location)
  {
    super(player,card);
    this.location=location;
  }

  @Override
  public String getLabel()
  {
    return "Recycle "+card.getLabelPrefixedWithPeriod()+" from "+location.getLabel(player);
  }

  @Override
  protected void doResolve()
  {
    player.getGameModel().log(player.getName()+" recycles "+card.getLabelPrefixedWithPeriod()+" from his "+location.getLabel());
    player.returnCard(card, location);
  }

  public static List<Move> getAllRecycleCardMoves(Player player, CardLocation location)
  {
    return getRecycleCardMoves(player, player.getCards(location), location);
  }
  
  public static List<Move> getRecycleCardMoves(Player player, List<Card> cards, CardLocation location)
  {
    List<Move> moves=new ArrayList<>();
    for (Card card : cards)
    {
      moves.add(new RecycleCard(player, card, location));  
    }
    return moves;
  }

  @Override
  public Move cloneMove(GameModel cloneModel)
  {
    return new RecycleCard(cloneModel.getPlayers()[player.getIndex()], card, location);
  }

}
