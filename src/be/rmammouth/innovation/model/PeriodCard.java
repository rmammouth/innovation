package be.rmammouth.innovation.model;

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
  
  
}
