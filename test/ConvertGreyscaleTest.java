import org.junit.Test;

import control.commands.ConvertGreyscale;
import model.Image;
import model.ImageCollection;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for the ConvertGreyscale command class.
 */
public class ConvertGreyscaleTest {

  Image image1;
  ImageCollection collection;
  ConvertGreyscale convertGreyscale;


  @Test
  public void init1() {
    this.image1 = new Image("res/dogs.ppm");
    this.collection = new ImageCollection();
    assertEquals(this.image1, this.image1);
  }

  @Test
  public void testBrightenOrDarken() {
    init1();
    this.collection.getHashMap().put("OG dogs", this.image1);
    assertEquals(172, this.image1.getPixel(0, 0).getR());
    assertEquals(162, this.image1.getPixel(0, 0).getG());
    assertEquals(150, this.image1.getPixel(0, 0).getB());

    this.convertGreyscale = new ConvertGreyscale("OG dogs", "new dogs",
            "red", this.collection);

    assertEquals(172, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getR());
    assertEquals(172, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getG());
    assertEquals(172, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getB());
    assertEquals(2, this.collection.getHashMap().size());

    // checking og is not mutated
    assertEquals(172, this.collection.getHashMap().get("OG dogs").getPixel(0, 0).getR());
    assertEquals(162, this.collection.getHashMap().get("OG dogs").getPixel(0, 0).getG());
    assertEquals(150, this.collection.getHashMap().get("OG dogs").getPixel(0, 0).getB());
  }
}
