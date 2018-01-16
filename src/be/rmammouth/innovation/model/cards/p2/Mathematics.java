package be.rmammouth.innovation.model.cards.p2;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.gamestates.*;
import be.rmammouth.innovation.model.moves.*;

public class Mathematics extends Card
{
  public Mathematics()
  {
    super("Mathematics", Period.TWO, Color.BLUE,
          null,
          Resource.BULB, Resource.CROWN, Resource.BULB);

    addDogma(new CooperationDogma(Resource.BULB)
    {
      @Override
      public boolean activateOnPlayer(CardActivationState cas, Player player)
      {
        List<Move> recycleMoves=RecycleCard.getAllRecycleCardMoves(player, CardLocation.HAND);
        Pass pass=new Pass(player);
        recycleMoves.add(pass);
        Move chosenMove=player.getController().getAndResolveNextMove(recycleMoves);
        if (chosenMove==pass) return false;
        else
        {
          Card recycledCard=((RecycleCard)chosenMove).getCard();
          if (recycledCard.getPeriod()==Period.TEN) throw new GameOverException("Pile 11 drawn");
          DrawCard draw=new DrawCard(player, recycledCard.getPeriod().next());
          draw.resolve();
          new PlayCard(player, draw.getCard()).resolve();
          return true;
        }
      }
    });
  }
}
