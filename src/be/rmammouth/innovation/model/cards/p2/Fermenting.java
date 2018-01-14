package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Fermenting extends Card
{
  public Fermenting()
  {
    super("Fermenting", Period.TWO, Color.YELLOW,
          Resource.LEAF,
          Resource.LEAF, null, Resource.TOWER);

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
