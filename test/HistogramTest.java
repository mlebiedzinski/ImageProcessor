import org.junit.Test;

import model.Histogram;
import model.Image;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for testing the histogram.
 */
public class HistogramTest {

  Histogram histogram;
  Image image;

  @Test(expected = IllegalArgumentException.class)
  public void testImageConsException() {
    this.histogram = new Histogram(null);
  }

  @Test
  public void testHistogram() {
    image = new Image("res/dogs.png");
    histogram = new Histogram(image);

    assertEquals(257, histogram.getRedFrequencyMap().size());
    assertEquals(257, histogram.getGreenFrequencyMap().size());
    assertEquals(257, histogram.getBlueFrequencyMap().size());
    assertEquals(257, histogram.getIntensityFrequencyMap().size());

  }
}
