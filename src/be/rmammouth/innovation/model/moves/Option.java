package be.rmammouth.innovation.model.moves;

import be.rmammouth.innovation.*;
import be.rmammouth.innovation.model.*;

public class Option extends Move
{
  private String label;
  private String resolveText;

  public Option(Player player, String label, String resolveText)
  {
    super(player);
    this.label = label;
    this.resolveText = resolveText;
  }

  @Override
  public String getLabel()
  {
    return label;
  }
  
  public boolean modifiesGameModel()
  {
    return false;
  }

  @Override
  protected void doResolve()
  {
    Innovation.getViewManager().log(resolveText);
  }
}
