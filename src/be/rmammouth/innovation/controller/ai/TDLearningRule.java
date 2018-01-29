package be.rmammouth.innovation.controller.ai;

import org.neuroph.core.data.*;
import org.neuroph.nnet.learning.*;

public class TDLearningRule extends BackPropagation
{
  protected double decay;
  protected double originalLearningRate;

  public TDLearningRule(double decay)
  {
    super();
    this.decay = decay;
    this.originalLearningRate=learningRate;
  }

  @Override
  public void setLearningRate(double learningRate)
  {
    super.setLearningRate(learningRate);
    this.originalLearningRate=learningRate;
  }

  @Override
  protected void beforeEpoch()
  {
    super.beforeEpoch();
    learningRate=originalLearningRate;
  }

  @Override
  protected void learnPattern(DataSetRow trainingElement)
  {
    super.learnPattern(trainingElement);
    learningRate*=decay;
  }
  
}
