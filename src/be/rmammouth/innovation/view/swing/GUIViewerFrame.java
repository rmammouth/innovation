package be.rmammouth.innovation.view.swing;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.moves.*;
import be.rmammouth.innovation.view.*;

public class GUIViewerFrame extends JFrame
{
  private GameModel model;
  private JPanel contentPane;
  private DefaultListModel<String> logListModel=new DefaultListModel<>();
  private JPanel inputPanel;
  private JTabbedPane playersTabPane;
  private Map<Integer, PlayerPanel> playerPanels=new HashMap<>();
  private GameModelPanel gameModelPanel;
  

  /**
   * Create the frame.
   */
  public GUIViewerFrame()
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
    
    gameModelPanel = new GameModelPanel(this);
    topSplitPane.setRightComponent(gameModelPanel);
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
    
    ToolTipManager.sharedInstance().setDismissDelay(30000);
  }
  
  private void init(GameModel model)
  {
    this.model=model;
    
    for (Player player : model.getPlayers())
    {
      PlayerPanel panel=new PlayerPanel(player);
      playersTabPane.add(player.getName(), panel);
      playerPanels.put(player.getIndex(), panel);
    }
    setVisible(true);
  }

  public GameModel getModel()
  {
    return model;
  }

  public void updateView(GameModel model)
  {
    if (this.model==null)
    {
      //first call
      init(model);
    }
    
    this.model=model;
    gameModelPanel.fireDataChanged();
    for (Player player : model.getPlayers())
    {
      PlayerPanel panel=playerPanels.get(player.getIndex());
      if (panel!=null) panel.fireDataChanged(player);
    }
  }

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
