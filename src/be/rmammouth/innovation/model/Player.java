package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.model.gamestates.*;

public class Player
{
  private int index;
  private String name;
  private GameModel gameModel;
  private PlayerController controller; 
  private List<Dominable> dominations=new ArrayList<>();
  private List<Card> hand=new ArrayList<>();
  private ScorePile scorePile=new ScorePile(this);
  private Map<Color, CardsPile> board=new EnumMap<>(Color.class);
  private int turnOrderIndex;
  
  private Player()
  {    
  }
  
  public Player(String name, PlayerController controller)
	{
		this.name=name;
		this.controller=controller;
		controller.setPlayer(this);
		for (Color color : Color.values())
		{
			board.put(color, new CardsPile());
		}
	}

	public int getIndex()
  {
    return index;
  }
	
	void setIndex(int index)
	{
	  this.index=index;
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
  
  public void dominate(Card card)
  {
    gameModel.removePeriodAchievement(card.getPeriod());
    dominations.add(card);
    checkForDominationsWin();
  }
  
  private void checkForDominationsWin()
  {
    if (dominations.size()>=gameModel.getDominationsCountNeededToWin())
    {
      throw new GameOverException(Collections.singletonList(this), VictoryType.BY_DOMINATION);
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
  
  public boolean hasColorOnBoard(Color color)
  {
    return !board.get(color).isEmpty();
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
    putCardInPlay(card, CardLocation.HAND);
  }
  
  public void putCardInPlay(Card card, CardLocation location)
  {
    if (isCardPresent(card, location))
    {
      removeCard(card, location);
      if (card.getColor()!=null)
      {
        board.get(card.getColor()).addCardOnTop(card);
      }
    }
    else throw new IllegalArgumentException(name+" doesn't have the card "+card.getLabel()+ " in his "+location.getLabel());
    
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
    return Card.getHighestPeriod(getCards(location));
  }
  
  public Period getLowestPeriod(CardLocation location)
  {
    return Card.getLowestPeriod(getCards(location));
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
      break;
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
      break;
    case SCORE_PILE:
      getScorePile().removeCard(card);
      break;
    }    
  }
  
  public boolean isCardPresent(Card card, CardLocation location)
  {
    switch (location)
    {
    case HAND:
      return hand.contains(card);
    case BOARD:
      return getCardsPile(card.getColor()).isCardPresent(card);
    case SCORE_PILE:
      return getScorePile().isCardPresent(card);
    }
    return false;
  }

  @Override
  public String toString()
  {
    return getName();
  }  

  @Override
  public int hashCode()
  {
    return index;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Player) return ((Player)obj).index==index;
    else return false;
  }

  public Player cloneForPlayer(Player player)
  {
    Player clone=new Player();
    clone.index=index;
    clone.name=name;
    if (player.index==index)
    {
      clone.dominations.addAll(dominations);
      clone.hand.addAll(hand);
    }
    else
    {
      for (Dominable domination : dominations)
      {
        clone.dominations.add(domination.cloneCard());
      }
      for (Card card : hand)
      {
        clone.hand.add(card.cloneCard());
      }
    }
    clone.scorePile=scorePile.cloneForPlayer(clone);
    for (Color color : board.keySet())
    {
      clone.board.put(color, board.get(color).clonePile());
    }
    clone.turnOrderIndex=turnOrderIndex;
    
    return clone;
  }
}
