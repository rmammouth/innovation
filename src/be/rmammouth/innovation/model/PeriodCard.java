package be.rmammouth.innovation.model;

import java.util.*;

public class PeriodCard implements Dominable 
{
  protected Period period;
  
  public PeriodCard(Period period)
  {
    this.period=period;
  }

	public Period getPeriod()
	{
		return period;
	}
  
	public static Period getHighestPeriod(List<? extends PeriodCard> cards)
	{
	  Period highest=null;
    for (PeriodCard card : cards)
    {
      if (highest==null) highest=card.getPeriod();
      else
      {
        if (highest.isLower(card.getPeriod()))
        {
          highest=card.getPeriod();
        }
      }
    }
    return highest;
	}
	
	public static Period getLowestPeriod(List<? extends PeriodCard> cards)
	{
	  Period lowest=null;
    for (PeriodCard card : cards)
    {
      if (lowest==null) lowest=card.getPeriod();
      else
      {
        if (lowest.isHigher(card.getPeriod()))
        {
          lowest=card.getPeriod();
        }
      }
    }
    return lowest;
	}
  
}
