package be.rmammouth.innovation.model.cards.p1;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Tools extends Card
{
  public Tools()
  {
    super("Tools", Period.ONE, Color.BLUE,
        null,
        Resource.BULB, Resource.BULB, Resource.TOWER);
    
    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        Option recycle3Cards=new Option(player, "Recyle 3 cards", player.getName()+" will recycle 3 cards");
        Pass pass=new Pass(player);
        List<Move> options=Arrays.asList(recycle3Cards, pass);
        Move chosenMove=player.getController().getAndResolveNextMove(options);
        if (chosenMove==pass) return false;
        else
        {
          int nbrRecycledCards;
          for (nbrRecycledCards=0;nbrRecycledCards<3;nbrRecycledCards++)
          {
            if (player.getHand().isEmpty()) break;
            List<Move> recycleCardMoves=RecycleCard.getAllRecycleCardMoves(player, CardLocation.HAND);
            player.getController().getAndResolveNextMove(recycleCardMoves);
          }
          if (nbrRecycledCards==0) return false;
          if (nbrRecycledCards==3)
          {
            DrawCard drawCard=new DrawCard(player, Period.THREE);
            drawCard.resolve();
            PlayCard playCard=new PlayCard(player, drawCard.getCard());
            playCard.resolve();
          }
          return true;
        }
      }
    });
    
    addDogma(new CooperationDogma(Resource.BULB)
    {      
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        List<Card> cardsP3=player.getFilteredHand(new CardPeriodFilter(Period.THREE));
        if (cardsP3.isEmpty()) return false;
        List<Move> recycleP3=RecycleCard.getRecycleCardMoves(player, cardsP3, CardLocation.HAND);
        Pass pass=new Pass(player);
        recycleP3.add(pass);
        Move chosenMove=player.getController().getAndResolveNextMove(recycleP3);
        if (chosenMove==pass) return false;
        //draw 3 cards P1
        for (int n=0;n<3;n++)
        {
          new DrawCard(player, Period.ONE).resolve();
        }
        return true;
      }
    });
  }

}
