package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

/**
 * Don't do anything (useful for "you may..." dogmas)
 * @author Seb
 *
 */
public class Pass extends Move
{
  public Pass(Player player)
  {
    super(player);
  }

  @Override
  public String getLabel()
  {
    return "Pass";
  }

  @Override
  protected void doResolve()
  {
    Innovation.getViewer().log(player.getName()+" pass");
  }
}
