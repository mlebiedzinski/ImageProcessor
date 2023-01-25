package model;

/**
 * This interface is here primarily for future assignments and variations of the
 * pixel class.
 */
public interface IPixel {

  /**
   * This method brightens or darkens the pixel according to the inputted constant.
   *
   * @param constant int to brighten or darken the pixel by
   * @return new brighter/darker pixel
   */
  Pixel brightenOrDarken(int constant);

  /**
   * This method is a generic component value adjuster so that it can be
   * called for adjusting the rgb fields to r or g or b, value, etc. so that all RGB
   * values are equal to the specified component's value.
   *
   * @param component value to change RGB values to
   * @return new pixel with adjusted component values
   * @throws IllegalArgumentException if the component value is invalid
   */
  Pixel convertPixelGreyscale(String component);

  /**
   * This helper is for the color transformation to greyscale. It is left as public since
   * it acts as a helper that is called in the TransformGreyscale command class.
   *
   * @return new pixel with greyscale values
   */
  Pixel transformPixelGreyscale();

  /**
   * This helper is for the color transformation to sepia. It is left as public since
   * it acts as a helper that is called in the TransformSepia command class.
   *
   * @return new pixel with sepia values
   */
  Pixel transformPixelSepia();

  /**
   * This getter method returns the pixel's red value and is public so the red value can be
   * accessed by other classes.
   *
   * @return Red value of this pixel
   */
  int getR();

  /**
   * This getter method returns the pixel's green value and is public so the green value can be
   * accessed by other classes.
   *
   * @return Green value of this pixel
   */
  int getG();

  /**
   * This getter method returns the pixel's blue value and is public so the blue value can be
   * accessed by other classes.
   *
   * @return Blue value of this pixel
   */
  int getB();

  /**
   * This getter method returns the pixel's value and is public so the value can be
   * accessed by other classes.
   *
   * @return Value of this pixel (maximum value of the three components for each pixel)
   */
  int getValue();

  /**
   * This getter method returns the pixel's intensity value and is public so the intensity can be
   * accessed by other classes.
   *
   * @return Intensity of this pixel
   */
  int getIntensity();

  /**
   * This getter method returns the pixel's luma value and is public so the luma can be
   * accessed by other classes.
   *
   * @return Luma of this pixel
   */
  int getLuma();


}
