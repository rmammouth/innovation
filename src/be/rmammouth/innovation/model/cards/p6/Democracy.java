package be.rmammouth.innovation.model.cards.p6;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Democracy extends Card
{
  public Democracy()
  {
    super("Democracy", Period.SIX, Color.PURPLE,
          Resource.CROWN,
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
