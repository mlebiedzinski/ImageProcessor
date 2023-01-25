package control.commands;

import control.ICommand;
import model.Image;
import model.ImageCollection;
import model.Pixel;

/**
 * This is a class for the vertical flip command. It implements ICommand.
 * Using this will flip the image vertically.
 */
public class VerticalFlip implements ICommand {

  /**
   * This is the constructor for VerticalFlip; it takes in the original and new image's names
   * as well as the maps. It goes over 2D pixel array to flip the image vertically by reassigning
   * pixels.
   *
   * @param imgName  name of image
   * @param destName name of newly flipped image
   * @param imageMap map of images
   */
  public VerticalFlip(String imgName, String destName, ImageCollection imageMap) {

    Image imageCopy = new Image(imageMap.getHashMap().get(imgName).getPixelArray());

    for (int i = 0; i < imageCopy.getHeight() / 2; i++) {
      for (int j = 0; j < imageCopy.getWidth(); j++) {
        Pixel holder = imageCopy.getPixel((imageCopy.getHeight() - i - 1), j);
        imageCopy.setNewPixel((imageCopy.getHeight() - i - 1), j, i, j);
        imageCopy.setNewPixeltoPixel(i, j, holder);
      }
    }

    imageMap.updateMap(destName, imageCopy);

    System.out.println("Vertically-flipped image was created.");
  }
}
