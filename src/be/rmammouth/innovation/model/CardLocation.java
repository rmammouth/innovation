package be.rmammouth.innovation.model;

public enum CardLocation
{
  HAND,
  BOARD,
  SCORE_PILE;
  
  public String getLabel()
  {
    switch (this)
    {
    case HAND:
      return "hand";
    case BOARD:
      return "board";
    case SCORE_PILE:
      return "score pile";
    }
    return null;
  }
  
  public String getLabel(Player player)
  {
    return player.getName()+"'s "+getLabel();
  }
}
