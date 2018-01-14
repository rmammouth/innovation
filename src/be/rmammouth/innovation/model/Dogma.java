package be.rmammouth.innovation.model;

public abstract class Dogma
{
  protected Resource resource;
  protected Card card;
  protected int index;
  protected String text;
  
	public Dogma(Resource resource)
	{
		this.resource = resource;
	}

	/**
	 * Activate the dogma
	 * @param cas
	 * @return True if this triggers the free draw action
	 */
  public abstract boolean activate(CardActivationState cas);

  public int getIndex()
  {
    return index;
  }

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public Resource getResource()
  {
    return resource;
  }
}
