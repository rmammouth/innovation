package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.controller.*;

public class Player
{
  private String name;
  private GameModel gameModel;
  private PlayerController controller; 
  private List<Achievement> achievements=new ArrayList<>();
  private List<Card> hand=new ArrayList<>();
  private ScorePile scorePile=new ScorePile();
  private Map<Color, CardsPile> board=new EnumMap<>(Color.class);
  private int turnOrderIndex;
  
  public Player(String name, PlayerController controller)
	{
		this.name=name;
		this.controller=controller;
		for (Color color : Color.values())
		{
			board.put(color, new CardsPile());
		}
	}

	public String getName()
	{
    return name;
  }

  public PlayerController getController()
  {
    return controller;
  }

  public GameModel getGameModel()
  {
    return gameModel;
  }

  void setGameModel(GameModel gameModel)
  {
    this.gameModel = gameModel;
  }

  /**
   * Index in turn order (1 based)
   * @return
   */
  public int getTurnOrderIndex()
  {
    return turnOrderIndex;
  }

  public void setTurnOrderIndex(int turnOrderIndex)
  {
    this.turnOrderIndex = turnOrderIndex;
  }

  public void addToHand(Card card)
	{
		hand.add(card);
	}
  
  public void removeFromHand(Card card)
  {
    hand.remove(card);
  }
  
  public void addToScorePile(Card card)
  {
    scorePile.addCard(card);
  }
  
  public ScorePile getScorePile()
  {
    return scorePile;
  }
  
  public List<Card> getHand()
  {
  	return Collections.unmodifiableList(hand);
  }
  
  public CardsPile getCardsPile(Color color)
  {
    return board.get(color);
  }
  
  public List<Card> getFilteredHand(CardFilter filter)
  {
    List<Card> filteredHand=new ArrayList<>();
    for (Card card : hand)
    {
      if (!filter.isFiltered(card))
      {
        filteredHand.add(card);
      }
    }
    return filteredHand;
  }
  
  public Period getHighestPeriodInHand()
  {
    Period highest=null;
    for (Card card : hand)
    {
      if (highest==null) highest=card.getPeriod();
      else
      {
        if (highest.isLower(card.getPeriod()))
        {
          highest=card.getPeriod();
        }
      }
    }
    return highest;
  }
  
  public Period getLowestPeriodInHand()
  {
    Period lowest=null;
    for (Card card : hand)
    {
      if (lowest==null) lowest=card.getPeriod();
      else
      {
        if (lowest.isHigher(card.getPeriod()))
        {
          lowest=card.getPeriod();
        }
      }
    }
    return lowest;
  }
  
  public List<Card> getActiveCardsOnBoard()
  {
    List<Card> topCards=new ArrayList<>();
    for (CardsPile pile : board.values())
    {
      if (!pile.isEmpty())
      {
        topCards.add(pile.getTopCard());
      }
    }
    return topCards;
  }
  
  public Set<Color> getColorsOnBoard()
  {
    Set<Color> colors=new HashSet<>();
    for (Color color : Color.values())
    {
      if (!board.get(color).isEmpty())
      {
        colors.add(color);
      }
    }
    return colors;
  }
  
  public void putCardInPlay(Card card)
  {
    if (hand.remove(card))
    {
      board.get(card.getColor()).addCardOnTop(card);
    }
    else throw new IllegalArgumentException(name+" doesn't have the card "+card.getName()+ " in his hand!");
  }
  
  /**
   * Get max period of cards in play (null if no cards in play)
   * @return
   */
  public Period getHighestActivePeriod()
  {
  	Period maxActivePeriod=null;
  	for (Color color : Color.values())
  	{
  		Card topCard=board.get(color).getTopCard();
  		if (topCard!=null)
  		{
  			if ((maxActivePeriod==null) || (maxActivePeriod.isLower(topCard.getPeriod())))
  			{
  				maxActivePeriod=topCard.getPeriod();
  			}
  		}
  	}
  	return maxActivePeriod;
  }
  
  public ResourcesCount getResourcesCount()
  {
    ResourcesCount count=new ResourcesCount();
    for (CardsPile pile : board.values())
    {
      pile.addResourcesToCount(count);
    }
    return count;
  }

  public void transferCard(Card card, CardLocation fromLocation, Player toPlayer, CardLocation toLocation)
  {
    this.removeCard(card, fromLocation);
    toPlayer.addCard(card, toLocation);
  }
  
  public void returnCard(Card card, CardLocation location)
  {
    this.removeCard(card, location);
    gameModel.returnCardToPile(card);
  }
  
  public List<Card> getCards(CardLocation location)
  {
    switch (location)
    {
    case BOARD:
      throw new UnsupportedOperationException();
    case HAND:
      return hand;
    case SCORE_PILE:
      throw new UnsupportedOperationException();
    }
    return null;
  }

  private void addCard(Card card, CardLocation toLocation)
  {
    switch (toLocation)
    {
    case HAND:
      addToHand(card);
      break;
    case BOARD:
      break;
    case SCORE_PILE:
      break;
    default:
      break;      
    }   
  }

  private void removeCard(Card card, CardLocation fromLocation)
  {
    switch (fromLocation)
    {
    case HAND:
      removeFromHand(card);
      break;
    case BOARD:
      break;
    case SCORE_PILE:
      break;
    default:
      break;      
    }    
  }

  @Override
  public String toString()
  {
    return getName();
  }
}
