import org.junit.Test;

import control.commands.BrightenOrDarken;
import model.Image;
import model.ImageCollection;

import static org.junit.Assert.assertEquals;


/**
 * This is a test class for the BrightenOrDarken command class.
 */
public class BrightenOrDarkenTest {

  Image image1;
  ImageCollection collection;
  BrightenOrDarken brightenOrDarken;


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
    this.brightenOrDarken = new BrightenOrDarken("OG dogs", "new dogs",
            10, this.collection);
    assertEquals(182, this.collection.getHashMap().get("new dogs").getPixel(0, 0).getR());
    assertEquals(2, this.collection.getHashMap().size());

    // checking og is not mutated
    assertEquals(172, this.collection.getHashMap().get("OG dogs").getPixel(0, 0).getR());
  }

}
