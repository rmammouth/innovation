package be.rmammouth.innovation.model;

public abstract class Dogma
{
  protected Resource resource;
  protected Card card;
  
	public Dogma(Resource resource)
	{
		this.resource = resource;
	}

	/**
	 * Actovate the dogma
	 * @param cas
	 * @return True if this triggers the free draw action
	 */
  public abstract boolean activate(CardActivationState cas);
}
