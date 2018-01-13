package be.rmammouth.innovation.model.cards;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.cards.p1.*;
import be.rmammouth.innovation.model.cards.p2.*;
import be.rmammouth.innovation.model.cards.p3.*;

public class Cards
{
  private static Map<String, Card> directory=new HashMap<>();
  
  static
  {
    //period 1
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
  	
  	//period 2
  	register(new Calendar());
  	register(new CanalBuilding());
  	register(new Construction());
  	register(new Currency());  	
  	register(new Fermenting());
  	register(new MapMaking());
  	register(new Mathematics());
  	register(new Monotheism());
  	register(new Philosophy());
  	register(new RoadBuilding());
  	
  	//period 3
  	register(new Engineering());
  	register(new Machinery());
  	register(new Medicine());
  	register(new Optics());
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
