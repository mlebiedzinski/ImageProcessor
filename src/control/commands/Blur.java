package control.commands;

import control.ICommand;
import model.Image;
import model.ImageCollection;
import model.Kernel;
import model.Pixel;

/**
 * This is a class for the blur command. It implements ICommand.
 * Using this will make the image blurry through the use of the Gaussian kernel filter.
 */
public class Blur implements ICommand {

  /**
   * This is the constructor for Blur. It does through the image and
   * uses a Kernel array to filter the image and computes new RGB values for
   * each pixel depending on its neighbor's RGB values. It uses the Gaussian filter provided in
   * the assignment description.
   *
   * @param imgName  image name
   * @param destName new name of image
   * @param imageMap map of images
   */
  public Blur(String imgName, String destName, ImageCollection imageMap) {

    Image imageCopy = new Image(imageMap.getHashMap().get(imgName).getPixelArray());

    Kernel[][] kernel = new Kernel[3][3];

    kernel[0][0] = new Kernel(0.0625);
    kernel[0][1] = new Kernel(0.125);
    kernel[0][2] = new Kernel(0.0625);
    kernel[1][0] = new Kernel(0.125);
    kernel[1][1] = new Kernel(0.25);
    kernel[1][2] = new Kernel(0.125);
    kernel[2][0] = new Kernel(0.0625);
    kernel[2][1] = new Kernel(0.125);
    kernel[2][2] = new Kernel(0.0625);

    for (int i = 0; i < imageCopy.getHeight(); i++) {
      for (int j = 0; j < imageCopy.getWidth(); j++) {

        int totalR = 0;
        int totalG = 0;
        int totalB = 0;

        for (int kerneli = 0; kerneli < 3; kerneli++) {
          for (int kernelj = 0; kernelj < 3; kernelj++) {
            try {

              double r = (imageCopy.getPixel(kerneli + (i - 1), kernelj + (j - 1)).getR()
                      * kernel[kerneli][kernelj].getMultipliedVal());
              double g = (imageCopy.getPixel(kerneli + (i - 1), kernelj + (j - 1)).getG()
                      * kernel[kerneli][kernelj].getMultipliedVal());
              double b = (imageCopy.getPixel(kerneli + (i - 1), kernelj + (j - 1)).getB()
                      * kernel[kerneli][kernelj].getMultipliedVal());

              // casting here so the doubles could be correctly calculated then turned into ints :)
              totalR = (int) (totalR + r);
              totalG = (int) (totalG + g);
              totalB = (int) (totalB + b);

            } catch (IndexOutOfBoundsException ignored) {
              // ignore if exception is thrown since those outside values will just not be
              // computed, which is what we want.
            }
          }
        }
        if (totalR > 255) {
          totalR = 255;
        }
        if (totalG > 255) {
          totalG = 255;
        }
        if (totalB > 255) {
          totalB = 255;
        }
        imageCopy.setNewPixeltoPixel(i, j, new Pixel(totalR, totalG, totalB));
      }
    }


    imageMap.updateMap(destName, imageCopy);

    System.out.println("Blurred image was created.");
  }
}
