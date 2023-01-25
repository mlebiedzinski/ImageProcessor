import org.junit.Test;

import control.commands.TransformSepia;
import model.Image;
import model.ImageCollection;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for the TransformSepia command class.
 */
public class TransformSepiaTest {
  Image image1;
  ImageCollection collection;
  TransformSepia transformSepia;

  @Test
  public void init1() {
    this.image1 = new Image("res/dogs.ppm");
    this.collection = new ImageCollection();
    assertEquals(this.image1, this.image1);

  }

  @Test
  public void testTransformSepia() {
    init1();
    this.collection.getHashMap().put("OG dogs", this.image1);
    assertEquals(172, this.image1.getPixel(0, 0).getR());
    assertEquals(162, this.image1.getPixel(0, 0).getG());
    assertEquals(150, this.image1.getPixel(0, 0).getB());
    assertEquals(163, this.image1.getPixel(0, 0).getLuma());

    this.transformSepia = new TransformSepia("OG dogs", "new dogs", this.collection);
    assertEquals(220, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getR());
    assertEquals(196, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getG());
    assertEquals(152, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getB());
    assertEquals(2, this.collection.getHashMap().size());
  }
}
