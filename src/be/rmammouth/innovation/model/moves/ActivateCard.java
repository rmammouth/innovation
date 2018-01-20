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
    PlayerInteraction playerInteraction=cardActivationStatus.getNextInteraction();
    if (playerInteraction!=null)
    {
      player.getGameModel().setCurrentState(new ActivatingCard(player.getGameModel(),cardActivationStatus));
    }
  }
  
  @Override
  public boolean isActionCompleted()
  {
    PlayerInteraction playerInteraction=cardActivationStatus.getNextInteraction();
    if (playerInteraction==null)
    {
      //card activation completely resolved (no further player interaction needed)      
      return true;
    }
    else
    {
      //player interaction still needed
      return false;
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
