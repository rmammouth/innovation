package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.achievements.*;
import be.rmammouth.innovation.model.moves.*;
import be.rmammouth.innovation.view.*;

public class Construction extends Card
{
  public Construction()
  {
    super("Construction", Period.TWO, Color.RED,
          Resource.TOWER,
          null, Resource.TOWER, Resource.TOWER);

    addDogma(new SupremacyDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getAffectedPlayer().getHand().isEmpty() || (das.getNumberOfResolvedMoves(TransferCard.class)>=2))
        {
          new DrawCard(das.getAffectedPlayer(), Period.TWO).resolve();
          return null;
        }
        else
        {
          List<Move> moves=TransferCard.getAllTransferCardMoves(das.getAffectedPlayer().getHand(), das.getAffectedPlayer(), CardLocation.HAND, cas.getActivatingPlayer(), CardLocation.HAND);
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
      }
    });

    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getAffectedPlayer().getActiveCardsOnBoard().size()==5)
        {
          boolean onlyPlayerWith5TopCards=true;
          Iterator<Player> itr=das.getAffectedPlayer().getGameModel().getOtherPlayersIterator(das.getAffectedPlayer());
          while (itr.hasNext() && onlyPlayerWith5TopCards)
          {
            Player otherPlayer=itr.next();
            if (otherPlayer.getActiveCardsOnBoard().size()==5)
            {
              onlyPlayerWith5TopCards=false;
            }
          }
          if (onlyPlayerWith5TopCards)
          {
            GameViewer.getViewer().log(das.getAffectedPlayer().getName()+" is the only one with 5 colors on board and dominates the Empire achievement");
            das.getAffectedPlayer().dominate(Achievements.get("Empire"));
          }
        }
        return null;
      }
    });
  }
}
