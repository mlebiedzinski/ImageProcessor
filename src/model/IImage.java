package model;

import java.awt.image.BufferedImage;

/**
 * This interface represents an image.
 */
public interface IImage {

  /**
   * This getter method returns the image's width and is public so the width can be accessed by
   * other classes.
   *
   * @return image width
   */
  int getWidth();

  /**
   * This getter method returns the image's height and is public so the height can be accessed by
   * other classes.
   *
   * @return image height
   */
  int getHeight();

  /**
   * This getter method returns the max value and is public so the max value can be accessed by
   * other classes.
   *
   * @return max value
   */
  int getMaxValue();

  /**
   * This getter method returns the pixel array and is public so the array can be accessed by
   * other classes.
   *
   * @return pixel array
   */
  Pixel[][] getPixelArray();

  /**
   * This getter method returns the pixel at a given i, j location. It's public so the pixel at a
   * specific location can be accessed in other classes and also for testing purposes.
   *
   * @param i i of a pixel
   * @param j j of a pixel
   * @return pixel
   */
  Pixel getPixel(int i, int j);

  /**
   * This method sets the new Pixel at a given location equal to a pixel at another given location.
   * It's public so classes like TransformSepia can access it.
   *
   * @param iOld old i for a pixel
   * @param jOld old j for a pixel
   * @param iNew new i for a pixel
   * @param jNew new j for a pixel
   */
  void setNewPixel(int iOld, int jOld, int iNew, int jNew);

  /**
   * This method sets the new Pixel at a given location equal to a given pixel.
   * It's public so classes like TransformSepia can access it.
   *
   * @param iOld  old i for a pixel
   * @param jOld  old j for a pixel
   * @param pixel new pixel
   */
  void setNewPixeltoPixel(int iOld, int jOld, Pixel pixel);

  /**
   * This method create a string in the proper format of a ppm file.
   *
   * @param imgPath the file path of the image
   * @returns a string in proper format.
   */
  String imageToString(String imgPath);

  /**
   * This method uses the list of pixels to convert the image into a buffered image.
   *
   * @returns a buffered image .
   */
  BufferedImage toBufferedImage();
}
