package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.model.gamestates.*;

public class Player
{
  private String name;
  private GameModel gameModel;
  private PlayerController controller; 
  private List<Dominable> dominations=new ArrayList<>();
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
  
  public void dominate(SpecialAchievement achievement)
  {
    gameModel.removeAchievement(achievement);
    dominations.add(achievement);
    checkForDominationsWin();
  }
  
  public void dominate(PeriodCard card)
  {
    gameModel.removePeriodAchievement(card.getPeriod());
    dominations.add(card);
    checkForDominationsWin();
  }
  
  private void checkForDominationsWin()
  {
    if (dominations.size()>=gameModel.getDominationsCountNeededToWin())
    {
      throw new GameOverException(name+" has won with "+dominations.size()+" dominations");
    }
  }
  
  public List<Dominable> getDominations()
  {
    return Collections.unmodifiableList(dominations);
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
  
  public List<Color> getSplayableColors(Splaying splaying)
  {
    List<Color> colors=new ArrayList<>();
    for (Color color : Color.values())
    {
      CardsPile pile=getCardsPile(color);
      if (pile.isSplayable(splaying))
      {
        colors.add(color);
      }
    }
    return colors;
  }
  
  public List<Card> getFilteredHand(CardFilter filter)
  {
    return getFilteredCards(CardLocation.HAND, filter);
  }
  
  public List<Card> getFilteredCards(CardLocation location, CardFilter filter)
  {
    List<Card> filteredCards=new ArrayList<>();
    List<Card> cards=getCards(location);
    for (Card card : cards)
    {
      if (!filter.isFiltered(card))
      {
        filteredCards.add(card);
      }
    }
    return filteredCards;
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
  
  public Period getHighestPeriod(CardLocation location)
  {
    return PeriodCard.getHighestPeriod(getCards(location));
  }
  
  public Period getLowestPeriod(CardLocation location)
  {
    return PeriodCard.getLowestPeriod(getCards(location));
  }
  
  public List<Card> getCards(CardLocation location)
  {
    switch (location)
    {
    case BOARD:
      return getActiveCardsOnBoard();
    case HAND:
      return getHand();
    case SCORE_PILE:
      return getScorePile().getCards();
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
      getCardsPile(card.getColor()).addCardOnTop(card);
    case SCORE_PILE:
      getScorePile().addCard(card);
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
      getCardsPile(card.getColor()).remove(card);
    case SCORE_PILE:
      getScorePile().removeCard(card);
      break;
    }    
  }

  @Override
  public String toString()
  {
    return getName();
  }
}
