import org.junit.Test;

import java.util.HashMap;

import control.ControllerGUI;
import control.commands.TransformSepia;
import model.Image;
import model.ImageCollection;
import view.IView;
import view.ImageGUI;

import static org.junit.Assert.assertEquals;

/**
 * A test class for the GUI Controller.
 */
public class ControllerGUITest {

  ImageCollection collection;
  ImageCollection collection2;
  IView view;
  ControllerGUI controller;
  HashMap<String, String> commandParameters;


  @Test
  public void init() {
    this.collection = new ImageCollection();
    this.collection2 = new ImageCollection();
    this.view = new ImageGUI();

    assertEquals(this.collection, collection);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerGUINullCollectionException() {
    this.collection = new ImageCollection();
    this.controller = new ControllerGUI(this.collection, this.view);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerGUINullViewException() {
    this.view = new ImageGUI();
    this.controller = new ControllerGUI(this.collection, this.view);
  }

  @Test
  public void testProcessCommand() {

    init();

    this.controller = new ControllerGUI(this.collection, this.view);

    Image image = new Image("res/dogs.png");
    this.collection.getHashMap().put("Loaded image", image);

    Image imageCompareBeforeSepia = new Image("res/dogs.png");
    this.collection2.getHashMap().put("imageCompareBeforeSepia", imageCompareBeforeSepia);
    TransformSepia sepia = new TransformSepia("imageCompareBeforeSepia",
            "imageCompareAfterSepia", this.collection2);

    this.commandParameters = new HashMap<>();
    this.commandParameters.put("command", "sepia");

    this.controller.processCommand(commandParameters);

    assertEquals(this.collection.getHashMap().get("Loaded image").getPixel(100, 100).getR(),
            this.collection2.getHashMap().get("imageCompareAfterSepia").
                    getPixel(100, 100).getR());
    assertEquals(this.collection.getHashMap().get("Loaded image").getPixel(250, 250).getG(),
            this.collection2.getHashMap().get("imageCompareAfterSepia").
                    getPixel(250, 250).getG());
  }

  /**
   * Testing that an action performed will produce the correct result.
   */
  @Test
  public void testActionPerformed() {
    init();

    this.controller = new ControllerGUI(this.collection, this.view);

    Image image = new Image("res/dogs.png");
    this.collection.getHashMap().put("Loaded image", image);

    Image imageCompareBeforeSepia = new Image("res/dogs.png");

    this.collection2.getHashMap().put("imageCompareBeforeSepia", imageCompareBeforeSepia);

    TransformSepia sepia = new TransformSepia("imageCompareBeforeSepia",
            "imageCompareAfterSepia", this.collection2);

    this.view.getCommandOptionsBoxFromCommandPanel().setSelectedItem("sepia");

    // testing execute
    this.view.getCommandAndInstructionPanel().getExecuteButton().doClick();

    assertEquals(this.collection.getHashMap().get("Loaded image").getPixel(100, 100).getR(),
            this.collection2.getHashMap().get("imageCompareAfterSepia").
                    getPixel(100, 100).getR());

  }

}
