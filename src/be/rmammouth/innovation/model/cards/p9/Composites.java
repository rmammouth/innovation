package be.rmammouth.innovation.model.cards.p9;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Composites extends Card
{
  public Composites()
  {
    super("Composites", Period.NINE, Color.RED,
          Resource.FACTORY,
          Resource.FACTORY, null, Resource.FACTORY);

    addDogma(new SupremacyDogma(Resource.FACTORY)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });
  }
}
