package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class DrawCard extends Move
{
	private Period period;  //null if best pile available for player
	
	public DrawCard(Player player)
	{
		super(player);
		period=player.getModel().getMaxActivePeriod();
		if (period==null) period=Period.ONE;
	}
	
	public DrawCard(Player player, Period period)
	{
		super(player);
		this.period=period;
	}

	@Override
	public String getLabel()
	{
		return "Draw a ["+period.getLabel()+"]";
	}
	
	@Override
	public void resolve()
	{
		
	}

	@Override
	public String getResolvedLabel()
	{
		return player.getName()+" has drawn a card";
	}
}
