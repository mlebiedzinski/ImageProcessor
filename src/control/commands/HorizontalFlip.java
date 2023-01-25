package control.commands;

import control.ICommand;
import model.Image;
import model.ImageCollection;
import model.Pixel;

/**
 * This is a class for the horizontal flip command. It implements ICommand.
 * Using this will flip the image horizontally.
 */
public class HorizontalFlip implements ICommand {

  /**
   * This is the constructor for HorizontalFlip; it takes in the original and new image's names
   * as well as the maps. It goes over 2D pixel array to flip the image horizontally by reassigning
   * pixels.
   *
   * @param imgName  name of original image
   * @param destName name of newly flipped image
   * @param imageMap map of images
   */
  public HorizontalFlip(String imgName, String destName, ImageCollection imageMap) {

    Image imageCopy = new Image(imageMap.getHashMap().get(imgName).getPixelArray());

    for (int i = 0; i < imageCopy.getHeight(); i++) {
      for (int j = 0; j < imageCopy.getWidth() / 2; j++) {
        Pixel holder = imageCopy.getPixel(i, imageCopy.getWidth() - j - 1);
        imageCopy.setNewPixel(i, imageCopy.getWidth() - j - 1, i, j);
        imageCopy.setNewPixeltoPixel(i, j, holder);
      }
    }

    imageMap.updateMap(destName, imageCopy);

    System.out.println("Horizontally-flipped image was created.");
  }

}
