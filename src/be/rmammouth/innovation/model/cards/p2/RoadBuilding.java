package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class RoadBuilding extends Card
{
  public RoadBuilding()
  {
    super("RoadBuilding", Period.TWO, Color.RED,
          Resource.TOWER,
          null, Resource.TOWER, Resource.TOWER);

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
