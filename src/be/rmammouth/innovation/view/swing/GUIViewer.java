package be.rmammouth.innovation.view.swing;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;
import be.rmammouth.innovation.view.*;

public class GUIViewer extends JFrame implements GameViewer
{
  private GameModel model;
  private JPanel contentPane;
  private DefaultListModel<String> logListModel=new DefaultListModel<>();
  private JPanel inputPanel;
  private JTabbedPane playersTabPane;
  private Map<Player, PlayerPanel> playerPanels=new HashMap<>();

  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          GUIViewer frame = new GUIViewer();
          frame.setVisible(true);
        } 
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public void init(GameModel model)
  {
    this.model=model;
    for (Player player : model.getPlayers())
    {
      PlayerPanel panel=new PlayerPanel(player);
      playersTabPane.add(player.getName(), panel);
      playerPanels.put(player, panel);
    }
    setVisible(true);
  }

  /**
   * Create the frame.
   */
  public GUIViewer()
  {
    setTitle("Innovation");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 992, 669);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
    
    JSplitPane mainSplitPane = new JSplitPane();
    mainSplitPane.setResizeWeight(1.0);
    mainSplitPane.setDividerSize(3);
    mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    contentPane.add(mainSplitPane, BorderLayout.CENTER);
    
    JSplitPane topSplitPane = new JSplitPane();
    topSplitPane.setResizeWeight(1.0);
    topSplitPane.setDividerSize(3);
    mainSplitPane.setLeftComponent(topSplitPane);
    
    playersTabPane = new JTabbedPane(JTabbedPane.TOP);
    topSplitPane.setLeftComponent(playersTabPane);
    
    JPanel infoPanel = new JPanel();
    topSplitPane.setRightComponent(infoPanel);
    topSplitPane.setDividerLocation(600);
    
    JPanel bottomPanel = new JPanel();
    mainSplitPane.setRightComponent(bottomPanel);
    mainSplitPane.setDividerLocation(400);
    bottomPanel.setLayout(new BorderLayout(0, 0));
    
    inputPanel = new JPanel();
    bottomPanel.add(inputPanel, BorderLayout.NORTH);
    
    JScrollPane logScrollPane = new JScrollPane();
    logScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    logScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    JList<String> logList = new JList<String>((ListModel) logListModel);
    logList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    logScrollPane.setViewportView(logList);
    bottomPanel.add(logScrollPane);
    
    
  }

  @Override
  public void turnStarted(Player player)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void moveResolved(Move move)
  {
    for (PlayerPanel panel : playerPanels.values())
    {
      panel.fireDataChanged();
    }
  }

  @Override
  public void log(String message)
  {
    EventQueue.invokeLater(new Runnable()
    {
      
      @Override
      public void run()
      {
        logListModel.insertElementAt(message,0);
        
      }
    });
  }

  public JPanel getInputPanel() 
  {
    return inputPanel;
  }
}
