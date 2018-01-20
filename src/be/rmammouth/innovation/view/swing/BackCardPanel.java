package be.rmammouth.innovation.view.swing;

import javax.swing.*;

import be.rmammouth.innovation.model.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class BackCardPanel extends JPanel
{

  /**
   * Create the panel.
   */
  public BackCardPanel(Card card)
  {
    setPreferredSize(new Dimension(196, 132));
    setMinimumSize(new Dimension(196, 132));
    setMaximumSize(new Dimension(196, 132));
    setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
    setBackground(Color.GRAY);
    setLayout(new BorderLayout(0, 0));
    
    JLabel periodLabel = new JLabel(card.getPeriod().asString());
    periodLabel.setBackground(Color.LIGHT_GRAY);
    periodLabel.setFont(new Font("Tahoma", Font.PLAIN, 64));
    periodLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(periodLabel, BorderLayout.CENTER);

  }

}
