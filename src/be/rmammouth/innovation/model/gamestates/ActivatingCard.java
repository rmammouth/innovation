package be.rmammouth.innovation.model.gamestates;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class ActivatingCard extends SinglePlayerGameState
{
  private CardActivationStatus cardActivationState;
  
  public ActivatingCard(GameModel model, CardActivationStatus cardActivationState)
  {
    super(model);
    this.cardActivationState=cardActivationState;
  }

  @Override
  public PlayerInteraction getNextInteraction()
  {
    return cardActivationState.getNextInteraction();
  }

  @Override
  public void moveResolved(Move move)
  {
    cardActivationState.nextStep();
    PlayerInteraction nextInteraction=cardActivationState.getNextInteraction();
    if (nextInteraction==null)
    {
      //card resolution over, new turn or back to previous state
      int actionsLeft=((ChoosingAction)getPreviousState()).getActionsLeft();
      if (actionsLeft==0)
      {
        model.nextPlayerTurn();
        model.setCurrentState(new ChoosingAction(model));
      }
      else model.setCurrentState(getPreviousState());
    }
  }

}
