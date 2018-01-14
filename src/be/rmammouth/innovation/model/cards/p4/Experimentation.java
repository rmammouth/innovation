package be.rmammouth.innovation.model.cards.p4;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Experimentation extends Card
{
  public Experimentation()
  {
    super("Experimentation", Period.FOUR, Color.BLUE,
          null,
          Resource.BULB, Resource.BULB, Resource.BULB);

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
