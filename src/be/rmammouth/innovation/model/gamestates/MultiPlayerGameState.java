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
    List<PlayerInteraction> nextInteractions=getNextInteractions();
    List<Move> chosenMoves=new ArrayList<Move>();
    for (PlayerInteraction interaction : nextInteractions)
    {
      List<Move> availableMoves=interaction.getAvailableMoves();
      Move chosenMove=interaction.getPlayer().getController().getNextMove(availableMoves);
      chosenMoves.add(chosenMove);
    }
    for (Move chosenMove : chosenMoves)
    {
      chosenMove.resolve();
    }
    movesResolved(chosenMoves);
  }
  
  public abstract List<PlayerInteraction> getNextInteractions();
  
  public abstract void movesResolved(List<Move> moves);

}
