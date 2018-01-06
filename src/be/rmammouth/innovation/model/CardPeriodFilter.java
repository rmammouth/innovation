package be.rmammouth.innovation.model;

public class CardPeriodFilter implements CardFilter
{
  private Period period; 
  
  public CardPeriodFilter(Period period)
  {
    this.period = period;
  }

  @Override
  public boolean isFiltered(Card card)
  {
    return card.getPeriod()!=period;
  }
}
