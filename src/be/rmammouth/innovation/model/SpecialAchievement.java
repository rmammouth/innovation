package be.rmammouth.innovation.model;

public abstract class SpecialAchievement implements Dominable
{
  private String name;
  
  public SpecialAchievement(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
  
  public abstract boolean isWon(GameModel model, Player player);

  @Override
  public Dominable cloneCard()
  {
    return this;
  }
}
