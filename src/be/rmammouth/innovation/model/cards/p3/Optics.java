package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Optics extends Card
{
  public Optics()
  {
    super("Optics", Period.THREE, Color.RED,
          Resource.CROWN,
          Resource.CROWN, Resource.CROWN, null);

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });
  }
}
