package be.rmammouth.innovation.model.moves;

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
  public void resolve()
  {
  }

  @Override
  public String getResolvedLabel()
  {
    return player.getName()+" doesn't do anything";
  }

}
