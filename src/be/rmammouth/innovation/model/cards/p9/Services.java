package be.rmammouth.innovation.model.cards.p9;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Services extends Card
{
  public Services()
  {
    super("Services", Period.NINE, Color.PURPLE,
          null,
          Resource.LEAF, Resource.LEAF, Resource.LEAF);

    addDogma(new SupremacyDogma(Resource.LEAF)
    {
      @Override
      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)
      {
      }
    });
  }
}
