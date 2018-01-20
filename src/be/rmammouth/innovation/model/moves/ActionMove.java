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
   * Return true if action totally completed (or false if further interaction needed)
   * @return
   */
  public boolean isActionCompleted()
  {
    return true;
  }
}
