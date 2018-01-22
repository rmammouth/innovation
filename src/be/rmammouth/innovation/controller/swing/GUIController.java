package be.rmammouth.innovation.controller.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import be.rmammouth.innovation.controller.*;
import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;

public class GUIController extends PlayerController
{
  private JPanel inputPanel;
  
  public GUIController(JPanel inputPanel)
  {
    super();
    this.inputPanel=inputPanel;
  }

  @Override
  public Move getNextMove(GameModel model, java.util.List<? extends Move> availableMoves)
  {
    GUIInteraction guiInteraction=new GUIInteraction(inputPanel);
    
    EventQueue.invokeLater(new Runnable()
    {      
      @Override
      public void run()
      {
        guiInteraction.showAvailableMoves(availableMoves);        
      }
    });
    
    synchronized(guiInteraction)
    {
      try
      {
        guiInteraction.wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    
    return guiInteraction.getChosenMove();
  }  
}

class GUIInteraction
{
  private JPanel inputPanel;
  private Move chosenMove;
  
  public GUIInteraction(JPanel inputPanel)
  {
    super();
    this.inputPanel = inputPanel;
  }
  
  void showAvailableMoves(java.util.List<? extends Move> availableMoves)
  {
    inputPanel.removeAll();
    inputPanel.setLayout(new FlowLayout());
    for (final Move move : availableMoves)
    {
      JButton button=new JButton(move.getLabel());
      button.addActionListener(new ActionListener()
      {        
        @Override
        public void actionPerformed(ActionEvent e)
        {
          chosenMove=move;
          synchronized(GUIInteraction.this)
          {
            GUIInteraction.this.notify();
          }
        }
      });
      inputPanel.add(button);
    }
    inputPanel.revalidate();
    inputPanel.repaint();    
  }

  public Move getChosenMove()
  {
    return chosenMove;
  }
  
  
}