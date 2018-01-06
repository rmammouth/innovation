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
    return "Transfer "+card.getNamePrefixedWithPeriod()+" from "+fromLocation.getLabel(player)+" to "+toLocation.getLabel(toPlayer);
  }

  @Override
  public void doResolve()
  {
    Innovation.getViewer().log(player+" transfers "+card.getNamePrefixedWithPeriod()+" from his "+fromLocation.getLabel()+" to "+toLocation.getLabel(toPlayer));
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
}
