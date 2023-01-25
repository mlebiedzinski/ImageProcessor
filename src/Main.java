
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import control.ControllerGUI;
import control.ImageControllerImpl;
import model.ImageCollection;
import view.IView;
import view.ImageGUI;

/**
 * A main class to run the program.
 */
public class Main {

  /**
   * The main method to run the whole program.
   *
   * @param args input arguments
   */
  public static void main(String[] args) {

    if (args.length == 0) {
      ImageCollection collection = new ImageCollection();
      IView view = new ImageGUI();
      ControllerGUI controllerGUI = new ControllerGUI(collection, view);
      controllerGUI.reSetup();

    } else if (args[0].equals("-text")) {

      System.out.println("enter command");

      ImageCollection imageCollection = new ImageCollection();
      ImageControllerImpl imageController = new ImageControllerImpl(imageCollection,
              new InputStreamReader(System.in));

      try {
        imageController.execute();

      } catch (IllegalArgumentException e) {
        throw new IllegalStateException(e.getMessage());

      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    } else if (args[0].equals("-file")) {

      ImageCollection imageCollection = new ImageCollection();
      ImageControllerImpl imageController = new ImageControllerImpl(imageCollection,
              new StringReader(args[1]));

      try {
        imageController.execute();
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException(e.getMessage());

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}


