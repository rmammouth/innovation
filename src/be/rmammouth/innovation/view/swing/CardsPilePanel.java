package be.rmammouth.innovation.view.swing;

import java.awt.*;

import javax.swing.*;

import be.rmammouth.innovation.model.*;

public class CardsPilePanel extends JPanel
{
  private CardsPile pile;
  
  /**
   * Create the panel.
   */
  public CardsPilePanel(CardsPile pile)
  {
    this.pile=pile;
    setLayout(new CardsPileLayout());
  }

  class CardsPileLayout implements LayoutManager
  {
    private static final int stackGap=5;
    
    @Override
    public void addLayoutComponent(String name, Component comp)
    {            
    }

    @Override
    public void removeLayoutComponent(Component comp)
    {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent)
    {
      int cardsCount=parent.getComponentCount();
      
      switch (pile.getSplaying())
      {
      case LEFT:
        throw new UnsupportedOperationException();
      case NONE:
        if (cardsCount>0)
        {
          Dimension cardSize=parent.getComponent(0).getPreferredSize();
          return new Dimension(cardSize.width+((cardsCount-1)*stackGap), cardSize.height+((cardsCount-1)*stackGap));
        }
        else return new Dimension(0,0);
      case RIGHT:
        throw new UnsupportedOperationException();
      case UP:
        throw new UnsupportedOperationException();
      default:
        throw new UnsupportedOperationException();
      }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent)
    {
      return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent)
    {
      int x=0, y=0;     
      //top card is at the end
      for (int i=parent.getComponentCount()-1;i>=0;i--)
      {
        Component comp=parent.getComponent(i);
        comp.setSize(comp.getPreferredSize());
        comp.setBounds(x, y, comp.getPreferredSize().width, comp.getPreferredSize().height);
        x+=stackGap;
        y+=stackGap;
      }
    }
    
    
  }
}


