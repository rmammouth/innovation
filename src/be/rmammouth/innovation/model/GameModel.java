package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.model.gamestates.*;
import be.rmammouth.innovation.model.moves.*;

public class GameModel
{
	private final static GameModel instance=new GameModel();
	
  private Player[] players;
  private Map<Period, DrawPile> cardPiles=new EnumMap<>(Period.class);
  private Map<Period, Card> periodAchievements=new EnumMap<>(Period.class);
  private List<SpecialAchievement> specialAchievements=new ArrayList<>();
  
  private Player currentTurn=null;
  private GameState currentState;
  private int turnNumber;
  
  private GameModel()
  {
  	for (Period period : Period.values())
  	{
  		cardPiles.put(period, new DrawPile());
  	}
  }
  
  public static GameModel getInstance()
  {
  	return instance;
  }
  
  public Player[] getPlayers()
	{
		return players;
	}  

	public Player getCurrentTurn()
	{
		return currentTurn;
	}

	public void setCurrentTurn(Player currentTurn)
	{
		this.currentTurn = currentTurn;
	}

	public GameState getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState(GameState currentState)
	{
		this.currentState = currentState;
	}

	public void init(Player[] players)
  {
  	this.players=players;
  	
  	//init all cards piles
  	for (Card card : Cards.getAll())
  	{
  		cardPiles.get(card.getPeriod()).add(card);
  	}
  	
  	//shuffle them, and take out the period achievement
  	for (Period period : Period.values())
  	{
  		DrawPile cardPile=cardPiles.get(period);
  		cardPile.shuffle();
  		if ((period!=Period.TEN) && !cardPile.isEmpty())
  		{
  			periodAchievements.put(period, cardPile.draw());
  		}
  	}
  	
  	//deal first hand
  	for (Player player : players)
  	{
  		player.addToHand(cardPiles.get(Period.ONE).draw());
  		player.addToHand(cardPiles.get(Period.ONE).draw());
  	}
  	
  	currentState=new PlayFirstCard(this);
  	turnNumber=1;
  }
  
  public Map<Player, List<Move>> getNextPlayers()
  {
  	return currentState.getNextPlayers();
  }
  
  public void movesDone(List<Move> moves)
  {
  	currentState.movesDone(moves);
  }
}
