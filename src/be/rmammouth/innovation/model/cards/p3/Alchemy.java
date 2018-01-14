package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Alchemy extends Card
{
  public Alchemy()
  {
    super("Alchemy", Period.THREE, Color.BLUE,
          null,
          Resource.LEAF, Resource.TOWER, Resource.TOWER);

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
