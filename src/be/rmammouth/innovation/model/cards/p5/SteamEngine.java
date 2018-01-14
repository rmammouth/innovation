package be.rmammouth.innovation.model.cards.p5;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class SteamEngine extends Card
{
  public SteamEngine()
  {
    super("SteamEngine", Period.FIVE, Color.YELLOW,
          null,
          Resource.FACTORY, Resource.CROWN, Resource.FACTORY);

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
