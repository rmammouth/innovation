package be.rmammouth.innovation.model;

import java.util.*;

public abstract class Card extends PeriodCard
{
  private String name;
  private Color color;
  private Map<ResourceLocation, Resource> resources=new EnumMap<>(ResourceLocation.class);
  protected List<Dogma> dogmas=new ArrayList<>();
  
  protected Card(String name, Period period, Color color, Resource topLeft, Resource bottomLeft, Resource bottomCentre, Resource bottomRight)
  {
    super(period);
  	this.name=name;
  	this.color=color;
  	resources.put(ResourceLocation.TOP_LEFT, topLeft);
  	resources.put(ResourceLocation.BOTTOM_LEFT, bottomLeft);
  	resources.put(ResourceLocation.BOTTOM_CENTER, bottomCentre);
  	resources.put(ResourceLocation.BOTTOM_RIGHT, bottomRight);
  }
  
  public void activate(GameState gs)
  {
    CardActivationState cas=buildActivationState();
    for (Dogma dogma : dogmas)
    {
      dogma.activate(gs, cas);
    }
  }
  
  protected CardActivationState buildActivationState()
  {
    return null;
  }
}
