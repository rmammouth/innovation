package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class ChoosingAction extends SinglePlayerGameState
{
	private Player player;
	private int actionsLeft;
	
	public ChoosingAction(GameModel model)
	{
		super(model);
		player=model.getCurrentTurnPlayer();
		actionsLeft=getAvailableActionsCount();
	}

	@Override
	public PlayerInteraction getNextInteraction()
	{
	  Innovation.getViewManager().log(player.getName()+" has "+actionsLeft+" action(s) left to play");
	  
		List<ActionMove> moves=new ArrayList<>();
		moves.add(new DrawCard(player));
		moves.addAll(PlayCard.getAllPlayableCardMoves(player));
		moves.addAll(ActivateCard.getAllActivableCardMoves(player));
		moves.addAll(DominatePeriod.getAllDominablePeriodMoves(player));
		return new PlayerInteraction(player, new ArrayList<Move>(moves));
	}

	@Override
	public void moveResolved(Move move)
	{
	  actionsLeft--;
	  ActionMove actionMove=(ActionMove)move;
	  GameState newGameState=actionMove.getNewGameState();
	  if (newGameState==null)
	  {
	    //action completely resolved  		
  		if (actionsLeft==0)
  		{
  		  model.nextPlayerTurn();
  		  model.setCurrentState(new ChoosingAction(model));
  		}
	  }
	  else
	  {
	    //some player interaction is needed, action is still being resolved
	    newGameState.setPreviousState(this);
	    model.setCurrentState(newGameState);
	  }
	}
	
	public int getActionsLeft()
  {
    return actionsLeft;
  }

  private int getAvailableActionsCount()
  {
    //count how many actions this player can play
    if (model.getTurnNumber()>1)
    {
      return 2;
    }
    else
    {
      if (player.getTurnOrderIndex()==1) return 1;
      else if (player.getTurnOrderIndex()==2) return ((model.getPlayersCount()==4) ? 1 : 2);
      else return 2;
    }
  }

}
