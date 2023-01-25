package model;

/**
 * This class represents a pixel, which has components like RGB (red, green blue).
 * This class was made to possibly be able to store additional components with more constructors.
 */
public class Pixel implements IPixel {

  // the red component
  private int r;
  // the green component
  private int g;
  // the blue component
  private int b;
  // value: the maximum value of the three components for each pixel
  private int value;
  // we made these 2 fields below ints instead of doubles, since if the user were to use
  // "intensity-component" or "luma-component", the intensity and luma values should be ints since
  // RGB can't handle decimal values, only whole numbers.

  // Intensity: the average of the three components for each pixel
  private int intensity;
  // Luma: the weighted sum of 0.2126r + 0.7152g + 0.0722b
  private int luma;

  /**
   * This is the pixel constructor that initializes the pixel fields like RGB
   * and value, etc.
   *
   * @param r red
   * @param g green
   * @param b blue
   * @throws IllegalArgumentException if rgb values are invalid
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (r > 255 || r < 0
            || g > 255 || g < 0
            || b > 255 || b < 0) {
      throw new IllegalArgumentException("Invalid rgb values");
    } else {
      this.r = r;
      this.g = g;
      this.b = b;
      this.value = Math.max(Math.max(this.r, this.g), this.b);

      // did typecasting here so we could correctly calculate the doubles
      // but then turn them into ints :)
      this.intensity = (int) Math.round(((double) (this.r + this.g + this.b) / 3));
      this.luma = (int) Math.round(((0.2126 * this.r) + (0.7152 * this.g) + (0.0722 * this.b)));
    }
  }

  /**
   * This method brightens or darkens the pixel according to the inputted constant.
   *
   * @param constant int to brighten or darken the pixel by
   * @return new brighter/darker pixel
   */
  public Pixel brightenOrDarken(int constant) {
    if (constant < 0) {
      return new Pixel(Math.max((this.r + constant), 0), Math.max((this.g + constant), 0),
              Math.max((this.b + constant), 0));
    }
    // if constant >= 0
    else {
      return new Pixel(Math.min((this.r + constant), 255), Math.min((this.g + constant), 255),
              Math.min((this.b + constant), 255));
    }
  }

  /**
   * This method is a generic component value adjuster so that it can be
   * called for adjusting the rgb fields to r or g or b, value, etc. so that all RGB
   * values are equal to the specified component's value.
   *
   * @param component value to change RGB values to
   * @return new pixel with adjusted component values
   * @throws IllegalArgumentException if the component value is invalid
   */
  public Pixel convertPixelGreyscale(String component) {
    Pixel newPixelHolder = new Pixel(this.r, this.g, this.b);
    switch (component) {
      case "red":
        newPixelHolder.g = this.r;
        newPixelHolder.b = this.r;
        break;
      case "green":
        newPixelHolder.r = this.g;
        newPixelHolder.b = this.g;
        break;
      case "blue":
        newPixelHolder.r = this.b;
        newPixelHolder.g = this.b;
        break;
      case "value":
        newPixelHolder.r = this.value;
        newPixelHolder.g = this.value;
        newPixelHolder.b = this.value;
        break;
      case "luma":
        newPixelHolder.r = this.luma;
        newPixelHolder.g = this.luma;
        newPixelHolder.b = this.luma;
        break;
      case "intensity":
        newPixelHolder.r = this.intensity;
        newPixelHolder.g = this.intensity;
        newPixelHolder.b = this.intensity;
        break;
      default:
        System.out.println("Default. There is no such component :(");
    }
    return new Pixel(newPixelHolder.r, newPixelHolder.g, newPixelHolder.b);
  }

  /**
   * This helper is for the color transformation to greyscale. It is left as public since
   * it acts as a helper that is called in the TransformGreyscale command class.
   *
   * @return new pixel with greyscale values
   */
  public Pixel transformPixelGreyscale() {
    return new Pixel(this.luma, this.luma, this.luma);
  }

  /**
   * This helper is for the color transformation to sepia. It is left as public since
   * it acts as a helper that is called in the TransformSepia command class.
   *
   * @return new pixel with sepia values
   */
  public Pixel transformPixelSepia() {
    int sepR = (int) ((this.r * 0.393) + (this.g * 0.769) + (this.b * 0.189));
    int sepG = (int) ((this.r * 0.349) + (this.g * 0.686) + (this.b * 0.168));
    int sepB = (int) ((this.r * 0.272) + (this.g * 0.534) + (this.b * 0.131));

    int goodR = clamp(sepR);
    int goodG = clamp(sepG);
    int goodB = clamp(sepB);
    return new Pixel(goodR, goodG, goodB);
  }

  // helper to clamp values to 0 or 255, it exists to avoid some code duplication.
  private int clamp(int x) {
    if (x < 0) {
      x = 0;
    } else if (x > 255) {
      x = 255;
    }
    return x;
  }


  /**
   * This getter method returns the pixel's red value and is public so the red value can be
   * accessed by other classes.
   *
   * @return Red value of this pixel
   */
  public int getR() {
    return this.r;
  }

  /**
   * This getter method returns the pixel's green value and is public so the green value can be
   * accessed by other classes.
   *
   * @return Green value of this pixel
   */
  public int getG() {
    return this.g;
  }

  /**
   * This getter method returns the pixel's blue value and is public so the blue value can be
   * accessed by other classes.
   *
   * @return Blue value of this pixel
   */
  public int getB() {
    return this.b;
  }

  /**
   * This getter method returns the pixel's value and is public so the value can be
   * accessed by other classes.
   *
   * @return Value of this pixel (maximum value of the three components for each pixel)
   */
  public int getValue() {
    return this.value;
  }

  /**
   * This getter method returns the pixel's intensity value and is public so the intensity can be
   * accessed by other classes.
   *
   * @return Intensity of this pixel
   */
  public int getIntensity() {
    return this.intensity;
  }

  /**
   * This getter method returns the pixel's luma value and is public so the luma can be
   * accessed by other classes.
   *
   * @return Luma of this pixel
   */
  public int getLuma() {
    return this.luma;
  }
}
