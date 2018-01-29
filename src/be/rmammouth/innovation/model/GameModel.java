package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.achievements.*;
import be.rmammouth.innovation.model.cards.*;
import be.rmammouth.innovation.model.gamestates.*;
import be.rmammouth.innovation.model.moves.*;
import be.rmammouth.innovation.view.*;

public class GameModel
{
  /**
   * True if this is the main game model, false if it's a copy used for player view or AI.
   */
  private boolean main;
  
  private Player[] players;
  private Map<Period, DrawPile> drawPiles=new EnumMap<>(Period.class);
  private Map<Period, Card> periodAchievements=new EnumMap<>(Period.class);
  private Set<SpecialAchievement> specialAchievements=new HashSet<>();
  
  /**
   * Player playing the current turn (or null if opening)
   */
  private Player currentTurnPlayer=null;
  
  /**
   * Actions left to play during current turn
   */
  private int currentTurnActionsLeft;
  
  /**
   * Player who played the first turn (or null if opening)
   */
  private Player firstPlayer=null;
  
  private GameState currentState;
  
  /**
   * Number of current turn (1 based)
   */
  private int turnNumber;
  
  /**
   * List with winning players (usually one, except if tie).
   * If null, game is not over yet.
   */
  private List<Player> winners;
  
  private VictoryType victoryType;
   
  
  public boolean isMain()
  {
    return main;
  }

  public Player[] getPlayers()
	{
		return players;
	}  
  
  public int getPlayersCount()
  {
    return players.length;
  }

	public Player getCurrentTurnPlayer()
	{
		return currentTurnPlayer;
	}

	public void setCurrentTurn(Player currentTurn)
	{
	//notify controllers that turn is over (for AI learning)
    for (Player player : players)
    {
      player.getController().turnOver();
    }
    
		this.currentTurnPlayer = currentTurn;
		this.currentTurnActionsLeft=getAvailableActionsCount(currentTurnPlayer);
	}

	public Player getFirstPlayer()
	{
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer)
	{
		this.firstPlayer = firstPlayer;
		
		Iterator<Player> itr=getTurnOrderIterator();
		int turnOrderIndex=1;
		while (itr.hasNext())
		{
		  itr.next().setTurnOrderIndex(turnOrderIndex++);
		}
	}
	
	/**
	 * Return an iterator on all the players in turn order
	 * @return
	 */
	public Iterator<Player> getTurnOrderIterator()
	{
	  return new Iterator<Player>()
    {
	    int nextIndex=-1;
	    int playersReturned=0;
	    
      @Override
      public boolean hasNext()
      {
        return playersReturned < players.length;
      }

      @Override
      public Player next()
      {
        if (nextIndex==-1)
        {
          //first call : let's find where is the first player
          nextIndex=getPlayerIndex(firstPlayer);          
        }
        
        Player nextPlayer=players[nextIndex];
        nextIndex=(nextIndex+1)%players.length;
        playersReturned++;
        return nextPlayer;
      }    
    };
	}
	
	/**
	 * Return an iterator on all players (in turn order) except the player passed as a parameter
	 * @param me
	 * @return
	 */
	public Iterator<Player> getOtherPlayersIterator(Player me)
	{
	  return new Iterator<Player>()
    {
      int nextIndex=-1;
      int playersReturned=0;
      
      @Override
      public boolean hasNext()
      {
        return playersReturned < (players.length-1);
      }

      @Override
      public Player next()
      {
        if (nextIndex==-1)
        {
          //first call : let's find where is the first player
          nextIndex=(getPlayerIndex(me)+1)%players.length;          
        }
        
        Player nextPlayer=players[nextIndex];
        nextIndex=(nextIndex+1)%players.length;
        playersReturned++;
        return nextPlayer;
      }    
    };
	}
	
	/**
	 * Return player index in the players table
	 * @return
	 */
	private int getPlayerIndex(Player player)
	{
    for (int i=0;i<players.length;i++)
    {
      if (players[i]==player)
      {
        return i;
      }
    }  
    return -1;
	}

	public int getTurnNumber()
  {
    return turnNumber;
  }
	
	private int getAvailableActionsCount(Player player)
  {
    //count how many actions this player can play
    if (turnNumber>1)
    {
      return 2;
    }
    else
    {
      if (player.getTurnOrderIndex()==1) return 1;
      else if (player.getTurnOrderIndex()==2) return ((getPlayersCount()==4) ? 1 : 2);
      else return 2;
    }
  }
	
	public int getCurrentTurnActionsLeft()
  {
    return currentTurnActionsLeft;
  }

  public void decreaseActionCount()
	{
	  currentTurnActionsLeft--;
	  if (currentTurnActionsLeft==0) nextPlayerTurn();
	}

  public GameState getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState(GameState currentState)
	{
		this.currentState = currentState;
	}
	
	public Map<Player, ResourcesCount> getResourcesCounts()
	{
	  Map<Player, ResourcesCount> counts=new HashMap<>();
	  for (Player player : players)
    {
      counts.put(player, player.getResourcesCount());
    }
	  return counts;
	}

	public void startNewGame(Player[] players)
  {
	  this.main=true;
  	this.players=players;
  	Cards.randomizeGameIds();
  	
  	int index=0;
  	for (Player player : players)
  	{
  	  player.setGameModel(this);
  	  player.setIndex(index++);
  	}
  	
  	for (Period period : Period.values())
    {
      drawPiles.put(period, new DrawPile());
    }
  	
  	//init all cards piles
  	for (Card card : Cards.getAll())
  	{
  		drawPiles.get(card.getPeriod()).add(card);
  	}
  	
  	//shuffle them, and take out the period achievement
  	for (Period period : Period.values())
  	{
  		DrawPile cardPile=drawPiles.get(period);
  		cardPile.shuffle();
  		if ((period!=Period.TEN) && !cardPile.isEmpty())
  		{
  			periodAchievements.put(period, cardPile.draw());
  		}
  	}
  	
  	//special achievements
  	specialAchievements.addAll(Achievements.getAll());
  	
    //notify controllers that game is starting
    for (Player player : players)
    {
      player.getController().gameStarting();
    }
  	
  	//deal first hand
  	for (Player player : players)
  	{
  	  for (int i=0;i<2;i++)
  	  {
    	  Move drawCard=new DrawCard(player);
    	  drawCard.resolve();
  	  }
  	}
  	
  	currentState=new PlayingFirstCard(this);
  	turnNumber=1;
  }
	
	public Card drawCardFromPile(Period period)
	{
	  if (drawPiles.get(period).isEmpty())
	  {
	    if (period==Period.TEN)
	    {
	      log("Can't draw from the last empty pile : game over");
	      gameOverByLastPileEmpty();
	    }
	    return drawCardFromPile(period.next());
	  }
	  else return drawPiles.get(period).draw();
	}

  public void returnCardToPile(Card card)
	{
	  drawPiles.get(card.getPeriod()).returnCard(card);	  
	}
	
	public DrawPile getDrawPile(Period period)
	{
	  return drawPiles.get(period);
	}
  
  public void nextPlayerTurn()
  {
    int i=getPlayerIndex(currentTurnPlayer);
    i=(i+1)%players.length;    
    if (currentTurnPlayer==firstPlayer)
    {
      turnNumber++;
    }    
    setCurrentTurn(players[i]);    
  }
  
  public void removeAchievement(SpecialAchievement achievement)
  {
    specialAchievements.remove(achievement);    
  }
  
  public Card getPeriodAchievement(Period period)
  {
    return periodAchievements.get(period);
  }
  
  public void removePeriodAchievement(Period period)
  {
    periodAchievements.remove(period);
  }
  
  public boolean isPeriodAchievementAvailable(Period period)
  {
    return periodAchievements.containsKey(period);
  }
  
  public boolean isSpecialAchievementAvailable(SpecialAchievement monument)
  {
    return specialAchievements.contains(monument);
  }
  
  public int getDominationsCountNeededToWin()
  {
    return 8-players.length;
  }
  
  public void gameOverByLastPileEmpty() throws GameOverException
  {
    int maxPoints=-1;  //point = (score*10)+domination
    List<Player> winners=new ArrayList<>();
    for (Player player : players)
    {
      int playerPoints=(player.getScorePile().getScore()*10)+player.getDominations().size();
      if (playerPoints>maxPoints)
      {
        winners.clear();
        winners.add(player);
        maxPoints=playerPoints;
      }
      else if (playerPoints==maxPoints)
      {
        winners.add(player);
      }
    }
    throw new GameOverException(winners, VictoryType.BY_SCORE);    
  }
  
  public List<Player> getWinners()
  {
    return winners;
  }
  
  public VictoryType getVictoryType()
  {
    return victoryType;
  }

  public boolean isGameOver()
  {
    return winners!=null;
  }
  
  public void setGameOver(List<Player> winners, VictoryType victoryType)
  {
    this.winners=winners;
    this.victoryType=victoryType;
    
    //notify controllers that game is over (for AI learning)
    for (Player player : players)
    {
      player.getController().gameOver();
    }
  }

  public GameModel cloneForPlayer(Player player)
  {
    GameModel clone=new GameModel();
    
    clone.main=false;
    clone.players=new Player[players.length];
    for (int i=0;i<players.length;i++)
    {
      clone.players[i]=players[i].cloneForPlayer(player);
      clone.players[i].setGameModel(clone);
    }
    
    for (Period period : Period.values())
    {
      clone.drawPiles.put(period, drawPiles.get(period).clonePile());
      if (periodAchievements.get(period)!=null)
      {
        clone.periodAchievements.put(period, periodAchievements.get(period).cloneCard());
      }
    }
        
    clone.specialAchievements.addAll(specialAchievements);
    
    if (currentTurnPlayer!=null)
    {
      clone.currentTurnPlayer=clone.players[currentTurnPlayer.getIndex()];
    }
    
    if (firstPlayer!=null)
    {
      clone.firstPlayer=clone.players[firstPlayer.getIndex()];
    }
    
    clone.turnNumber=turnNumber;
    clone.currentTurnActionsLeft=currentTurnActionsLeft;
    if (currentState!=null)
    {
      clone.currentState=currentState.cloneState(clone);
    }
    
    if (winners!=null)
    {
      for (Player winner : winners)
      {
        clone.winners.add(clone.players[winner.getIndex()]);
      }
    }
    clone.victoryType=victoryType;
    
    return clone;
  }
  
  public void log(String message)
  {
    if (main)
    {
      GameViewer.getViewer().log(message);
    }
  }
  
}
