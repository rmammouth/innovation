package be.rmammouth.innovation.model;

import java.util.*;

public class Card implements Achievement
{
  private String name;
  private Period period;
  private Color color;
  private EnumMap<ResourceLocation, Resource> resources=new EnumMap<>(ResourceLocation.class);
  protected List<Dogma> dogmas=new ArrayList<>();
  
  protected Card(String name, Period period, Color color, Resource topLeft, Resource bottomLeft, Resource bottomCentre, Resource bottomRight)
  {
  	this.name=name;
  	this.period=period;
  	this.color=color;
  	resources.put(ResourceLocation.TOP_LEFT, topLeft);
  	resources.put(ResourceLocation.BOTTOM_LEFT, bottomLeft);
  	resources.put(ResourceLocation.BOTTOM_CENTER, bottomCentre);
  	resources.put(ResourceLocation.BOTTOM_RIGHT, bottomRight);
  }
}
