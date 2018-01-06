package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class ActivateCard extends CardMove
{
  public ActivateCard(Player player, Card card)
  {
    super(player,card);
  }

  @Override
  public String getLabel()
  {
    return "Activate "+card.getNamePrefixedWithPeriod();
  }

  @Override
  public void resolve()
  {
    Innovation.getViewer().log(player.getName()+" has activated "+card.getNamePrefixedWithPeriod());
    card.activate(player.getGameModel(), player);
  }

  @Override
  public String getResolvedLabel()
  {
    return player.getName()+" has finished resolving "+card.getNamePrefixedWithPeriod();
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
