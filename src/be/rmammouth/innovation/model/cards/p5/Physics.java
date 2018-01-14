package be.rmammouth.innovation.model.cards.p5;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Physics extends Card
{
  public Physics()
  {
    super("Physics", Period.FIVE, Color.BLUE,
          Resource.FACTORY,
          Resource.BULB, Resource.BULB, null);

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
