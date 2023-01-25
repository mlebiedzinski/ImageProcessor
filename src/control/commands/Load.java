package control.commands;

import control.ICommand;
import model.Image;
import model.ImageCollection;

/**
 * This is a class for the load command. It implements ICommand.
 * Loads an image from the specified path and refers it to henceforth in the program
 * by the given image name.
 */
public class Load implements ICommand {

  /**
   * This is the constructor for Load. It takes a filename, constructs the new Image based off
   * of that file, then adds it to the map.
   *
   * @param imgPath  name of file
   * @param imgName  name of image the user wants
   * @param imageMap map of images
   */
  public Load(String imgPath, String imgName, ImageCollection imageMap) {

    imageMap.getHashMap().put(imgName, new Image(imgPath));

    System.out.println("File loaded!");

  }

}
