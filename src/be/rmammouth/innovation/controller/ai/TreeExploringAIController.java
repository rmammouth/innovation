package be.rmammouth.innovation.controller.ai;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;
import be.rmammouth.innovation.view.*;

public abstract class TreeExploringAIController extends PlayerController
{
  private Random random=new Random();
  
  public TreeExploringAIController()
  {
    super();
  }

  @Override
  public Move getNextMove(GameModel model, List<? extends Move> availableMoves)
  {
    float bestEval=Float.NEGATIVE_INFINITY;
    List<Move> bestMoves=new ArrayList<>();
    for (Move move : availableMoves)
    {
      GameModel cloneModel=model.cloneForPlayer(player);
      Move cloneMove=move.cloneMove(cloneModel);
      cloneMove.resolve();
   //   cloneModel.getCurrentState().nextStep();
      float eval=evalGameModel(cloneModel);
      GameViewer.getViewer().log(move.getLabel()+" : "+eval);
      if (eval>bestEval)
      {
        bestMoves.clear();
        bestMoves.add(move);
        bestEval=eval;
      }
      else if (eval==bestEval)
      {
        bestMoves.add(move);
      }
    }
    return bestMoves.get(random.nextInt(bestMoves.size()));
  }
  
  public abstract float evalGameModel(GameModel model);
}
