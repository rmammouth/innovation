package be.rmammouth.innovation.model;

public abstract class Dogma
{
  protected Resource resource;
  protected Card card;
  
	public Dogma(Resource resource)
	{
		this.resource = resource;
	}

  public abstract void activate(CardActivationState cas);
}
