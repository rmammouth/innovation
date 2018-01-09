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
      
      if (cardsCount==0) return new Dimension(0,0);
      
      Dimension cardSize=parent.getComponent(0).getPreferredSize();
      if (cardsCount==1) return cardSize;
      
      switch (pile.getSplaying())
      {
      case LEFT:
      case RIGHT:
        return new Dimension(cardSize.width+((cardsCount-1)*(cardSize.width/3)), cardSize.height);
      case NONE:
        return new Dimension(cardSize.width+((cardsCount-1)*stackGap), cardSize.height+((cardsCount-1)*stackGap));
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
      int cardCount=parent.getComponentCount();
      if (cardCount==0) return;

      int x=0, y=0, deltaX=0, deltaY=0;
      Dimension cardSize=parent.getComponent(0).getPreferredSize();
      
      switch (pile.getSplaying())
      {
      case LEFT:
        x=0;
        y=0;
        deltaX=cardSize.width/3;
        deltaY=0;
        break;
      case NONE:
        x=(cardCount-1)*stackGap;
        y=(cardCount-1)*stackGap;
        deltaX=-stackGap;
        deltaY=-stackGap;
        break;
      case RIGHT:
        x=(cardCount-1)*(cardSize.width/3);
        y=0;
        deltaX=-(cardSize.width/3);
        deltaY=0;
        break;
      case UP:
        x=0;
        y=0;
        deltaX=0;
        deltaY=cardSize.height/2;
        break;
      default:
        break;        
      }
      
      //top card is at the start
      for (int i=0;i<parent.getComponentCount();i++)
      {
        Component comp=parent.getComponent(i);
        comp.setSize(comp.getPreferredSize());
        comp.setBounds(x, y, comp.getPreferredSize().width, comp.getPreferredSize().height);
        x+=deltaX;
        y+=deltaY;
      }
    }
  }
}


