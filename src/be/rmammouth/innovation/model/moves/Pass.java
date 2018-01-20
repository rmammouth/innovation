package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

/**
 * Don't do anything (useful for "you may..." dogmas)
 * @author Seb
 *
 */
public class Pass extends Option
{
  public Pass(Player player)
  {
    super(player,"Pass",player.getName()+" pass");
  }
  
  public boolean isPass()
  {
    return true;
  }
}
