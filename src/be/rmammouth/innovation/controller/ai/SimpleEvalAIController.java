package be.rmammouth.innovation.controller.ai;

import be.rmammouth.innovation.model.*;

public class SimpleEvalAIController extends TreeExploringAIController
{
  public SimpleEvalAIController()
  {
    super();
  }

  @Override
  public float evalGameModel(GameModel model)
  {
    if (model.isGameOver())
    {
      if (model.getWinners().size()==1) 
      {
        if (model.getWinners().get(0).equals(player)) return Float.MAX_VALUE;
        else return Float.MIN_VALUE;
      }
      else
      {
        if (model.getWinners().contains(player)) return 0;
        else return Float.MIN_VALUE;
      }
    }
    
    float eval=0;
    for (Player playerEval : model.getPlayers())
    {
      if (playerEval.equals(player)) eval+=evalGameModelForPlayer(model, playerEval);
      else eval-=evalGameModelForPlayer(model, playerEval);
    }
    return eval;
  }

  private float evalGameModelForPlayer(GameModel model, Player player)
  {
    int eval=0;
    eval+=player.getDominations().size()*100;
    eval+=player.getScorePile().getScore();
  /*  eval+=periodAsFloat(player.getHighestActivePeriod()) * periodAsFloat(player.getHighestActivePeriod());
    eval+=periodAsFloat(player.getHighestPeriod(CardLocation.HAND));*/
    return eval;
  }
  
  private static float periodAsFloat(Period period)
  {
    if (period==null) return 0;
    else return period.asInt();
  }
}
