package be.rmammouth.innovation.model.cards.p8;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Socialism extends Card
{
  public Socialism()
  {
    super("Socialism", Period.EIGHT, Color.PURPLE,
          Resource.LEAF,
          null, Resource.LEAF, Resource.LEAF);

    addDogma(new CooperationDogma(Resource.LEAF)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
