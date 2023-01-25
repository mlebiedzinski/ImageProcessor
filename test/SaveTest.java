import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import control.commands.Load;
import control.commands.Save;
import control.commands.VerticalFlip;
import model.ImageCollection;

import static org.junit.Assert.assertTrue;

/**
 * This is a test class for the Save command class.
 */
public class SaveTest {

  ImageCollection collection;
  Load loadpng;
  Load loadppm;
  Load loadjpg;
  Load loadbmp;
  Save save;


  @Test
  public void testSave() {
    ImageCollection collection = new ImageCollection();
    Path pathPPM = Paths.get("res/dogssave.ppm");
    Path pathPNG = Paths.get("res/dogssave.png");
    Path pathJPG = Paths.get("res/dogssave.jpg");
    Path pathBMP = Paths.get("res/dogssave.bmp");

    // saving a ppm
    assertTrue(Files.notExists(pathPPM));
    this.loadppm = new Load("res/dogs.ppm", "dogsPPM", collection);
    this.save = new Save("res/dogssave.ppm", "dogsPPM", collection);
    assertTrue(Files.exists(pathPPM));

    //saving a png
    assertTrue(Files.notExists(pathPNG));
    this.loadpng = new Load("res/dogs.png", "dogsPNG", collection);
    this.save = new Save("res/dogssave.png", "dogsPNG", collection);
    assertTrue(Files.exists(pathPNG));

    //saving a jpg
    assertTrue(Files.notExists(pathJPG));
    this.loadjpg = new Load("res/dogs.jpg", "dogsJPG", collection);
    this.save = new Save("res/dogssave.jpg", "dogsJPG", collection);
    assertTrue(Files.exists(pathJPG));

    //saving a bmp
    assertTrue(Files.notExists(pathBMP));
    this.loadbmp = new Load("res/dogs.bmp", "dogsBMP", collection);
    this.save = new Save("res/dogssave.bmp", "dogsBMP", collection);
    assertTrue(Files.exists(pathBMP));

    // saving a file as different type than the one it loaded in as (PPM -> PNG)
    this.save = new Save("res/dogssaveNEW.png", "dogsPPM", collection);
    Path pathPNGnew = Paths.get("res/dogssaveNEW.png");
    assertTrue(Files.exists(pathPNGnew));

    // overwriting a file after changes
    VerticalFlip vf = new VerticalFlip("dogsJPG", "dogsJPGflipped", collection);
    this.save = new Save("res/dogsave.jpg", "dogsJPGflipped", collection);
    assertTrue(Files.exists(pathJPG));

  }
}