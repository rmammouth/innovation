package be.rmammouth.innovation.model.gamestates;

import java.util.*;

import be.rmammouth.innovation.model.*;

public class GameOverException extends RuntimeException
{
  private List<Player> winners;
  private VictoryType victoryType;
  
  public GameOverException(List<Player> winners, VictoryType victoryType)
  {
    super();
    this.winners = winners;
    this.victoryType = victoryType;
  }

  public List<Player> getWinners()
  {
    return winners;
  }

  public VictoryType getVictoryType()
  {
    return victoryType;
  }
  
  
}
