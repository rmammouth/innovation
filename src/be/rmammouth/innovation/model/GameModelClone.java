package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.model.moves.*;

/**
 * The game model as viewed by a specific player, as well as the moves (s)he's allowed to play
 * @author Seb
 *
 */
public class GameModelClone
{
  private Player player;
  private GameModel model;
  private List<Move> moves;
  
  public GameModelClone(Player mainPlayer, GameModel mainModel, List<Move> mainMoves)
  {
    model=mainModel.cloneForPlayer(mainPlayer);
    player=model.getPlayers()[mainPlayer.getIndex()];
    moves=new ArrayList<>();
    for (Move mainMove : mainMoves)
    {
      moves.add(mainMove.cloneMove(model));
    }    
  }

  public Player getPlayer()
  {
    return player;
  }

  public GameModel getModel()
  {
    return model;
  }

  public List<Move> getMoves()
  {
    return moves;
  }
}
