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
        if (das.getResolutionStep()==0)
        {
          List<Card> archivableCards=das.getAffectedPlayer().getFilteredHand(new CardFilter()
          {          
            @Override
            public boolean isFiltered(Card card) 
            {
              return !das.getAffectedPlayer().getColorsOnBoard().contains(card.getColor());
            }
          });
          if (archivableCards.isEmpty()) return null;
          List<Move> moves=ArchiveCard.getArchiveCardMoves(das.getAffectedPlayer(), archivableCards);
          moves.add(new Pass(das.getAffectedPlayer()));
          das.setResolutionStep(1);
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else if (das.getResolutionStep()==1)
        {          
          if (das.getLastResolvedMove().isPass()) return null;
          ArchiveCard archiveCard=(ArchiveCard)das.getLastResolvedMove();
          if (!das.getAffectedPlayer().getCardsPile(archiveCard.getCard().getColor()).isSplayable(Splaying.LEFT)) return null;
          SplayPile splayMove=new SplayPile(das.getAffectedPlayer(), archiveCard.getCard().getColor(), Splaying.LEFT);
          das.setResolutionStep(2);
          return new PlayerInteraction(das.getAffectedPlayer(), splayMove, new Pass(das.getAffectedPlayer()));
        }
        else return null;
      }
    });
  }
}
