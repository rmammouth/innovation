package be.rmammouth.innovation.model.achievements;

import java.util.*;

import be.rmammouth.innovation.model.*;

public class Achievements
{
  private static List<SpecialAchievement> list=new ArrayList<>();
  private static Map<String, SpecialAchievement> directory=new HashMap<>();
  
  static
  { 
    register(new Empire());
    register(new Monument());
    register(new Universe());
    register(new Wonder());
    register(new World());
  }
  
  private static void register(SpecialAchievement specialAchievement)
  {
    list.add(specialAchievement);
    directory.put(specialAchievement.getName(), specialAchievement);
  }
  
  public static SpecialAchievement get(String name)
  {
    return directory.get(name);
  }
  
  public static List<SpecialAchievement> getAll()
  {
    return Collections.unmodifiableList(list);
  }
}
