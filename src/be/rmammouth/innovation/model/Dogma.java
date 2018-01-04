package be.rmammouth.innovation.model;

public abstract class Dogma
{
  private Resource resource;
  
	public Dogma(Resource resource)
	{
		this.resource = resource;
	}
  
  public abstract DogmaType getType();
  
  public abstract void activate(GameState gs, CardActivationState cas);
  
  public abstract void activateOnPlayer(GameState gs, CardActivationState cas, Player player);
}
