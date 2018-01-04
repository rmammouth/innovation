package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.gamestates.*;
import be.rmammouth.innovation.model.moves.*;

public class GameModel
{
	private final static GameModel instance=new GameModel();
	
  private PlayerModel[] playerModels;
  private Map<Period, DrawPile> cardPiles=new EnumMap<>(Period.class);
  private Map<Period, Card> periodAchievements=new EnumMap<>(Period.class);
  private List<SpecialAchievement> specialAchievements=new ArrayList<>();
  
  private PlayerModel currentTurn=null;
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
  
  public PlayerModel[] getPlayerModels()
	{
		return playerModels;
	}  

	public PlayerModel getCurrentTurn()
	{
		return currentTurn;
	}

	public void setCurrentTurn(PlayerModel currentTurn)
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
  	playerModels=new PlayerModel[players.length];
  	for (int i=0;i<players.length;i++)
  	{
  		playerModels[i]=players[i].getModel();
  	}
  	
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
  	for (PlayerModel player : playerModels)
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
