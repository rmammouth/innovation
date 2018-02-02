package be.rmammouth.innovation.controller.ai;

import org.neuroph.core.data.*;
import org.neuroph.nnet.*;
import org.neuroph.util.*;

public class NeurophTest
{

  public static void main(String[] args) throws InterruptedException
  {
    MultiLayerPerceptron mlp=new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 2, 1);
    mlp.getLearningRule().setMaxIterations(1000);
    mlp.getLearningRule().setLearningRate(0.1);
    mlp.getLearningRule().setBatchMode(true);
    
    mlp.setInput(-1, -1);
    mlp.calculate();
    System.out.println(mlp.getOutput()[0]);
    
    DataSet ds=new DataSet(2, 1);
    ds.add(new DataSetRow(new double[]{-1,  -1}, new double[]{0}));
    ds.add(new DataSetRow(new double[]{-1,  1}, new double[]{-1}));
    ds.add(new DataSetRow(new double[]{1,  -1}, new double[]{1}));
    ds.add(new DataSetRow(new double[]{1,  0.5}, new double[]{1}));
    ds.add(new DataSetRow(new double[]{0.5,  1}, new double[]{-1}));
    ds.add(new DataSetRow(new double[]{0,  0}, new double[]{0}));
    ds.add(new DataSetRow(new double[]{0,  1}, new double[]{-1}));
    ds.add(new DataSetRow(new double[]{1,  0}, new double[]{1}));
    mlp.learn(ds);
  
    mlp.setInput(0.9, -0.5);
    mlp.calculate();
    System.out.println(mlp.getOutput()[0]);
  }
  
  private static void logWeights(Double[] weights)
  {
    for (int i=0;i<weights.length;i++)
    {
      System.out.print(weights[i]+" ");
    }
    System.out.println();
  }

}
