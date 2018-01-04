package be.rmammouth.innovation.model.effects;

import java.util.*;

public class Sequence implements Effect
{
  private List<Effect> effects=new ArrayList<>();
  
  public Sequence(Effect... effects)
  {
  	this.effects.addAll(Arrays.asList(effects));
  }
}
