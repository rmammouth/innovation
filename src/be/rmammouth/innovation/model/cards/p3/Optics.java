package be.rmammouth.innovation.model.cards.p3;

import java.util.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class Optics extends Card
{
  public Optics()
  {
    super("Optics", Period.THREE, Color.RED,
          Resource.CROWN,
          Resource.CROWN, Resource.CROWN, null);

    addDogma(new CooperationDogma(Resource.CROWN)
    {
      @Override
      public PlayerInteraction getNextPlayerInteraction(CardActivationStatus cas, DogmaActivationStatus das)
      {
        if (das.getResolutionStep()==0)
        {
          DrawCard draw=new DrawCard(das.getAffectedPlayer(), Period.THREE);
          draw.resolve();
          PlayCard play=new PlayCard(das.getAffectedPlayer(), draw.getCard());
          play.resolve();
          if (play.getCard().containsResource(Resource.CROWN))
          {
            DrawCard draw4=new DrawCard(das.getAffectedPlayer(), Period.FOUR);
            draw4.resolve();
            new ScoreCard(das.getAffectedPlayer(), draw4.getCard()).resolve();
            return null;
          }
          else 
          {
            if (das.getAffectedPlayer().getScorePile().isEmpty()) return null;
            List<Player> playersWithFewerPoints=getPlayersWithFewerPoints(cas, das);
            if (playersWithFewerPoints.isEmpty()) return null;
            List<Move> moves=new ArrayList<>();
            for (Player player : playersWithFewerPoints)
            {
              moves.add(new ChoosePlayer(das.getAffectedPlayer(), player, "Transfer card to "+player.getName()));
            }
            das.setResolutionStep(1);
            return new PlayerInteraction(das.getAffectedPlayer(), moves);
          }
        }
        else if (das.getResolutionStep()==1)
        {
          Player chosenPlayer=((ChoosePlayer)das.getLastResolvedMove()).getChosenPlayer();
          List<Move> moves=TransferCard.getAllTransferCardMoves(das.getAffectedPlayer().getScorePile().getCards(), das.getAffectedPlayer(), CardLocation.SCORE_PILE, chosenPlayer, CardLocation.SCORE_PILE);
          das.setResolutionStep(2);
          return new PlayerInteraction(das.getAffectedPlayer(), moves);
        }
        else return null;
      }
      
      private List<Player> getPlayersWithFewerPoints(CardActivationStatus cas, DogmaActivationStatus das)
      {
        List<Player> players=new ArrayList<Player>();
        Iterator<Player> itr=cas.getModel().getOtherPlayersIterator(das.getAffectedPlayer());
        while (itr.hasNext())
        {
          Player player=itr.next();
          if (player.getScorePile().getScore()<das.getAffectedPlayer().getScorePile().getScore())
          {
            players.add(player);
          }
        }
        return players;
      }
    });
  }
}
