package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.gamestates.*;
import be.rmammouth.innovation.model.moves.*;

public class GameModel
{
  private Player[] players;
  private Map<Period, DrawPile> cardPiles=new EnumMap<>(Period.class);
  private Map<Period, Card> periodAchievements=new EnumMap<>(Period.class);
  private List<SpecialAchievement> specialAchievements=new ArrayList<>();
  
  /**
   * Player playing the current turn (or null if opening)
   */
  private Player currentTurnPlayer=null;
  
  /**
   * Player who played the first turn (or null if opening)
   */
  private Player firstPlayer=null;
  
  private GameState currentState;
  
  /**
   * Number of current turn (1 based)
   */
  private int turnNumber;
  
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
		this.currentTurnPlayer = currentTurn;
		Innovation.getViewer().turnStarted(currentTurnPlayer);
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

  public GameState getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState(GameState currentState)
	{
		this.currentState = currentState;
	}

	public void startNewGame(Player[] players)
  {
  	this.players=players;
  	
  	for (Player player : players)
  	{
  	  player.setGameModel(this);
  	}
  	
  	for (Period period : Period.values())
    {
      cardPiles.put(period, new DrawPile());
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
  	for (Player player : players)
  	{
  	  for (int i=0;i<2;i++)
  	  {
    	  DrawCard drawCard=new DrawCard(player);
    	  drawCard.resolveAndLog();
  	  }
  	}
  	
  	currentState=new PlayingFirstCard(this);
  	turnNumber=1;
  }
	
	public Card drawCardFromPile(Period period)
	{
	  if (cardPiles.get(period).isEmpty())
	  {
	    if (period==Period.TEN)
	    {
	      throw new GameOverException("no more cards left to draw");
	    }
	    return drawCardFromPile(period.next());
	  }
	  else return cardPiles.get(period).draw();
	}
	
	public void returnCardToPile(Card card)
	{
	  cardPiles.get(card.getPeriod()).returnCard(card);	  
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
}
