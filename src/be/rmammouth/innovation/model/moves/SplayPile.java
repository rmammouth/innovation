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

  @Override
  protected void doResolve()
  {
    Innovation.getViewer().log(player.getName()+" splays his "+color+" pile "+splaying);
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

}
