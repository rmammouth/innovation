package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class ChoosingAction extends SinglePlayerGameState
{
	private Player player;
	private int actionsLeft=-1;  //has to be initialized at first call of getAvailableMoves
	
	public ChoosingAction(GameModel model)
	{
		super(model);
		player=model.getCurrentTurnPlayer();
	}

  @Override
	public Player getActivePlayer()
	{
		return player;
	}

	@Override
	public List<Move> getAvailableMoves()
	{
	  if (actionsLeft==-1)
	  {
	    actionsLeft=getAvailableActionsCount();
	  }
	  
		List<Move> moves=new ArrayList<>();
		moves.add(new DrawCard(player));
		moves.addAll(PlayCard.getAllPlayableCardMoves(player));
		moves.addAll(ActivateCard.getAllActivableCardMoves(player));
		moves.addAll(DominatePeriod.getAllDominablePeriodMoves(player));
		return moves;
	}

	@Override
	public void moveResolved(Move move)
	{
		actionsLeft--;
		if (actionsLeft==0)
		{
		  model.nextPlayerTurn();
		  model.setCurrentState(new ChoosingAction(model));
		}
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
