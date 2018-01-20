package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.gamestates.*;

/**
 * Move that can be played as action as part of a player's turn
 * @author Seb
 *
 */
public abstract class ActionMove extends CardMove
{
  private boolean turnAction;
  
  public ActionMove(Player player, Card card)
  {
    super(player, card);
  }

  @Override
  protected final void doResolve()
  {
    boolean resolutionComplete=doResolveAction();
    if (turnAction && resolutionComplete)
    {
      player.getGameModel().decreaseActionCount();
    }    
  }
  
  
  public void setTurnAction(boolean turnAction)
  {
    this.turnAction = turnAction;
  }

  /**
   * Resolve the action
   * @return True if the action is completely resolved
   */
  protected abstract boolean doResolveAction();
}
