package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class PlayCard extends CardMove
{
  public PlayCard(Player player, Card card)
  {
  	super(player,card);
  }
  
	@Override
	public String getLabel()
	{
		return "Play "+card.getNamePrefixedWithPeriod();
	}

	@Override
	public void doResolve()
	{
	  Innovation.getViewer().log(player.getName()+" puts "+card.getNamePrefixedWithPeriod()+" into play");
		player.putCardInPlay(card);		
	}
	
	public static List<Move> getAllPlayableCardMoves(Player player)
	{
		return getPlayCardMoves(player, player.getHand());
	}
	
	public static List<Move> getPlayCardMoves(Player player, List<Card> cards)
  {
	  List<Move> moves=new ArrayList<>();
    for (Card card : cards)
    {
      moves.add(new PlayCard(player, card));  
    }
    return moves;
  }
}
