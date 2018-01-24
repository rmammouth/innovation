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
  public PlayCard(Player player, Card card)
  {
  	super(player,card);
  }
  
	@Override
	public String getLabel()
	{
		return "Play "+card.getLabelPrefixedWithPeriod();
	}

	@Override
	protected boolean doResolveAction()
	{
	  player.getGameModel().log(player.getName()+" puts "+card.getLabelPrefixedWithPeriod()+" into play");
		player.putCardInPlay(card);		
		return true;
	}
	
	public static List<PlayCard> getAllPlayableCardMoves(Player player)
	{
		return getPlayCardMoves(player, player.getHand());
	}
	
	public static List<PlayCard> getPlayCardMoves(Player player, List<Card> cards)
  {
	  List<PlayCard> moves=new ArrayList<>();
    for (Card card : cards)
    {
      moves.add(new PlayCard(player, card));  
    }
    return moves;
  }

  @Override
  public Move cloneMove(GameModel cloneModel)
  {
    return new PlayCard(cloneModel.getPlayers()[player.getIndex()], card);
  }
}
