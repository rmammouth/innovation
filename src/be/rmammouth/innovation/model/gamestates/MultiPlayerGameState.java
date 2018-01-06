package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public abstract class MultiPlayerGameState extends GameState
{
  public MultiPlayerGameState(GameModel model)
  {
    super(model);
  }
  
  public final void nextStep()
  {
    List<Player> activePlayers=getActivePlayers();
    List<Move> chosenMoves=new ArrayList<Move>();
    for (Player player : activePlayers)
    {
      List<Move> availableMoves=getAvailableMoves(player);
      Move chosenMove=player.getController().getNextMove(availableMoves);
      chosenMoves.add(chosenMove);
    }
    for (Move chosenMove : chosenMoves)
    {
      chosenMove.resolveAndLog();
    }
    movesResolved(chosenMoves);
  }
  
  public abstract List<Player> getActivePlayers();
  
  public abstract List<Move> getAvailableMoves(Player player);
  
  public abstract void movesResolved(List<Move> moves);

}
