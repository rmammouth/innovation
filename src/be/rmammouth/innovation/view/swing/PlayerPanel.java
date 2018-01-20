package be.rmammouth.innovation.view.swing;

import javax.swing.*;
import javax.swing.table.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.cards.*;

import java.awt.BorderLayout;
import java.util.*;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;

public class PlayerPanel extends JPanel
{
  private static Map<String, CardPanel> cardsPanels=new HashMap<String, CardPanel>();
  
  private Player player;  
  private JTable resourcesTable;
  private ResourceTableModel resourcesTableModel=new ResourceTableModel();
  private JPanel handPanel;
  private JPanel boardPanel;
  private Map<Color,CardsPilePanel> cardsPilePanels=new EnumMap<>(Color.class);
  private JLabel scoreLabel;
  private JLabel dominationLabel;
  
  static
  {
    for (Card card : Cards.getAll())
    {
      cardsPanels.put(card.getName(), new CardPanel(card));
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
    inflDomPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
    southEastPanel.add(inflDomPanel, BorderLayout.CENTER);
    inflDomPanel.setLayout(new BoxLayout(inflDomPanel, BoxLayout.Y_AXIS));
    
    scoreLabel = new JLabel("Score");
    inflDomPanel.add(scoreLabel);
    
    dominationLabel = new JLabel("Dominations");
    inflDomPanel.add(dominationLabel);
    
    resourcesTable = new JTable(resourcesTableModel);
    resourcesTable.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
    
    fireDataChanged(player);
  }
  
  public void fireDataChanged(Player player)
  {
    this.player=player;
    
    //update resource table
    resourcesTableModel.fireTableDataChanged();
    
    //update hand
    handPanel.removeAll();
    for (Card card : player.getHand())
    {
      handPanel.add(getCardPanel(card));
      handPanel.repaint();
    }
    
    //piles
    for (Color color : Color.values())
    {
      CardsPilePanel panel=cardsPilePanels.get(color);
      panel.removeAll();
      for (Card card : player.getCardsPile(color).getCards())
      {
        //must add cards in reverse order so the z order is correct
        panel.add(getCardPanel(card),0);
      }
      panel.repaint();
    }
    
    //score
    String scoreTxt="";  
    if (player.getScorePile().getCards().size()<=1)
    {
      scoreTxt="Score : "+player.getScorePile().getScore();
    }
    else
    {
      for (Card card : player.getScorePile().getCards())
      {
        if (scoreTxt.length()==0) scoreTxt="Score : ";
        else scoreTxt+="+";
        scoreTxt+=card.getPeriod().asInt();
      }
      scoreTxt+="="+player.getScorePile().getScore();
    }
    scoreLabel.setText(scoreTxt);
    
    //dominations
    dominationLabel.setText("Dominations : "+player.getDominations().size());
  }
  
  private static JPanel getCardPanel(Card card)
  {
    if (card.isRevealed()) return cardsPanels.get(card.getName());
    else return new BackCardPanel(card);
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


