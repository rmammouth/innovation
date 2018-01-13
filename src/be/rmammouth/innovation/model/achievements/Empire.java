package be.rmammouth.innovation.model.achievements;

import be.rmammouth.innovation.model.*;

public class Empire extends SpecialAchievement
{
  public Empire()
  {
    super("Empire");
  }

  @Override
  public boolean isWon(GameModel model, Player player)
  {
    return false;
  }

}
