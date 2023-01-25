package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a Histogram, which is a table of (value,frequency) entries for the
 * RGB and intensity components in the image.
 */
public class Histogram {

  // key: 0-255 value
  // value: frequency
  Map<Integer, Integer> redFrequencyMap = new HashMap<>();
  Map<Integer, Integer> blueFrequencyMap = new HashMap<>();
  Map<Integer, Integer> greenFrequencyMap = new HashMap<>();
  Map<Integer, Integer> intensityFrequencyMap = new HashMap<>();


  /**
   * This is the constructor for Histogram. It creates a map for each component (RGB and intensity)
   * and in each map, the key is the 0-255 value and the value is the frequency.
   *
   * @param image image to be used for the histogram
   */
  public Histogram(Image image) {

    if (image == null) {
      throw new IllegalArgumentException("Image is null.");
    }

    // initialize each map with value of 0 for keys 0-255?
    for (int i = 0; i < 257; i++) {
      this.redFrequencyMap.put(i, 0);
      this.blueFrequencyMap.put(i, 0);
      this.greenFrequencyMap.put(i, 0);
      this.intensityFrequencyMap.put(i, 0);
    }

    // iterate through entire image
    // for each component:
    // add 1 to the value attached to that key
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {

        int currentR = image.getPixel(i, j).getR();
        int currentG = image.getPixel(i, j).getG();
        int currentB = image.getPixel(i, j).getB();
        int currentIntensity = image.getPixel(i, j).getIntensity();

        this.redFrequencyMap.put(currentR, this.redFrequencyMap.get(currentR) + 1);
        this.blueFrequencyMap.put(currentG, this.blueFrequencyMap.get(currentG) + 1);
        this.greenFrequencyMap.put(currentB, this.greenFrequencyMap.get(currentB) + 1);
        this.intensityFrequencyMap.put(currentIntensity,
                this.intensityFrequencyMap.get(currentIntensity) + 1);
      }
    }
  }


  /**
   * This getter method returns the red frequency map; it's here so the value
   * and frequency amounts can be passed to the HistogramPanel and be visualized in the view.
   *
   * @return red frequency map
   */
  public Map<Integer, Integer> getRedFrequencyMap() {
    return this.redFrequencyMap;
  }

  /**
   * This getter method returns the green frequency map; it's here so the value
   * and frequency amounts can be passed to the HistogramPanel and be visualized in the view.
   *
   * @return green frequency map
   */
  public Map<Integer, Integer> getGreenFrequencyMap() {
    return this.greenFrequencyMap;
  }

  /**
   * This getter method returns the blue frequency map; it's here so the value
   * and frequency amounts can be passed to the HistogramPanel and be visualized in the view.
   *
   * @return blue frequency map
   */
  public Map<Integer, Integer> getBlueFrequencyMap() {
    return this.blueFrequencyMap;
  }

  /**
   * This getter method returns the intensity frequency map; it's here so the value
   * and frequency amounts can be passed to the HistogramPanel and be visualized in the view.
   *
   * @return intensity frequency map
   */
  public Map<Integer, Integer> getIntensityFrequencyMap() {
    return this.intensityFrequencyMap;
  }

}
