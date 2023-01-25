import org.junit.Test;

import java.util.HashMap;

import model.Image;
import model.ImageCollection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a test class for the ImageCollection class.
 */
public class ImageCollectionTest {

  Image image1;
  ImageCollection collection;
  HashMap<String, Image> imageMap;

  @Test
  public void init1() {
    this.image1 = new Image("res/dogs.ppm");
    this.collection = new ImageCollection();
    assertEquals(this.image1, this.image1);
  }

  @Test
  public void testCons() {
    init1();
    this.collection.updateMap("dogs", this.image1);
    assertEquals(1, this.collection.getHashMap().size());
  }

  @Test
  public void testGetHashMap() {
    init1();
    imageMap = this.collection.getHashMap();
    this.collection.updateMap("dogs", this.image1);
    assertEquals(this.imageMap, this.collection.getHashMap());
  }

  @Test
  public void testUpdateMap() {
    init1();
    this.collection.updateMap("dogs", this.image1);
    assertTrue(this.collection.getHashMap().containsKey("dogs"));
    assertEquals(this.image1, this.collection.getHashMap().get("dogs"));
    assertEquals(1, this.collection.getHashMap().size());
  }

}
