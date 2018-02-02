package be.rmammouth.innovation.controller.ai;

import java.io.*;
import java.util.*;

import org.neuroph.core.*;
import org.neuroph.core.data.*;
import org.neuroph.core.learning.*;
import org.neuroph.nnet.*;
import org.neuroph.nnet.learning.*;
import org.neuroph.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.achievements.*;
import be.rmammouth.innovation.model.cards.*;

/**
 * Input data :
 * 1->105 : card in hand (-1,1)
 * 106->210 : card on board (-1,1) 
 * @author Seb
 *
 */
public class NeuralNetworkAIController extends TreeExploringAIController
{
  private static NeuralNetwork mlp;
  
  private DataSet dataSet;
  
  static
  {
    File file=getFile();
    if (file.exists())
    {
      mlp=NeuralNetwork.createFromFile(getFile());
    }
    else
    {
      mlp=new MultiLayerPerceptron(TransferFunctionType.TANH, 8, 1);
      mlp.setLearningRule(new TDLearningRule(0.7));
      ((IterativeLearning)mlp.getLearningRule()).setMaxIterations(1);
      ((IterativeLearning)mlp.getLearningRule()).setLearningRate(0.1);
      ((SupervisedLearning)mlp.getLearningRule()).setBatchMode(true);
   /*   MomentumBackpropagation lr=new MomentumBackpropagation();
     lr.setLearningRate(0.5);
      lr.setMaxIterations(1);
      mlp.setLearningRule(lr);*/
    }
  }
  
  public NeuralNetworkAIController()
  {
    dataSet=new DataSet(mlp.getInputsCount(), mlp.getOutputsCount());
  }
  
  @Override
  public void gameStarting()
  {
    dataSet.clear();
 //   double[] output=new double[player.getGameModel().getPlayers().length];
   // for (int i=0;i<output.length;i++) output[i]=1.0/output.length;
    double[] output=new double[]{0.5};
    teachNetwork(player.getGameModel(), output, true);
  }

  @Override
  public void turnOver()
  {
    teachNetwork(player.getGameModel(), null, true);
  }

  @Override
  public void gameOver()
  {
    double[] output=getGameOverData(player.getGameModel());
    teachNetwork(player.getGameModel(), output, true);    
  }
  
  private void teachNetwork(GameModel model, double[] expectedOutput, boolean learn)
  {
    double[] input=getNetworkInput(model);    
    if (expectedOutput==null) expectedOutput=mlp.getOutput();

    DataSetRow row=new DataSetRow(input, expectedOutput);
    dataSet.add(0, row);
    if (learn) mlp.learn(dataSet);
  }  
  
  @Override
  public float evalGameModel(GameModel model)
  {    
    double[] input=getNetworkInput(model);
    mlp.setInput(input);
    mlp.calculate();
    double[] output=mlp.getOutput();
    return (float)output[0];
  /*  double sum=0;
    for (int i=0;i<output.length;i++) sum+=output[i];
    return (float)(output[0]/sum);*/
  }
  
  private double[] getNetworkInput(GameModel model)
  {
    double[] input=new double[mlp.getInputsCount()];
    int p=0;
    Map<Player, ResourcesCount> resourceCounts=model.getResourcesCounts();    

    //game over?
/*    input[p++]=normalize(model.isGameOver());
    
    //period domination available
    for (Period period : Period.values())
    {
      if (period!=Period.TEN)
      {
        input[p++]=normalize(model.isPeriodAchievementAvailable(period));
      }
    }
    
    //special achievement available
    for (SpecialAchievement sa : Achievements.getAll())
    {
      input[p++]=normalize(model.isSpecialAchievementAvailable(sa));
    }*/
    
    //this player
    Player playerModel=model.getPlayers()[player.getIndex()];
    p=fillWithPlayer(input, p, playerModel, resourceCounts);
    
    //opponents
    Iterator<Player> opponents=playerModel.getGameModel().getOtherPlayersIterator(playerModel);
    while (opponents.hasNext())
    {
      p=fillWithOpponent(input, p, opponents.next());
    }
    
    if (p!=mlp.getInputsCount()) throw new IllegalArgumentException("Incorrect inputs count for NN : "+p+"!="+mlp.getInputsCount());
    
    return input;
  }

  private int fillWithPlayer(double[] input, int p, Player player, Map<Player, ResourcesCount> resourceCounts)
  {
 /*   List<Card> activeCards=player.getActiveCardsOnBoard();
    for (Card card : Cards.getAll())
    {
      input[p++]=normalize(player.getHand().contains(card));
      input[p++]=normalize(activeCards.contains(card));
      input[p++]=normalize(player.getScorePile().isCardPresent(card));
    }*/
    input[p++]=normalize(player.getDominations().size(), 0,  player.getGameModel().getDominationsCountNeededToWin());
    input[p++]=normalize(player.getScorePile().getScore(), 0, 50.0);
    input[p++]=normalize(player.getHighestPeriod(CardLocation.HAND));
    input[p++]=normalize(player.getHighestPeriod(CardLocation.BOARD));
  /*  
    for (Resource resource : Resource.values())
    {
      int bestCount=0;
      for (Player playerCount : player.getGameModel().getPlayers())
      {
        if (resourceCounts.get(playerCount).getCount(resource)>bestCount)
        {
          bestCount=resourceCounts.get(playerCount).getCount(resource);
        }
      }
      input[p++]=normalize(resourceCounts.get(player).getCount(resource), 0, bestCount); 
      
      Iterator<Player> opponents=player.getGameModel().getOtherPlayersIterator(player);
      while (opponents.hasNext())
      {
        Player opponent=opponents.next();
        input[p++]=normalize(resourceCounts.get(player).getCount(resource) > resourceCounts.get(opponent).getCount(resource));  //supremacy dogma applies
        input[p++]=normalize(resourceCounts.get(player).getCount(resource) <= resourceCounts.get(opponent).getCount(resource));  //benefits from coop dogma        
      }
    }    */
    return p;
  }  
  
  private int fillWithOpponent(double[] input, int p, Player player)
  {
  /*  List<Card> activeCards=player.getActiveCardsOnBoard();
    for (Card card : Cards.getAll())
    {
      input[p++]=normalize(activeCards.contains(card));
    }*/
    input[p++]=normalize(player.getDominations().size(), 0,  player.getGameModel().getDominationsCountNeededToWin());
    input[p++]=normalize(player.getScorePile().getScore(), 0, 50.0);
    input[p++]=normalize(player.getHighestPeriod(CardLocation.HAND));
    input[p++]=normalize(player.getHighestPeriod(CardLocation.BOARD));
    
    return p;
  }
  
  private double[] getGameOverData(GameModel model)
  {
 /*   double[] output=new double[player.getGameModel().getPlayers().length];
    List<Player> winners=model.getWinners();
    if (winners.contains(player)) output[0]=1.0/winners.size();
    else output[0]=0;
    
    Iterator<Player> itrOpponents=model.getOtherPlayersIterator(player);
    int idx=1;
    while (itrOpponents.hasNext())
    {
      Player opponent=itrOpponents.next();
      if (winners.contains(opponent)) output[idx]=1.0/winners.size();
      else output[idx]=0;
      idx++;
    }  
    return output;*/
    
    List<Player> winners=model.getWinners();
    if (winners.contains(player)) return new double[]{1};
    else return new double[]{0};
  }
  
  static private File getFile()
  {
    return new File("data/nnet/eval2.nnet");
  }
  
  public static void saveNetwork()
  {
    mlp.save(getFile().getAbsolutePath());
  }
  
  static private double normalize(boolean b)
  {
    return b ? 1 : -1;
  }
  
  static private double normalize(Period p)
  {
    if (p==null) return -1;
    else return normalize(p.asInt(), 0, 10);
  }
  
  static private double normalize(double v, double min, double max)
  {
    double range=max-min;
    if (range==0) return 0;
    else return ((v/range)*2)-1;
  }
}
