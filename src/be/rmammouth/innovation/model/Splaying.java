package be.rmammouth.innovation.model;

public enum Splaying
{
  NONE,
  LEFT,
  RIGHT,
  UP;
  
  public ResourceLocation[] getRevealedLocations()
  {
    switch (this)
    {
    case LEFT:
      return new ResourceLocation[]{ResourceLocation.BOTTOM_RIGHT};
    case RIGHT:
      return new ResourceLocation[]{ResourceLocation.TOP_LEFT, ResourceLocation.BOTTOM_LEFT};
    case UP:
      return new ResourceLocation[]{ResourceLocation.BOTTOM_LEFT, ResourceLocation.BOTTOM_CENTER, ResourceLocation.BOTTOM_RIGHT};
    default:
      return new ResourceLocation[]{};
    }
  }
}
