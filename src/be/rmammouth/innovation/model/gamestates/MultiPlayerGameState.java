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
  
  protected final void doNextStep()
  {
    List<PlayerInteraction> nextInteractions=getNextInteractions();
    List<Move> chosenMoves=new ArrayList<Move>();
    for (PlayerInteraction interaction : nextInteractions)
    {
      GameModelClone gmc=new GameModelClone(interaction.getPlayer(), model, interaction.getAvailableMoves());
      Move cloneMove=interaction.getPlayer().getController().getNextMove(gmc.getModel(), gmc.getMoves());
      Move originalMove=interaction.getAvailableMoves().get(gmc.getMoves().indexOf(cloneMove));
      chosenMoves.add(originalMove);
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
