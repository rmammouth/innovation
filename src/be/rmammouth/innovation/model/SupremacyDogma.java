package be.rmammouth.innovation.model;

public abstract class SupremacyDogma extends Dogma
{  
  public SupremacyDogma(Resource resource)
  {
    super(resource);
  }

  @Override
  public DogmaType getType()
  {
    return DogmaType.SUPREMACY;
  }

  @Override
  public void activate(GameModel gs, CardActivationState cas)
  {
  }

}
