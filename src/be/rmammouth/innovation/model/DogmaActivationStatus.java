package be.rmammouth.innovation.model;

import java.util.*;

import be.rmammouth.innovation.*;

public class DogmaActivationStatus
{  
  private Dogma dogma;
  private CardActivationStatus cardActivationStatus;
  private Iterator<Player> itrPlayers;
  private Player currentlyAffectedPlayer;
  private PlayerInteraction nextInteraction;
  private int resolutionStep;
  
  public DogmaActivationStatus(Dogma dogma, CardActivationStatus cardActivationStatus)
  {
    super();
    this.dogma = dogma;
    this.cardActivationStatus=cardActivationStatus;
  }

  public Dogma getDogma()
  {
    return dogma;
  }
  
  public void startResolution(CardActivationStatus cardActivationStatus)
  {
    itrPlayers=dogma.getAffectedPlayers(cardActivationStatus).iterator();
  }
  
  public final PlayerInteraction getNextInteraction()
  {
    if (currentlyAffectedPlayer==null)
    {
      if (itrPlayers.hasNext())
      {
        currentlyAffectedPlayer=itrPlayers.next();
        Innovation.getViewManager().log("Activating "+cardActivationStatus.getCard().getLabel()+" "+dogma.getDogmaTypeLabel()+" dogma "+dogma.getIndex()+" on "+currentlyAffectedPlayer.getName());
        resolutionStep=0;
      }
      else return null;
    }
    
    nextInteraction=dogma.getNextPlayerInteraction(cardActivationStatus, this);
    if (nextInteraction==null)
    {
      //we're done with this player, switch to the next one
      currentlyAffectedPlayer=null;
      return getNextInteraction();
    }
    else return nextInteraction;
  }

  public int getResolutionStep()
  {
    return resolutionStep;
  }

  public void setResolutionStep(int resolutionStep)
  {
    this.resolutionStep = resolutionStep;
  }

  public Player getAffectedPlayer()
  {
    return currentlyAffectedPlayer;
  }
}
