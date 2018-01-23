package be.rmammouth.innovation.controller.ai;

import java.util.*;

import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public abstract class TreeExploringAIController extends PlayerController
{
  public TreeExploringAIController()
  {
    super();
  }

  @Override
  public Move getNextMove(GameModel model, List<? extends Move> availableMoves)
  {
    float bestEval=Float.NEGATIVE_INFINITY;
    Move bestMove=null;
    for (Move move : availableMoves)
    {
      GameModel cloneModel=model.cloneForPlayer(player);
      Move cloneMove=move.cloneMove(cloneModel);
      cloneMove.resolve();
   //   cloneModel.getCurrentState().nextStep();
      float eval=evalGameModel(cloneModel);
      if (eval>=bestEval)
      {
        bestMove=move;
        bestEval=eval;
      }
    }
    return bestMove;
  }
  
  public abstract float evalGameModel(GameModel model);
}
