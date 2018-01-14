package be.rmammouth.innovation.model.cards.p10;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Robotics extends Card
{
  public Robotics()
  {
    super("Robotics", Period.TEN, Color.RED,
          null,
          Resource.FACTORY, Resource.CLOCK, Resource.FACTORY);

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
