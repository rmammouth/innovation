package be.rmammouth.innovation.model;

import java.util.*;

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
  
  public abstract String getDogmaTypeLabel();
  
  public abstract List<Player> getAffectedPlayers(CardActivationStatus cardActivationStatus);
  
  public abstract PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das);
  
  public abstract boolean enablesFreeDraw();
}
