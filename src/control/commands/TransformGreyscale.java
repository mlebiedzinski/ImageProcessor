package control.commands;

import control.ICommand;
import model.Image;
import model.ImageCollection;

/**
 * This is a class for the greyscale command. It implements ICommand.
 * It color-transforms the image so all RGB values are set to the luma.
 */
public class TransformGreyscale implements ICommand {

  /**
   * This is the constructor for TransformGreyscale. It goes through the image and
   * calls the helper transformPixelGreyscale in pixel to adjust the RGB values for each pixel.
   *
   * @param imgName  name of original image
   * @param destName name of new greyscale image
   * @param imageMap map of images
   */
  public TransformGreyscale(String imgName, String destName, ImageCollection imageMap) {

    Image imageCopy = new Image(imageMap.getHashMap().get(imgName).getPixelArray());

    for (int i = 0; i < imageCopy.getHeight(); i++) {
      for (int j = 0; j < imageCopy.getWidth(); j++) {
        imageCopy.setNewPixeltoPixel(i, j, imageCopy.getPixel(i, j).transformPixelGreyscale());
      }
    }
    imageMap.updateMap(destName, imageCopy);

    System.out.println("Greyscale image was created.");
  }
}
