package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class DrawCard extends CardMove
{
	private Period period;  //null if best pile available for player
	
	public DrawCard(Player player)
	{
		super(player,null);
		period=player.getHighestActivePeriod();
		if (period==null) period=Period.ONE;
	}
	
	public DrawCard(Player player, Period period)
	{
		super(player,null);
		this.period=period;
	}

	@Override
	public String getLabel()
	{
		return "Draw a ["+period.getLabel()+"]";
	}
	
	@Override
	public void doResolve()
	{
	  Innovation.getViewer().log(player.getName()+" draws a card from pile "+period);
		card=player.getGameModel().drawCardFromPile(period);
		player.addToHand(card);
		Innovation.getViewer().log(player.getName()+" has drawn "+card.getNamePrefixedWithPeriod());
	}	
}
