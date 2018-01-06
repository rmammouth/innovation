package be.rmammouth.innovation.model;

public enum Period
{
  ONE,
  TWO,
  THREE,
  FOUR,
  FIVE,
  SIX,
  SEVEN,
  EIGHT,
  NINE,
  TEN;
	
	public String getLabel()
	{
		return Integer.toString(ordinal()+1);
	}
	
	public Period next()
	{
	  return values()[ordinal()+1];
	}
	
	public boolean isLower(Period other)
	{
	  return this.compareTo(other)<0;
	}
	
	public boolean isHigher(Period other)
  {
    return this.compareTo(other)>0;
  }
}
