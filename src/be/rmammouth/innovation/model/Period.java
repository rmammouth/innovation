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
}
