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
  public ActionMove(Player player, Card card)
  {
    super(player, card);
  }

  /**
   * Get new GameState once this move has been resolved (or null if no change needed).
   * @return
   */
  public GameState getNewGameState()
  {
    return null;
  }
}
