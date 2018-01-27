package be.rmammouth.innovation.model;

public class World extends SpecialAchievement
{
  public World()
  {
    super("World");
  }

  @Override
  public boolean isWon(GameModel model, Player player)
  {
    return false;
  }

}
