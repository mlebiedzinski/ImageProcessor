import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import control.ImageControllerImpl;
import control.commands.Blur;
import control.commands.Load;
import model.Image;
import model.ImageCollection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a test class for the ImageController class.
 */
public class ImageControllerImplTest {
  Readable readableNull;

  ImageCollection collection;

  ImageControllerImpl controller;
  StringReader sb;

  @Test
  public void init1() {
    this.collection = new ImageCollection();
    this.sb = new StringReader("load");
    assertEquals(this.sb, this.sb);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConsIllegalArgCollection() {
    this.controller = new ImageControllerImpl(this.collection, new StringReader("load"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConsIllegalArgReadable() {
    this.collection = new ImageCollection();
    this.controller = new ImageControllerImpl(this.collection, this.readableNull);
  }

  @Test
  public void testCons() {
    init1();
    this.controller = new ImageControllerImpl(this.collection, this.sb);
    assertEquals(this.controller, this.controller);
  }


  @Test
  public void testExecute() throws IOException {

    collection = new ImageCollection();
    controller = new ImageControllerImpl(this.collection,
            new StringReader("load res/dogs.ppm dogs sepia dogs dogsSep"));

    this.controller.execute();

    assertTrue(this.collection.getHashMap().containsKey("dogs"));
    assertTrue(this.collection.getHashMap().containsKey("dogsSep"));
  }


  @Test
  public void testScript() throws IOException {
    this.collection = new ImageCollection();
    this.controller = new ImageControllerImpl(this.collection,
            new StringReader("load res/dogs.ppm dogs"));

    this.controller.execute();

    assertTrue(this.collection.getHashMap().containsKey("dogs"));
    assertEquals(1, this.collection.getHashMap().size());
  }


  @Test
  public void testScriptTextFile() throws IOException {
    this.collection = new ImageCollection();
    this.controller = new ImageControllerImpl(this.collection,
            new StringReader("testScriptIgnore.txt"));

    this.controller.execute();

    Image image1 = new Image("res/dogs.png");
    ImageCollection collectionDup = new ImageCollection();
    Load load = new Load("res/dogs.png", "dogsDup", collectionDup);
    Blur blur = new Blur("dogsDup", "dogsDupBlur", collectionDup);

    // loading in the image dogsBlurTestScript, which was saved from the script file being parsed
    Load loadSecond = new Load("res/dogsBlurTestScript.png",
            "dogsBlurTestScript", collectionDup);

    // checking the pixel RGB values are the same from the blurred image in the program and the
    // image we loaded in that was saved from the parsed script file
    assertEquals(collectionDup.getHashMap().get("dogsBlurTestScript").
                    getPixel(100, 100).getR(),
            collectionDup.getHashMap().get("dogsDupBlur").getPixel(100, 100).getR());
    assertEquals(collectionDup.getHashMap().get("dogsBlurTestScript").
                    getPixel(100, 100).getG(),
            collectionDup.getHashMap().get("dogsDupBlur").getPixel(100, 100).getG());
    assertEquals(collectionDup.getHashMap().get("dogsBlurTestScript").
                    getPixel(100, 100).getB(),
            collectionDup.getHashMap().get("dogsDupBlur").getPixel(100, 100).getB());
    assertEquals(collectionDup.getHashMap().get("dogsBlurTestScript").
                    getPixel(200, 150).getR(),
            collectionDup.getHashMap().get("dogsDupBlur").getPixel(200, 150).getR());

    assertEquals(collectionDup.getHashMap().get("dogsBlurTestScript").getHeight(),
            collectionDup.getHashMap().get("dogsDupBlur").getHeight());
    assertEquals(collectionDup.getHashMap().get("dogsBlurTestScript").getWidth(),
            collectionDup.getHashMap().get("dogsDupBlur").getWidth());
  }


}
