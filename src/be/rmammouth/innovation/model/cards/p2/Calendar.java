package be.rmammouth.innovation.model.cards.p2;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Calendar extends Card
{
  public Calendar()
  {
    super("Calendar", Period.TWO, Color.BLUE,
      null,
      Resource.LEAF, Resource.LEAF, Resource.BULB);
    
    addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        if (player.getScorePile().getSize()>player.getHand().size())
        {
          for (int n=0;n<2;n++)
          {
            new DrawCard(player, Period.THREE).resolve();            
          }
          return true;
        }
        else return false;
      }
    });
  }
}
