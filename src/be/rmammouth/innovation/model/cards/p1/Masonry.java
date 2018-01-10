package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.achievements.*;
import be.rmammouth.innovation.model.moves.*;

public class Masonry extends Card
{
  public Masonry()
  {
    super("Masonry", Period.ONE, Color.YELLOW,
        Resource.TOWER,
        null, Resource.TOWER, Resource.TOWER);
    
    addDogma(new CooperationDogma(Resource.TOWER)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        CardFilter towerFilter=new CardResourceFilter(Resource.TOWER);        
        List<Card> towerCards=player.getFilteredHand(towerFilter);
        if (towerCards.isEmpty()) return false;
        int playedCards=0;        
        while (!towerCards.isEmpty())
        {
          Move pass=new Pass(player);
          List<Move> playMoves=PlayCard.getPlayCardMoves(player, towerCards);
          playMoves.add(pass);
          Move chosenMove=player.getController().getAndResolveNextMove(playMoves);
          if (chosenMove==pass) break;
          else
          {
            towerCards=player.getFilteredHand(towerFilter);
            playedCards++;
          }
        }
        if (playedCards==0) return false;
        else
        {
          if (playedCards>=4)
          {
            Innovation.getViewer().log(player.getName()+" has put 4 or more [TOWER] cards into play and dominates the Monument achievement");
            player.dominate(Achievements.get("Monument"));
          }
          return true;
        }
      }
    });
  }
}
