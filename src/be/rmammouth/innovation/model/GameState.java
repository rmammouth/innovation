package be.rmammouth.innovation.model;

import java.util.*;

public class GameState
{
	private final static GameState instance=new GameState();
	
  private Player[] players;
  private Map<Period, List<Card>> cards=new EnumMap<>(Period.class);
  private Map<Period, Card> periodAchievements=new EnumMap<>(Period.class);
  private List<SpecialAchievement> specialAchievements=new ArrayList<>();
  
  private GameState()
  {  	
  }
  
  public static GameState getInstance()
  {
  	return instance;
  }
  
  public void init(int numPlayers)
  {
  	players=new Player[numPlayers];
  	for (int i=0;i<numPlayers;i++)
  	{
  		players[i]=new Player();
  	}
  }
}
