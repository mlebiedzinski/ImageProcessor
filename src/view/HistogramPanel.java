package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

import model.Histogram;

/**
 * This is the class for the histogram panel. It creates a visual for the viewer to see the
 * distribution of value and frequency for RGB and intensity components in the image.
 */
public class HistogramPanel extends JPanel {

  private Histogram histogram;

  /**
   * This is the constructor for HistogramPanel. It creates the histogram using the Graphics class.
   */
  public HistogramPanel() {
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(500, 540));
    this.setBorder(BorderFactory.createTitledBorder("RGB Histogram"));
  }

  public void updateHistogram(Histogram histogram) {
    this.histogram = histogram;
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // if no image has been loaded yet so no histogram -> only paint axis
    if (histogram == null) {
      Graphics2D g2d = (Graphics2D) g;

      // x axis
      g2d.drawLine(50, 40, 50, 400);
      // y axis
      g2d.drawLine(50, 400, 300, 400);

      g2d.drawString("0", 38, 400);
      g2d.drawString("255", 310, 400);
      g2d.drawString("value", 340, 400);
      g2d.drawString("3600", 14, 45);
      g2d.drawString("frequency", 50, 35);

      g2d.setColor(Color.BLACK);

      // else paint image's histogram
    } else {
      Graphics2D g2d = (Graphics2D) g;

      // x axis
      g2d.drawLine(50, 40, 50, 400);
      // y axis
      g2d.drawLine(50, 400, 300, 400);

      g2d.drawString("0", 38, 400);
      g2d.drawString("255", 310, 400);
      g2d.drawString("value", 340, 400);
      g2d.drawString("3600", 14, 45);
      g2d.drawString("frequency", 50, 35);

      g2d.setColor(Color.BLACK);

      Graphics2D g2dint = (Graphics2D) g;
      Map<Integer, Integer> intensity = this.histogram.getIntensityFrequencyMap();

      for (Map.Entry<Integer, Integer> map : intensity.entrySet()) {
        int key = map.getKey();
        int value = map.getValue() / 10;

        g2dint.fillRect(50 + key, 30 - value + 370, 1, value);
        g2dint.setColor(new Color(213, 0, 255, 130));

      }

      Graphics2D g2dblue = (Graphics2D) g;
      Map<Integer, Integer> blue = histogram.getBlueFrequencyMap();

      for (Map.Entry<Integer, Integer> map : blue.entrySet()) {
        int key = map.getKey();
        int value = map.getValue() / 10;

        g2dblue.fillRect(50 + key, 30 - value + 370, 1, value);
        g2dblue.setColor(new Color(0, 0, 255, 130));
      }

      Graphics2D g2dred = (Graphics2D) g;
      Map<Integer, Integer> red = histogram.getRedFrequencyMap();

      for (Map.Entry<Integer, Integer> map : red.entrySet()) {
        int key = map.getKey();
        int value = map.getValue() / 10;

        g2dred.fillRect(50 + key, 30 - value + 370, 1, value);
        g2dred.setColor(new Color(255, 0, 0, 130));
      }

      Graphics2D g2dgreen = (Graphics2D) g;
      Map<Integer, Integer> green = histogram.getGreenFrequencyMap();

      for (Map.Entry<Integer, Integer> map : green.entrySet()) {
        int key = map.getKey();
        int value = map.getValue() / 10;

        g2dgreen.fillRect(50 + key, 30 - value + 370, 1, value);
        g2dgreen.setColor(new Color(0, 255, 0, 130));
      }
    }

  }
}

