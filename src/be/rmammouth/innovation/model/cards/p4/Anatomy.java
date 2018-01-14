package be.rmammouth.innovation.model.cards.p4;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Anatomy extends Card
{
  public Anatomy()
  {
    super("Anatomy", Period.FOUR, Color.YELLOW,
          Resource.LEAF,
          Resource.LEAF, Resource.LEAF, null);

    addDogma(new SupremacyDogma(Resource.LEAF)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });
  }
}
