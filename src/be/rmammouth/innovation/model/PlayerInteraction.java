package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.model.moves.*;

public class PlayerInteraction
{
  private Player player;
  private List<Move> possibleMoves;
  
  public PlayerInteraction(Player player, List<Move> possibleMoves)
  {
    super();
    this.player = player;
    this.possibleMoves = possibleMoves;
  }
  
  public PlayerInteraction(Player player, Move... possibleMoves)
  {
    super();
    this.player = player;
    this.possibleMoves = Arrays.asList(possibleMoves);
  }

  public Player getPlayer()
  {
    return player;
  }

  public List<Move> getAvailableMoves()
  {
    return possibleMoves;
  }  
}
