package be.rmammouth.innovation.model.cards.p7;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Sanitation extends Card
{
  public Sanitation()
  {
    super("Sanitation", Period.SEVEN, Color.YELLOW,
          Resource.LEAF,
          Resource.LEAF, null, Resource.LEAF);

    addDogma(new SupremacyDogma(Resource.LEAF)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });
  }
}
