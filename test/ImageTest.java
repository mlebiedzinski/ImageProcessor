import org.junit.Test;

import model.Image;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for the Image class.
 */
public class ImageTest {
  Image image1;
  Image image2;

  // initialize fields for testing
  @Test
  public void init1() {
    this.image1 = new Image("res/dogs.ppm");
    this.image2 = new Image("res/dogs.png");
    assertEquals(this.image1, this.image1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageConsException() {
    this.image1 = new Image("nonexistent");
  }

  @Test
  public void testImageCons1() {
    init1();
    assertEquals(172, this.image1.getPixel(0, 0).getR());
  }

  @Test
  public void testImageIOCons() {
    init1();
    assertEquals(172, this.image2.getPixel(0, 0).getR());
    assertEquals(640, this.image2.getWidth());
  }

  @Test
  public void testImageConsPixels() {
    init1();
    Image imageCopy = new Image(this.image1.getPixelArray());
    assertEquals(427, imageCopy.getHeight());
    assertEquals(640, imageCopy.getWidth());
    assertEquals(imageCopy.getPixel(0, 0).getR(), this.image1.getPixel(0, 0).getR());
    assertEquals(imageCopy.getPixel(0, 0).getG(), this.image1.getPixel(0, 0).getG());
    assertEquals(imageCopy.getPixel(0, 0).getB(), this.image1.getPixel(0, 0).getB());
    assertEquals(imageCopy.getPixel(10, 10).getR(), this.image1.getPixel(10, 10).getR());
  }

  @Test
  public void testGetWidth() {
    init1();
    assertEquals(640, this.image1.getWidth());
  }

  @Test
  public void testGetHeight() {
    init1();
    assertEquals(427, this.image1.getHeight());
  }

  @Test
  public void testGetMaxValue() {
    init1();
    assertEquals(255, this.image1.getMaxValue());
  }

}
