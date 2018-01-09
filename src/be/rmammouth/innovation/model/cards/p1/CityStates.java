package be.rmammouth.innovation.model.cards.p1;


import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class CityStates extends Card
{
  public CityStates()
  {
    super("City States", Period.ONE, Color.PURPLE,
        null,
        Resource.CROWN, Resource.CROWN, Resource.TOWER);
    
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
        List<Move> archiveMoves=ArchiveCard.getArchiveCardMoves(player, archivableCards);
        Pass passArchive=new Pass(player);
        archiveMoves.add(passArchive);
        Move chosenArchiveMove=player.getController().getAndResolveNextMove(archiveMoves);
        if (chosenArchiveMove==passArchive) return false;
        else
        {          
          SplayPile splayMove=new SplayPile(player, ((ArchiveCard)chosenArchiveMove).getCard().getColor(), Splaying.LEFT);
          Pass passSplay=new Pass(player);
          List<Move> splayMoves=Arrays.asList(splayMove, passSplay);
          player.getController().getAndResolveNextMove(splayMoves);          
          return true;
        }
      }
    });
  }
}
