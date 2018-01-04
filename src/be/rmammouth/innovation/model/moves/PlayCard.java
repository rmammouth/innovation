package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class PlayCard extends Move
{
	private Card card;
	
  public PlayCard(Player player, Card card)
  {
  	super(player);
  	this.card=card;
  }
  
	@Override
	public String getLabel()
	{
		return "Play "+card.getPeriodName();
	}

	public Card getCard()
	{
		return card;
	}

	@Override
	public void resolve()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getResolvedLabel()
	{
		return player.getName()+" has played "+card.getPeriodName();
	}
	
	public static List<PlayCard> getAllPlayCardsForHand(PlayerModel playerModel)
	{
		List<PlayCard> moves=new ArrayList<>();
		for (Card card : playerModel.getHand())
		{
			moves.add(new PlayCard(playerModel.getPlayer(), card));	
		}
		return moves;
	}
}
