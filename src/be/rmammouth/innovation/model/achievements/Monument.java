package be.rmammouth.innovation.model.achievements;

import be.rmammouth.innovation.model.*;

public class Monument extends SpecialAchievement
{
  public Monument()
  {
    super("Monument");    
  }

  @Override
  public boolean isWon(GameModel model, Player player)
  {
    return false;
  }
}
