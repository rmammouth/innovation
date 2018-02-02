package be.rmammouth.innovation;

import be.rmammouth.innovation.controller.ai.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.view.*;

public class SimulateGames
{
  public static void main(String[] args)
  { 
    int[] wins=new int[]{0,0};
    
    for (int i=0;i<1000;i++)
    {
 //     GameViewer.setViewer(new ConsoleViewer());
      GameViewer.setViewer(new NullViewer());
      GameModel model=new GameModel();
  
//      Player p1=new Player("RandomAI", new RandomAIController());
      Player p1=new Player("NeuralNetworkAI", new NeuralNetworkAIController());
    //  Player p2=new Player("SimpleEvalAI", new SimpleEvalAIController());
      Player p2=new Player("RandomAI", new RandomAIController());
  
      Player[] players=new Player[]{p1, p2};
      model.startNewGame(players);
          
      while (!model.isGameOver())
      {
        model.getCurrentState().nextStep();
      }
      
      System.out.println("Game over at turn "+model.getTurnNumber());
      String winners="";
      for (Player winner : model.getWinners())
      {
        if (winners.length()>0) winners+=", ";
        winners+=winner.getName();
        wins[winner.getIndex()]++;
      }
      System.out.println("Winner(s) "+model.getVictoryType()+" : "+winners);
      System.out.println(p1.getName()+" "+wins[0]+"-"+wins[1]+" "+p2.getName());
    }
    NeuralNetworkAIController.saveNetwork();
  }

}
