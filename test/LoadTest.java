import org.junit.Test;

import control.commands.Load;
import model.Image;
import model.ImageCollection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a test class for the Load command class.
 */
public class LoadTest {

  Image imagePPM;
  Image imagePNG;
  Image imageJPG;
  Image imageBMP;

  ImageCollection collection;

  Load load;

  @Test
  public void init1() {
    this.imagePPM = new Image("res/dogs.ppm");
    this.imagePNG = new Image("res/dogs.png");
    this.imageJPG = new Image("res/dogs.jpg");
    this.imageBMP = new Image("res/dogs.bmp");
    this.collection = new ImageCollection();
    assertEquals(this.imagePPM, this.imagePPM);
  }

  @Test
  public void testLoad() {
    init1();
    // loading a ppm
    this.load = new Load("res/dogs.ppm", "dogsPPM", collection);
    assertEquals(1, this.collection.getHashMap().size());
    assertTrue(this.collection.getHashMap().containsKey("dogsPPM"));

    // loading a png
    this.load = new Load("res/dogs.png", "dogsPNG", collection);
    assertEquals(2, this.collection.getHashMap().size());
    assertTrue(this.collection.getHashMap().containsKey("dogsPNG"));

    // loading a jpg
    this.load = new Load("res/dogs.jpg", "dogsJPG", collection);
    assertEquals(3, this.collection.getHashMap().size());
    assertTrue(this.collection.getHashMap().containsKey("dogsJPG"));

    // loading a bmp
    this.load = new Load("res/dogs.bmp", "dogsBMP", collection);
    assertEquals(4, this.collection.getHashMap().size());
    assertTrue(this.collection.getHashMap().containsKey("dogsBMP"));
  }
}