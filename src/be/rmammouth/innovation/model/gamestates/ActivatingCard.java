package be.rmammouth.innovation.model.gamestates;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class ActivatingCard extends SinglePlayerGameState
{
  private CardActivationStatus cardActivationStatus;
  
  public ActivatingCard(GameModel model, CardActivationStatus cardActivationState)
  {
    super(model);
    this.cardActivationStatus=cardActivationState;
  }

  @Override
  public PlayerInteraction getNextInteraction()
  {
    return cardActivationStatus.getNextInteraction();
  }

  @Override
  public void moveResolved(Move move)
  {
    cardActivationStatus.addResolvedMove(move);
    cardActivationStatus.nextStep();
    PlayerInteraction nextInteraction=cardActivationStatus.getNextInteraction();
    if (nextInteraction==null)
    {
      //card resolution over, new turn or back to previous state
      model.decreaseActionCount();
      model.setCurrentState(new ChoosingAction(model));
    }
  }
}
