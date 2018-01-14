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
      public boolean activateOnPlayer(CardActivationState cas, Player player) 
      {
        List<Card> archivableCards=player.getFilteredHand(new CardFilter()
        {          
          @Override
          public boolean isFiltered(Card card) 
          {
            return !player.getColorsOnBoard().contains(card.getColor());
          }
        });
        if (archivableCards.isEmpty()) return false;
        else
        {
          List<Move> archiveMoves=ArchiveCard.getArchiveCardMoves(player, archivableCards);
          Pass passArchive=new Pass(player);
          archiveMoves.add(passArchive);
          Move chosenMove=player.getController().getAndResolveNextMove(archiveMoves);
          if (chosenMove==passArchive) return false;
          else
          {
            ArchiveCard archiveCard=(ArchiveCard)chosenMove;
            if (player.getCardsPile(archiveCard.getCard().getColor()).getSplaying()!=Splaying.LEFT)
            {
              SplayPile splayMove=new SplayPile(player, archiveCard.getCard().getColor(), Splaying.LEFT);
              Pass passSplay=new Pass(player);
              List<Move> splayMoves=Arrays.asList(splayMove, passSplay);
              player.getController().getAndResolveNextMove(splayMoves);
            }
            return true;
          }
        }
      }
    });
	}
}
