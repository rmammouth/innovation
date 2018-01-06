package be.rmammouth.innovation.model.moves;

import java.util.*;

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
	public void resolve()
	{
		player.putCardInPlay(card);
	}

	@Override
	public String getResolvedLabel()
	{
		return player.getName()+" has played "+card.getNamePrefixedWithPeriod();
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
