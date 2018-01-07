package be.rmammouth.innovation.view.swing;

import javax.swing.*;
import javax.swing.table.*;

import be.rmammouth.innovation.model.*;

import java.awt.BorderLayout;
import java.util.*;
import java.awt.FlowLayout;

public class PlayerPanel extends JPanel
{
  private static Map<Card, CardPanel> cardsPanels=new HashMap<>();
  
  private Player player;  
  private JTable resourcesTable;
  private ResourceTableModel resourcesTableModel=new ResourceTableModel();
  private JPanel handPanel;
  private JPanel boardPanel;
  private Map<Color,CardsPilePanel> cardsPilePanels=new EnumMap<>(Color.class);
  
  static
  {
    for (Card card : Cards.getAll())
    {
      cardsPanels.put(card, new CardPanel(card));
    }
  }
  
  /**
   * Create the panel.
   */
  public PlayerPanel(Player player)
  {
    this.player=player;
    setLayout(new BorderLayout(0, 0));
    
    JScrollPane boardScrollPane = new JScrollPane();
    add(boardScrollPane, BorderLayout.CENTER);
    
    boardPanel = new JPanel();
    FlowLayout flowLayout = (FlowLayout) boardPanel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    boardScrollPane.setViewportView(boardPanel);
    
    JPanel southPanel = new JPanel();
    add(southPanel, BorderLayout.SOUTH);
    southPanel.setLayout(new BorderLayout(0, 0));
    
    JPanel southEastPanel = new JPanel();
    southPanel.add(southEastPanel, BorderLayout.EAST);
    southEastPanel.setLayout(new BorderLayout(0, 0));
    
    JPanel inflDomPanel = new JPanel();
    southEastPanel.add(inflDomPanel, BorderLayout.CENTER);
    
    resourcesTable = new JTable(resourcesTableModel);
    southEastPanel.add(resourcesTable, BorderLayout.EAST);
    
    JScrollPane handScrollPane = new JScrollPane();
    handScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    southPanel.add(handScrollPane, BorderLayout.CENTER);
    
    handPanel = new JPanel();
    handScrollPane.setViewportView(handPanel);
    
    for (Color color : Color.values())
    {
      CardsPilePanel cardsPilePanel=new CardsPilePanel(player.getCardsPile(color));
      cardsPilePanels.put(color, cardsPilePanel);
      boardPanel.add(cardsPilePanel);
    }
    
    fireDataChanged();
  }
  
  public void fireDataChanged()
  {
    //update resource table
    resourcesTableModel.fireTableDataChanged();
    
    //update hand
    handPanel.removeAll();
    for (Card card : player.getHand())
    {
      handPanel.add(cardsPanels.get(card));
      handPanel.repaint();
    }
    
    //piles
    for (Color color : Color.values())
    {
      CardsPilePanel panel=cardsPilePanels.get(color);
      panel.removeAll();
      for (Card card : player.getCardsPile(color).getCards())
      {
        panel.add(cardsPanels.get(card),0);
      }
      panel.repaint();
    }
  }
  
  class ResourceTableModel extends AbstractTableModel
  {    
    @Override
    public int getRowCount()
    {
      return Resource.values().length;
    }

    @Override
    public int getColumnCount()
    {
      return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
      Resource resource=Resource.values()[rowIndex];
      if (columnIndex==0)
      {
        return resource.toString();
      }
      else
      {
        return player.getResourcesCount().getCount(resource);
      }
    }
  }
}


