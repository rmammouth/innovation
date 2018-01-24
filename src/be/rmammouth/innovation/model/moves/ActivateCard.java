package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.gamestates.*;

public class ActivateCard extends ActionMove
{
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
  protected boolean doResolveAction()
  {
    player.getGameModel().log(player.getName()+" activates "+card.getLabelPrefixedWithPeriod());
    CardActivationStatus cardActivationStatus=card.activate(player);
    PlayerInteraction playerInteraction=cardActivationStatus.getNextInteraction();
    if (playerInteraction==null)
    {
      return true;      
    }
    else
    {
      player.getGameModel().setCurrentState(new ActivatingCard(player.getGameModel(), cardActivationStatus));
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

  @Override
  public Move cloneMove(GameModel cloneModel)
  {
    return new ActivateCard(cloneModel.getPlayers()[player.getIndex()], card);
  }
}
