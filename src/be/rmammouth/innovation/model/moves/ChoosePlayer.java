package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.model.*;

public class ChoosePlayer extends Move
{
  private Player chosenPlayer;
  private String label;
  
  public ChoosePlayer(Player player, Player chosenPlayer, String label)
  {
    super(player);
    this.chosenPlayer=chosenPlayer;
    this.label=label;
  }
  
  @Override
  public String getLabel()
  {
    return label;
  }

  @Override
  protected void doResolve()
  {
  }

  @Override
  public boolean modifiesGameModel()
  {
    return false;
  }

  public Player getChosenPlayer()
  {
    return chosenPlayer;
  }

  @Override
  public Move cloneMove(GameModel cloneModel)
  {
    return new ChoosePlayer(cloneModel.getPlayers()[player.getIndex()], cloneModel.getPlayers()[chosenPlayer.getIndex()], label);
  }

}
