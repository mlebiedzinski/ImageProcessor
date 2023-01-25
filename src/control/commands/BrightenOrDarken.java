package control.commands;

import control.ICommand;
import model.Image;
import model.ImageCollection;

/**
 * This is a class for the brighten or darken command. It implements ICommand.
 * Using this will brighten the image by the given increment to create a new image.
 */
public class BrightenOrDarken implements ICommand {

  /**
   * This is the constructor for BrightenOrDarken. It goes through the image and
   * calls the helper brightenOrDarken in pixel to brighten or darken each pixel by the constant.
   *
   * @param imgName  image name
   * @param destName new name of image
   * @param constant constant to brighten or darken the image by
   * @param imageMap map of images
   */
  public BrightenOrDarken(String imgName, String destName, Integer constant,
                          ImageCollection imageMap) {

    Image imageCopy = new Image(imageMap.getHashMap().get(imgName).getPixelArray());

    for (int i = 0; i < imageCopy.getHeight(); i++) {
      for (int j = 0; j < imageCopy.getWidth(); j++) {
        imageCopy.setNewPixeltoPixel(i, j, imageCopy.getPixel(i, j).brightenOrDarken(constant));

      }
    }

    imageMap.updateMap(destName, imageCopy);

    System.out.println("Brightened or darkened image was created.");

  }
}
