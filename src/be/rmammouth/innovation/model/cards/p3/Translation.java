package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Translation extends Card
{
  public Translation()
  {
    super("Translation", Period.THREE, Color.BLUE,
          null,
          Resource.CROWN, Resource.CROWN, Resource.CROWN);

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        return false;
      }
    });

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
