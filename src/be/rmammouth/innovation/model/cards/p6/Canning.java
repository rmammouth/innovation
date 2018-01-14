package be.rmammouth.innovation.model.cards.p6;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Canning extends Card
{
  public Canning()
  {
    super("Canning", Period.SIX, Color.YELLOW,
          null,
          Resource.FACTORY, Resource.LEAF, Resource.FACTORY);

    addDogma(new CooperationDogma(Resource.FACTORY)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });

    addDogma(new CooperationDogma(Resource.FACTORY)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
