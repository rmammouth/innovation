package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.model.cards.*;

public class Cards
{
  private static Map<String, Card> directory=new HashMap<>();
  
  static
  {
  	register(new Agriculture());
  	register(new Archery());
  	register(new Clothing());
  	register(new CityStates());
  	register(new CodeOfLaws());
  	register(new Domestication());
  	register(new Masonry());
  	register(new Metalworking());
  	register(new Mysticism());
  	register(new Oars());
  	register(new Pottery());
  	register(new Sailing());
  	register(new TheWheel());
  	register(new Tools());
  	register(new Writing());
  }
  
  private static void register(Card card)
  {
  	directory.put(card.getName(), card);
  }
  
  public static Card get(String name)
  {
  	return directory.get(name);
  }
  
  public static Collection<Card> getAll()
  {
  	return directory.values();
  }
}
