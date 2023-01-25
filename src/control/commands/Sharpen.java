package control.commands;

import control.ICommand;
import model.Image;
import model.ImageCollection;
import model.Kernel;
import model.Pixel;

/**
 * This is a class for the sharpen command. It implements ICommand.
 * Sharpening accentuates edges (the boundaries between regions of high contrast).
 */
public class Sharpen implements ICommand {

  /**
   * This is the constructor for Sharpen. It goes through the image and
   * using an initialized array that represents a kernel, it computes new RGB values for
   * each pixel depending on its neighbor's RGB values. It uses the filter provided in the
   * assignment description.
   *
   * @param imgName  name of original image
   * @param destName name of new sharpened image
   * @param imageMap map of images
   */
  public Sharpen(String imgName, String destName, ImageCollection imageMap) {

    Image imageCopy = new Image(imageMap.getHashMap().get(imgName).getPixelArray());

    Kernel[][] kernel = new Kernel[5][5];

    kernel[0][0] = new Kernel(-0.125);
    kernel[0][1] = new Kernel(-0.125);
    kernel[0][2] = new Kernel(-0.125);
    kernel[0][3] = new Kernel(-0.125);
    kernel[0][4] = new Kernel(-0.125);
    kernel[1][0] = new Kernel(-0.125);
    kernel[1][1] = new Kernel(0.25);
    kernel[1][2] = new Kernel(0.25);
    kernel[1][3] = new Kernel(0.25);
    kernel[1][4] = new Kernel(-0.125);
    kernel[2][0] = new Kernel(-0.125);
    kernel[2][1] = new Kernel(0.25);
    kernel[2][2] = new Kernel(1.0);
    kernel[2][3] = new Kernel(0.25);
    kernel[2][4] = new Kernel(-0.125);
    kernel[3][0] = new Kernel(-0.125);
    kernel[3][1] = new Kernel(0.25);
    kernel[3][2] = new Kernel(0.25);
    kernel[3][3] = new Kernel(0.25);
    kernel[3][4] = new Kernel(-0.125);
    kernel[4][0] = new Kernel(-0.125);
    kernel[4][1] = new Kernel(-0.125);
    kernel[4][2] = new Kernel(-0.125);
    kernel[4][3] = new Kernel(-0.125);
    kernel[4][4] = new Kernel(-0.125);

    for (int i = 0; i < imageCopy.getHeight(); i++) {
      for (int j = 0; j < imageCopy.getWidth(); j++) {

        int totalR = 0;
        int totalG = 0;
        int totalB = 0;

        for (int kerneli = 0; kerneli < 5; kerneli++) {
          for (int kernelj = 0; kernelj < 5; kernelj++) {
            try {
              double r = (imageCopy.getPixel(kerneli + (i - 1), kernelj + (j - 1)).getR()
                      * kernel[kerneli][kernelj].getMultipliedVal());

              double g = (imageCopy.getPixel(kerneli + (i - 1), kernelj + (j - 1)).getG()
                      * kernel[kerneli][kernelj].getMultipliedVal());
              double b = (imageCopy.getPixel(kerneli + (i - 1), kernelj + (j - 1)).getB()
                      * kernel[kerneli][kernelj].getMultipliedVal());


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
        } else if (totalR < 0) {
          totalR = 0;
        }
        if (totalG > 255) {
          totalG = 255;
        } else if (totalG < 0) {
          totalG = 0;
        }
        if (totalB > 255) {
          totalB = 255;
        } else if (totalB < 0) {
          totalB = 0;
        }
        imageCopy.setNewPixeltoPixel(i, j, new Pixel(totalR, totalG, totalB));
      }
    }

    imageMap.updateMap(destName, imageCopy);

    System.out.println("Sharpened image was created.");
  }
}
