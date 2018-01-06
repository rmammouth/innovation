package be.rmammouth.innovation.view;

import javax.swing.*;
import javax.swing.table.*;

import be.rmammouth.innovation.model.*;

import java.awt.BorderLayout;

public class PlayerPanel extends JPanel
{
  private Player player;  
  private JTable resourcesTable;
  private ResourceTableModel resourcesTableModel=new ResourceTableModel();
  
  /**
   * Create the panel.
   */
  public PlayerPanel(Player player)
  {
    this.player=player;
    setLayout(new BorderLayout(0, 0));
    
    JPanel boardPanel = new JPanel();
    add(boardPanel, BorderLayout.CENTER);
    
    JPanel southPanel = new JPanel();
    add(southPanel, BorderLayout.SOUTH);
    southPanel.setLayout(new BorderLayout(0, 0));
    
    JPanel handPanel = new JPanel();
    southPanel.add(handPanel, BorderLayout.CENTER);
    
    JPanel southEastPanel = new JPanel();
    southPanel.add(southEastPanel, BorderLayout.EAST);
    southEastPanel.setLayout(new BorderLayout(0, 0));
    
    JPanel inflDomPanel = new JPanel();
    southEastPanel.add(inflDomPanel, BorderLayout.CENTER);
    
    resourcesTable = new JTable(resourcesTableModel);
    southEastPanel.add(resourcesTable, BorderLayout.EAST);
  }
  
  public void fireDataChanged()
  {
    resourcesTableModel.fireTableDataChanged();
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


