package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * This is the class for the HistogramKey. It is a guide for the user to understand which lines
 * represent each component in the image.
 */
public class HistogramKey extends JPanel {

  /**
   * This is the constructor for HistogramKey. It creates the key using the Graphics class.
   */
  public HistogramKey() {
    this.setPreferredSize(new Dimension(500, 400));
    this.setBackground(new Color(255, 251, 209));
  }

  /**
   * This method paints the histogram using the Graphics class.
   *
   * @param g the graphics object
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d1 = (Graphics2D) g;
    Graphics2D g2dred1 = (Graphics2D) g;
    Graphics2D g2dgreen1 = (Graphics2D) g;
    Graphics2D g2dblue1 = (Graphics2D) g;
    Graphics2D g2dint1 = (Graphics2D) g;

    g2d1.drawString("red component", 80, 17);
    g2d1.drawString("green component", 80, 34);
    g2d1.drawString("blue component", 80, 51);
    g2d1.drawString("intensity component", 80, 68);

    g2dred1.setColor(new Color(255, 0, 0));
    g2dred1.fillRect(60, 5, 15, 15);

    g2dgreen1.setColor(new Color(0, 255, 0));
    g2dgreen1.fillRect(60, 22, 15, 15);

    g2dblue1.setColor(new Color(0, 0, 255));
    g2dblue1.fillRect(60, 39, 15, 15);

    g2dint1.setColor(new Color(213, 0, 255, 255));
    g2dint1.fillRect(60, 56, 15, 15);

  }
}
