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
	
	public static List<PlayCard> getAllPlayableCardMoves(Player player)
	{
		List<PlayCard> moves=new ArrayList<>();
		for (Card card : player.getHand())
		{
			moves.add(new PlayCard(player, card));	
		}
		return moves;
	}
}
