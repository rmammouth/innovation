package be.rmammouth.innovation.model.moves;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class SplayPile extends Move
{
  private Color color;
  private Splaying splaying;
  
  public SplayPile(Player player, Color color, Splaying splaying)
  {
    super(player);
    this.color = color;
    this.splaying = splaying;
  }
  
  @Override
  public String getLabel()
  {
    return "Splay "+color+" pile "+splaying;
  }
  
  public boolean modifiesGameModel()
  {
    return true;
  }

  @Override
  protected void doResolve()
  {
    player.getGameModel().log(player.getName()+" splays his "+color+" pile "+splaying);
    player.getCardsPile(color).setSplaying(splaying);
  }
  
  public static List<Move> getSplayPileMoves(Player player, List<Color> colors, Splaying splaying)
  {
    List<Move> moves=new ArrayList<>();
    for (Color color : colors)
    {
      moves.add(new SplayPile(player, color, splaying));  
    }
    return moves;
  }

  @Override
  public Move cloneMove(GameModel cloneModel)
  {
    return new SplayPile(cloneModel.getPlayers()[player.getIndex()], color, splaying);
  }

}
