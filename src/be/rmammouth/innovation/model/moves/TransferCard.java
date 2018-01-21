package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

/**
 * Transfer card from a player to another one
 * @author Seb
 *
 */
public class TransferCard extends CardMove
{
  private Player toPlayer;
  private CardLocation fromLocation;
  private CardLocation toLocation;
  
  public TransferCard(Card card, Player fromPlayer, CardLocation fromLocation, Player toPlayer, CardLocation toLocation)
  {
    super(fromPlayer,card);
    this.toPlayer=toPlayer;
    this.fromLocation=fromLocation;
    this.toLocation=toLocation;
  }

  @Override
  public String getLabel()
  {
    return "Transfer "+card.getLabelPrefixedWithPeriod()+" from "+fromLocation.getLabel(player)+" to "+toLocation.getLabel(toPlayer);
  }

  @Override
  protected void doResolve()
  {
    Innovation.getViewManager().log(player+" transfers "+card.getLabelPrefixedWithPeriod()+" from his "+fromLocation.getLabel()+" to "+toLocation.getLabel(toPlayer));
    player.transferCard(card, fromLocation, toPlayer, toLocation);
  }

  public static List<Move> getAllTransferCardMoves(List<Card> cards, Player fromPlayer, CardLocation fromLocation, Player toPlayer, CardLocation toLocation)
  {
    List<Move> moves=new ArrayList<>();
    for (Card card : cards)
    {
      moves.add(new TransferCard(card, fromPlayer, fromLocation, toPlayer, toLocation));  
    }
    return moves;
  }

  public Player getToPlayer()
  {
    return toPlayer;
  }

  public CardLocation getFromLocation()
  {
    return fromLocation;
  }

  public CardLocation getToLocation()
  {
    return toLocation;
  }

  @Override
  public Move cloneMove(GameModel cloneModel)
  {
    return new TransferCard(card, cloneModel.getPlayers()[player.getIndex()], fromLocation, cloneModel.getPlayers()[toPlayer.getIndex()], toLocation);
  }
  
  
}
