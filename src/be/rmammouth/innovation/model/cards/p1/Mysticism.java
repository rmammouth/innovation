package be.rmammouth.innovation.model.cards.p1;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Mysticism extends Card
{
  public Mysticism()
  {
    super("Mysticism", Period.ONE, Color.PURPLE,
        null,
        Resource.TOWER, Resource.TOWER, Resource.TOWER);
    
    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        DrawCard draw=new DrawCard(player, Period.ONE);
        if (player.getColorsOnBoard().contains(draw.getCard().getColor()))
        {
          new PlayCard(player, draw.getCard()).resolve();
          new DrawCard(player, Period.ONE).resolve();
        }
        return true;
      }
    });
  }
}
