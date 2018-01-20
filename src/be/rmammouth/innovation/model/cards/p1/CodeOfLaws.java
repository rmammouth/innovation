package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class CodeOfLaws extends Card
{
  public CodeOfLaws()
  {
    super("CodeOfLaws", Period.ONE, Color.PURPLE,
          null,
          Resource.CROWN, Resource.CROWN, Resource.LEAF);

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        return null;
      }
    });
  }
}
