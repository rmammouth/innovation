package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

/**
 * Put a card into play (meld)
 * @author Seb
 *
 */
public class PlayCard extends ActionMove
{
  private CardLocation location=CardLocation.HAND;
  
  public PlayCard(Player player, Card card)
  {
  	super(player,card);
  }
  
  public PlayCard(Player player, Card card, CardLocation location)
  {
    super(player,card);
    this.location=location;
  }
  
	@Override
	public String getLabel()
	{
		return "Play "+card.getLabelPrefixedWithPeriod();
	}

	@Override
	protected boolean doResolveAction()
	{
	  player.getGameModel().log(player.getName()+" puts "+card.getLabelPrefixedWithPeriod()+" into play from his "+location);
		player.putCardInPlay(card, location);
		return true;
	}
	
	public static List<Move> getAllPlayableCardMoves(Player player)
	{
		return getAllPlayableCardMoves(player, CardLocation.HAND);
	}
	
	public static List<Move> getAllPlayableCardMoves(Player player, CardLocation location)
  {
    return getPlayCardMoves(player, player.getCards(location), location);
  }
	
	public static List<Move> getPlayCardMoves(Player player, List<Card> cards)
  {
	  return getPlayCardMoves(player, cards, CardLocation.HAND);
  }
	
	public static List<Move> getPlayCardMoves(Player player, List<Card> cards, CardLocation location)
  {
    List<Move> moves=new ArrayList<>();
    for (Card card : cards)
    {
      moves.add(new PlayCard(player, card, location));  
    }
    return moves;
  }

  @Override
  public Move cloneMove(GameModel cloneModel)
  {
    return new PlayCard(cloneModel.getPlayers()[player.getIndex()], card, location);
  }
}
