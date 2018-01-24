package be.rmammouth.innovation.view.swing;

import javax.swing.*;
import javax.swing.table.*;

import be.rmammouth.innovation.model.*;
import java.awt.BorderLayout;

public class GameModelPanel extends JPanel
{
  private GUIViewerFrame viewer;
  private CardsPilesTableModel cardsPilesTableModel=new CardsPilesTableModel();
  
  public GameModelPanel(GUIViewerFrame viewer)
  {
    this.viewer=viewer;
    setLayout(new BorderLayout(0, 0));
    
    JTable cardsPilesTable = new JTable(cardsPilesTableModel);
    add(cardsPilesTable, BorderLayout.CENTER);
  }


  public void fireDataChanged()
  {
    cardsPilesTableModel.fireTableDataChanged();
    
  }
  
  class CardsPilesTableModel extends AbstractTableModel
  {
    @Override
    public int getRowCount()
    {
      return Period.values().length;
    }

    @Override
    public int getColumnCount()
    {
      return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
      Period period=Period.values()[rowIndex];
      if (columnIndex==0) return period;
      else if (columnIndex==1) return viewer.getModel().getDrawPile(period).size();
      else return viewer.getModel().isPeriodAchievementAvailable(period);
    }
    
  }
}
