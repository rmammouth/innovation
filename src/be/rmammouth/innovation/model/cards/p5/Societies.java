package be.rmammouth.innovation.model.cards.p5;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Societies extends Card
{
  public Societies()
  {
    super("Societies", Period.FIVE, Color.PURPLE,
          Resource.CROWN,
          null, Resource.BULB, Resource.CROWN);

    addDogma(new SupremacyDogma(Resource.CROWN)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });
  }
}
