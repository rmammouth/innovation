package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.*;
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
  protected void doResolve()
  {
    Innovation.getViewer().log(player.getName()+" scores "+card.getNamePrefixedWithPeriod());
    player.removeFromHand(card);
    player.addToScorePile(card);
  }
}
