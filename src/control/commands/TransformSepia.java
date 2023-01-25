package control.commands;

import control.ICommand;
import model.Image;
import model.ImageCollection;

/**
 * This is a class for the sepia command. It implements ICommand.
 * It color-transforms the image, so it has a characteristic reddish brown tone
 */
public class TransformSepia implements ICommand {

  /**
   * This is the constructor for TransformSepia. It goes through the image and
   * calls the helper transformPixelSepia in pixel to adjust the RGB values for each pixel.
   *
   * @param imgName  name of original image
   * @param destName name of new sepia image
   * @param imageMap map of images
   */
  public TransformSepia(String imgName, String destName, ImageCollection imageMap) {

    Image imageCopy = new Image(imageMap.getHashMap().get(imgName).getPixelArray());

    for (int i = 0; i < imageCopy.getHeight(); i++) {
      for (int j = 0; j < imageCopy.getWidth(); j++) {
        imageCopy.setNewPixeltoPixel(i, j, imageCopy.getPixel(i, j).transformPixelSepia());
      }
    }
    imageMap.updateMap(destName, imageCopy);

    System.out.println("Sepia image was created.");
  }

}
