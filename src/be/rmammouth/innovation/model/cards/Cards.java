package be.rmammouth.innovation.model.cards;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.cards.p1.*;
import be.rmammouth.innovation.model.cards.p10.*;
import be.rmammouth.innovation.model.cards.p2.*;
import be.rmammouth.innovation.model.cards.p2.Calendar;
import be.rmammouth.innovation.model.cards.p2.Currency;
import be.rmammouth.innovation.model.cards.p3.*;
import be.rmammouth.innovation.model.cards.p4.*;
import be.rmammouth.innovation.model.cards.p5.*;
import be.rmammouth.innovation.model.cards.p6.*;
import be.rmammouth.innovation.model.cards.p7.*;
import be.rmammouth.innovation.model.cards.p8.*;
import be.rmammouth.innovation.model.cards.p9.*;
import be.rmammouth.innovation.view.*;

public class Cards
{  
  private static Random random=new Random();
  private static List<Card> list=new ArrayList<>();
  private static Map<Integer, Card> directoryByGameId=new HashMap<>();
  private static Map<String, Card> directoryByName=new HashMap<>();
  
  static
  {
    register(new Agriculture());
    register(new Archery());
    register(new Clothing());
    register(new CodeOfLaws());
    register(new CityStates());
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
    register(new Calendar());
    register(new CanalBuilding());
    register(new Currency());
    register(new Construction());
    register(new Fermenting());
    register(new Mapmaking());
    register(new Mathematics());
    register(new Monotheism());
    register(new Philosophy());
    register(new RoadBuilding());
    register(new Alchemy());
    register(new Education());
    register(new Compass());
    register(new Engineering());
    register(new Feudalism());
    register(new Machinery());
    register(new Medicine());
    register(new Optics());
    register(new Paper());
    register(new Translation());
    register(new Anatomy());
    register(new Colonialism());
    register(new Enterprise());
    register(new Experimentation());
    register(new Gunpowder());
    register(new Invention());
    register(new Navigation());
    register(new Perspective());
    register(new PrintingPress());
    register(new Reformation());
    register(new Astronomy());
    register(new Banking());
    register(new Chemistry());
    register(new Coal());
    register(new Measurement());
    register(new Physics());
    register(new Societies());
    register(new Statistics());
    register(new SteamEngine());
    register(new ThePirateCode());
    register(new AtomicTheory());
    register(new Canning());
    register(new Classification());
    register(new Democracy());
    register(new Emancipation());
    register(new Encyclopedia());
    register(new Industrialization());
    register(new MachineTools());
    register(new MetricSystem());
    register(new Vaccination());
    register(new Bicycle());
    register(new Combustion());
    register(new Electricity());
    register(new Evolution());
    register(new Explosives());
    register(new Lighting());
    register(new Publications());
    register(new Railroad());
    register(new Refrigeration());
    register(new Sanitation());
    register(new Antibiotics());
    register(new Corporations());
    register(new Empiricism());
    register(new Flight());
    register(new MassMedia());
    register(new Mobility());
    register(new QuantumTheory());
    register(new Rocketry());
    register(new Skyscrapers());
    register(new Socialism());
    register(new Collaboration());
    register(new Composites());
    register(new Computers());
    register(new Ecology());
    register(new Fission());
    register(new Genetics());
    register(new Satellites());
    register(new Services());
    register(new Specialization());
    register(new Suburbia());
    register(new AI());
    register(new Bioengineering());
    register(new Databases());
    register(new Globalization());
    register(new Miniaturization());
    register(new Robotics());
    register(new SelfService());
    register(new Software());
    register(new StemCells());
    register(new TheInternet());
    
    I18N.loadLanguage("EN");
  }
  
  private static void register(Card card)
  {
    list.add(card);
  	directoryByName.put(card.getName(), card);
  }
  
  public static void randomizeGameIds()
  {
    directoryByGameId.clear();
    for (Card card : directoryByName.values())
    {
      int gameId;
      do
      {
        gameId=random.nextInt();
      }
      while (directoryByGameId.get(gameId)!=null);
      directoryByGameId.put(gameId, card);
      card.setGameId(gameId);
    }
  }
  
  public static Card getByGameId(int id)
  {
    return directoryByGameId.get(id);
  }
  
  public static Card get(String name)
  {
  	return directoryByName.get(name);
  }
  
  public static List<Card> getAll()
  {
  	return Collections.unmodifiableList(list);
  }
}
