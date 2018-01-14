package be.rmammouth.innovation.view.swing;

import javax.swing.*;

import be.rmammouth.innovation.model.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

public class CardPanel extends JPanel
{
  private Card card;
  
  /**
   * Create the panel.
   */
  public CardPanel(Card card)
  {
    setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
    this.card=card;
    
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {1, 1, 1};
    gridBagLayout.rowHeights = new int[] {1, 1};
    gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0};
    gridBagLayout.rowWeights = new double[]{1.0, 1.0};
    setLayout(gridBagLayout);
    
    JLabel topLeftResource = new JLabel(getResourceText(ResourceLocation.TOP_LEFT));
    topLeftResource.setHorizontalAlignment(SwingConstants.CENTER);
    topLeftResource.setPreferredSize(new Dimension(64, 64));
    GridBagConstraints gbc_topLeftResource = new GridBagConstraints();
    gbc_topLeftResource.insets = new Insets(0, 0, 5, 5);
    gbc_topLeftResource.gridx = 0;
    gbc_topLeftResource.gridy = 0;
    add(topLeftResource, gbc_topLeftResource);
    
    JPanel cardInfoPanel = new JPanel();
    cardInfoPanel.setOpaque(false);
    cardInfoPanel.setPreferredSize(new Dimension(128, 64));
    GridBagConstraints gbc_cardInfoPanel = new GridBagConstraints();
    gbc_cardInfoPanel.gridwidth = 2;
    gbc_cardInfoPanel.insets = new Insets(0, 0, 5, 0);
    gbc_cardInfoPanel.fill = GridBagConstraints.BOTH;
    gbc_cardInfoPanel.gridx = 1;
    gbc_cardInfoPanel.gridy = 0;
    add(cardInfoPanel, gbc_cardInfoPanel);
    cardInfoPanel.setLayout(new BorderLayout(0, 0));
    
    JLabel cardName = new JLabel(card.getName());
    cardName.setHorizontalAlignment(SwingConstants.CENTER);
    cardInfoPanel.add(cardName, BorderLayout.NORTH);
    
    JLabel bottomRIghtResource = new JLabel(getResourceText(ResourceLocation.BOTTOM_RIGHT));
    bottomRIghtResource.setHorizontalAlignment(SwingConstants.CENTER);
    bottomRIghtResource.setPreferredSize(new Dimension(64, 64));
    GridBagConstraints gbc_bottomRIghtResource = new GridBagConstraints();
    gbc_bottomRIghtResource.gridx = 2;
    gbc_bottomRIghtResource.gridy = 1;
    add(bottomRIghtResource, gbc_bottomRIghtResource);
    
    JLabel bottomLeftResource = new JLabel(getResourceText(ResourceLocation.BOTTOM_LEFT));
    bottomLeftResource.setHorizontalAlignment(SwingConstants.CENTER);
    bottomLeftResource.setPreferredSize(new Dimension(64, 64));
    GridBagConstraints gbc_bottomLeftResource = new GridBagConstraints();
    gbc_bottomLeftResource.insets = new Insets(0, 0, 0, 5);
    gbc_bottomLeftResource.gridx = 0;
    gbc_bottomLeftResource.gridy = 1;
    add(bottomLeftResource, gbc_bottomLeftResource);
    
    JLabel bottomCentreResource = new JLabel(getResourceText(ResourceLocation.BOTTOM_CENTER));
    bottomCentreResource.setHorizontalAlignment(SwingConstants.CENTER);
    bottomCentreResource.setPreferredSize(new Dimension(64, 64));
    GridBagConstraints gbc_bottomCentreResource = new GridBagConstraints();
    gbc_bottomCentreResource.insets = new Insets(0, 0, 0, 5);
    gbc_bottomCentreResource.gridx = 1;
    gbc_bottomCentreResource.gridy = 1;
    add(bottomCentreResource, gbc_bottomCentreResource);

    setBackground(getCardColor());
    
    setToolTipText(buildToolTipText());
  }

  private Color getCardColor()
  {
    switch (card.getColor())
    {
    case BLUE:
      return Color.CYAN;
    case GREEN:
      return Color.GREEN;
    case PURPLE:
      return Color.MAGENTA;
    case RED:
      return Color.RED;
    case YELLOW:
      return Color.YELLOW;
    default:
      return Color.WHITE;
    }
  }
  
  private String getResourceText(ResourceLocation loc)
  {
    Resource resource=card.getResource(loc);
    if (resource==null) return card.getPeriod().asString();
    else return resource.toString();
  }
  
  private String buildToolTipText()
  {
    String text="<html>";
    for (Dogma dogma : card.getDogmas())
    {
      text+="<b>"+dogma.getResource()+"</b> : "+dogma.getText()+"<br/>";
    }
    text+="</html>";
    return text;
  }
}
