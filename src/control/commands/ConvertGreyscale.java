package control.commands;

import control.ICommand;
import model.Image;
import model.ImageCollection;

/**
 * This is a class for the converting an image to greyscale using a
 * "____-component" command. It implements ICommand.
 */
public class ConvertGreyscale implements ICommand {

  /**
   * This is the constructor for ConvertGreyscale. It goes through the image and
   * calls the helper convertPixelGreyscale in Pixel to adjust the pixels' RGB values
   * depending on which component the user specified (so that all RGB
   * values are equal to the specified component's value).
   *
   * @param imgName   name of original image
   * @param destName  name of new greyscale image
   * @param component component specified by user
   * @param imageMap  map of images
   */
  public ConvertGreyscale(String imgName, String destName, String component,
                          ImageCollection imageMap) {

    Image imageCopy = new Image(imageMap.getHashMap().get(imgName).getPixelArray());

    for (int i = 0; i < imageCopy.getHeight(); i++) {
      for (int j = 0; j < imageCopy.getWidth(); j++) {
        imageCopy.setNewPixeltoPixel(i, j,
                imageCopy.getPixel(i, j).convertPixelGreyscale(component));
      }
    }

    imageMap.updateMap(destName, imageCopy);

    System.out.println("Greyscale image was created using specified component.");
  }

}