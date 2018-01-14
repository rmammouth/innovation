package be.rmammouth.innovation.model.cards.p6;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class MachineTools extends Card
{
  public MachineTools()
  {
    super("MachineTools", Period.SIX, Color.RED,
          Resource.FACTORY,
          Resource.FACTORY, null, Resource.FACTORY);

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
