package be.rmammouth.innovation.model;

public abstract class CooperationDogma extends Dogma 
{
  public CooperationDogma(Resource resource)
  {
    super(resource);
  }

  @Override
  public DogmaType getType()
  {
    return DogmaType.COOPERATION;
  }

  @Override
  public void activate(GameState gs, CardActivationState cas)
  {
  }
}
