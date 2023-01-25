package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

/**
 * This panel represents the region where the image is produced.
 */
public class ImagePanel extends JPanel {

  private BufferedImage image;

  /**
   * This is the constructor for ImagePanel. It sets the background, size, border, etc.
   */
  public ImagePanel() {
    this.setBackground(Color.PINK);
    this.setPreferredSize(new Dimension(1000, 780));
    this.setBorder(BorderFactory.createTitledBorder("Image"));
    JLabel label = new JLabel();
    this.add(label);
  }

  /**
   * This method updates the image in the image panel so that it can be redrawn in the view.
   *
   * @param image buffered image
   */
  public void updateImage(BufferedImage image) {
    this.image = image;
  }

  /**
   * This method draws the image to the view.
   *
   * @param g the graphics object
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g.drawImage(this.image, 150, 30, null);
  }

}


