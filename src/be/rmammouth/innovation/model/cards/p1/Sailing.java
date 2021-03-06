package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Sailing extends Card
{
  public Sailing()
  {
    super("Sailing", Period.ONE, Color.GREEN,
          Resource.CROWN,
          Resource.CROWN, null, Resource.LEAF);

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.ONE);
        draw.resolve();
        PlayCard play=new PlayCard(das.getAffectedPlayer(), draw.getCard());
        play.resolve();
        return null;
      }
    });
  }
}
