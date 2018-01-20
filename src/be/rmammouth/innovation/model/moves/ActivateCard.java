package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.gamestates.*;

public class ActivateCard extends ActionMove
{
  private CardActivationStatus cardActivationStatus;
  
  public ActivateCard(Player player, Card card)
  {
    super(player,card);
  }

  @Override
  public String getLabel()
  {
    return "Activate "+card.getLabelPrefixedWithPeriod();
  }

  @Override
  protected void doResolve()
  {    
    cardActivationStatus=card.activate(player);
  }
  
  @Override
  public GameState getNewGameState()
  {
    PlayerInteraction playerInteraction=cardActivationStatus.getNextInteraction();
    if (playerInteraction==null)
    {
      //card activation completely resolved (no player interaction needed)      
      return null;
    }
    else
    {
      //player interaction needed
      return new ActivatingCard(player.getGameModel(),cardActivationStatus);
    }
  }

  public static List<ActivateCard> getAllActivableCardMoves(Player player)
  {
    List<ActivateCard> moves=new ArrayList<>();
    List<Card> topCards=player.getActiveCardsOnBoard();
    for (Card card : topCards)
    {
      moves.add(new ActivateCard(player, card));  
    }
    return moves;
  }

}
