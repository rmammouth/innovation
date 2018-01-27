package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.model.*;

public class ChooseCard extends CardMove
{
  private String label;
  
  public ChooseCard(Player player, Card card, String label)
  {
    super(player, card);
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
  public Move cloneMove(GameModel cloneModel)
  {
    return new ChooseCard(cloneModel.getPlayers()[player.getIndex()], card, label);
  }

}
