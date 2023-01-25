import org.junit.Test;

import control.commands.Blur;
import model.Image;
import model.ImageCollection;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for the Blur command class.
 */
public class BlurTest {

  Image image1;
  ImageCollection collection;
  Blur blur;

  @Test
  public void init1() {
    this.image1 = new Image("res/dogs.ppm");
    this.collection = new ImageCollection();
    assertEquals(this.image1, this.image1);

  }

  @Test
  public void testBlur() {
    init1();
    this.collection.getHashMap().put("OG dogs", this.image1);
    assertEquals(172, this.image1.getPixel(0, 0).getR());
    assertEquals(162, this.image1.getPixel(0, 0).getG());
    assertEquals(150, this.image1.getPixel(0, 0).getB());

    this.blur = new Blur("OG dogs", "new dogs", this.collection);
    assertEquals(95, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getR());
    assertEquals(90, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getG());
    assertEquals(82, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getB());
    assertEquals(2, this.collection.getHashMap().size());

  }

}
