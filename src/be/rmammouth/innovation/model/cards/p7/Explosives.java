package be.rmammouth.innovation.model.cards.p7;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Explosives extends Card
{
  public Explosives()
  {
    super("Explosives", Period.SEVEN, Color.RED,
          null,
          Resource.FACTORY, Resource.FACTORY, Resource.FACTORY);

    addDogma(new SupremacyDogma(Resource.FACTORY)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });
  }
}
