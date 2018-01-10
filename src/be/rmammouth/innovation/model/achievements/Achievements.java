package be.rmammouth.innovation.model.achievements;

import java.util.*;

import be.rmammouth.innovation.model.*;

public class Achievements
{
  private static Map<String, SpecialAchievement> directory=new HashMap<>();
  
  static
  {   
    register(new Monument());
  }
  
  private static void register(SpecialAchievement specialAchievement)
  {
    directory.put(specialAchievement.getName(), specialAchievement);
  }
  
  public static SpecialAchievement get(String name)
  {
    return directory.get(name);
  }
  
  public static Collection<SpecialAchievement> getAll()
  {
    return directory.values();
  }
}
