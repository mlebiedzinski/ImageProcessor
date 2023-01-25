import org.junit.Test;

import control.commands.VerticalFlip;
import model.Image;
import model.ImageCollection;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for the VerticalFlip command class.
 */
public class VerticalFlipTest {
  Image image1;
  ImageCollection collection;
  VerticalFlip verticalFlip;

  @Test
  public void init1() {
    this.image1 = new Image("res/dogs.ppm");
    this.collection = new ImageCollection();
    assertEquals(this.image1, this.image1);
  }

  @Test
  public void testVerticalFlip() {
    init1();
    this.collection.getHashMap().put("OG dogs", this.image1);
    assertEquals(172, this.image1.getPixel(0, 0).getR());

    this.verticalFlip = new VerticalFlip("OG dogs", "Flipped dogs",
            collection);

    assertEquals(172,
            this.collection.getHashMap().get("OG dogs").getPixel(0, 0).getR());
    assertEquals(188,
            this.collection.getHashMap().get("OG dogs").getPixel(426, 0).getR());

    assertEquals(188,
            this.collection.getHashMap().get("Flipped dogs").getPixel(0, 0).getR());

    assertEquals(172,
            this.collection.getHashMap().get("Flipped dogs").getPixel(426, 0).getR());

    assertEquals(2, this.collection.getHashMap().size());

    // checking og is not mutated
    assertEquals(156,
            this.collection.getHashMap().get("OG dogs").getPixel(426, 639).getR());
  }
}
