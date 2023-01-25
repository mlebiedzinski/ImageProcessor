package control;

import java.io.IOException;

/**
 * This is an interface for controller..
 */
public interface ImageController {

  /**
   * This is the primary method in the controller that
   * communicates with the model to execute actions in the program.
   *
   * @throws IOException if null values are present.
   */
  void execute() throws IOException;

}