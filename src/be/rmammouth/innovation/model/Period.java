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
  
  public static Period fromInt(int n)
  {
    return values()[n-1];
  }
	
  public int asInt()
  {
    return ordinal()+1;
  }
  
	public String asString()
	{
		return Integer.toString(asInt());
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
