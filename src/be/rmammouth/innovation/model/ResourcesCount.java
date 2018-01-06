package be.rmammouth.innovation.model;

import java.util.*;

public class ResourcesCount
{
  private Map<Resource, Integer> count=new EnumMap<>(Resource.class);
  
  public ResourcesCount()
  {
    for (Resource resource : Resource.values())
    {
      count.put(resource, 0);
    }
  }
  
  public void increment(Resource resource)
  {
    count.put(resource, count.get(resource).intValue() + 1);
  }
  
  public int getCount(Resource resource)
  {
    return count.get(resource);
  }

  @Override
  public String toString()
  {
    String s="";
    for (Resource resource : Resource.values())
    {
      if (s.length()>0) s+=" / ";
      s+=resource.toString()+":"+count.get(resource);
    }
    return s;
  }
  
  
}
