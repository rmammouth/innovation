package be.rmammouth.innovation.model.cards.p9;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Suburbia extends Card
{
  public Suburbia()
  {
    super("Suburbia", Period.NINE, Color.YELLOW,
          null,
          Resource.CROWN, Resource.LEAF, Resource.LEAF);

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
