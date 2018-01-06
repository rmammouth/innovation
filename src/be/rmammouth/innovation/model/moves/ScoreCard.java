package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.model.*;

public class ScoreCard extends CardMove
{
  public ScoreCard(Player player, Card card)
  {
    super(player, card);
  }

  @Override
  public String getLabel()
  {
    return "Score "+card.getNamePrefixedWithPeriod();
  }

  @Override
  public void resolve()
  {
    player.removeFromHand(card);
    player.addToScorePile(card);
  }

  @Override
  public String getResolvedLabel()
  {
    return player.getName()+" has scored "+card.getNamePrefixedWithPeriod();
  }

}
