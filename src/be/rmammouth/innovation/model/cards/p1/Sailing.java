package be.rmammouth.innovation.model.cards.p1;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Sailing extends Card
{
  public Sailing()
  {
    super("Sailing", Period.ONE, Color.GREEN,
        Resource.CROWN,
        Resource.CROWN, null, Resource.LEAF);
    
    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        DrawCard draw=new DrawCard(player, Period.ONE);
        draw.resolve();
        PlayCard play=new PlayCard(player, draw.getCard());
        play.resolve();
        return true;
      }
    });
  }
}
