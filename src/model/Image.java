package model;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * An Image represents a full image and consists of a 2d array of pixels.
 */
public class Image implements IImage {
  private Scanner sc;
  private int width;
  private int height;
  private int maxValue;

  private String description;

  // we made this public so it would compile with our tests so we could check
  // individual pixels.
  private Pixel[][] pixels;

  /**
   * This is the constructor for Image; it takes in a filename and calls
   * readPPM to read the file and initialize the 2d array of pixels.
   *
   * @param filename name of the file the user inputs
   */
  public Image(String filename) {

    String last3 = "";

    if (filename.length() >= 3) {
      last3 = filename.substring(filename.length() - 3);
    }

    if (last3.equals("ppm")) {

      if (ImageUtil.readPPM(filename) == null) {
        throw new IllegalArgumentException("Scanner is null.");
      } else {
        sc = ImageUtil.readPPM(filename);
        this.description = sc.next();
        this.width = sc.nextInt();
        this.height = sc.nextInt();
        this.maxValue = sc.nextInt();
        this.pixels = new Pixel[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
          for (int j = 0; j < this.width; j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            this.pixels[i][j] = new Pixel(r, g, b);
          }
        }
      }
    } else {
      try {
        if (ImageIO.read(new FileInputStream(filename)) == null) {
          throw new IllegalArgumentException("file is null");
        } else {

          BufferedImage buffImage = ImageIO.read(new FileInputStream(filename));
          this.width = buffImage.getWidth();
          this.height = buffImage.getHeight();
          this.pixels = new Pixel[this.height][this.width];

          for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {

              int color = buffImage.getRGB(j, i); // j, i since it's in x, y format
              int r = (color & 0x00ff0000) >> 16;
              int g = (color & 0x0000ff00) >> 8;
              int b = color & 0x000000ff;

              this.pixels[i][j] = new Pixel(r, g, b);

              this.maxValue = 0;

              if (r > this.maxValue) {
                this.maxValue = r;
              }
              if (g > this.maxValue) {
                this.maxValue = g;
              }
              if (b > this.maxValue) {
                this.maxValue = b;
              }
            }
          }
        }
      } catch (IOException e) {
        throw new IllegalArgumentException("Unsupported file format");
      }
    }
  }

  /**
   * This is a second constructor that is used to make copies of images.
   *
   * @param pixels 2D pixel array
   */
  public Image(Pixel[][] pixels) {
    // description?
    this.width = pixels[0].length; // rows
    this.height = pixels.length; // columns
    this.pixels = new Pixel[this.height][this.width];
    this.maxValue = 0;

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int r = pixels[i][j].getR();
        int g = pixels[i][j].getG();
        int b = pixels[i][j].getB();
        this.pixels[i][j] = new Pixel(r, g, b);
        if (r > this.maxValue) {
          this.maxValue = r;
        }
        if (g > this.maxValue) {
          this.maxValue = g;
        }
        if (b > this.maxValue) {
          this.maxValue = b;
        }
      }
    }
  }

  /**
   * This getter method returns the image's width and is public so the width can be accessed by
   * other classes.
   *
   * @return image width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * This getter method returns the image's height and is public so the height can be accessed by
   * other classes.
   *
   * @return image height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * This getter method returns the max value and is public so the max value can be accessed by
   * other classes.
   *
   * @return max value
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * This getter method returns the pixel array and is public so the array can be accessed by
   * other classes.
   *
   * @return pixel array
   */
  public Pixel[][] getPixelArray() {
    return this.pixels;
  }

  /**
   * This getter method returns the pixel at a given i, j location. It's public so the pixel at a
   * specific location can be accessed in other classes and also for testing purposes.
   *
   * @param i i of a pixel
   * @param j j of a pixel
   * @return pixel
   */
  public Pixel getPixel(int i, int j) {
    return this.pixels[i][j];
  }

  /**
   * This method sets the new Pixel at a given location equal to a pixel at another given location.
   * It's public so classes like TransformSepia can access it.
   *
   * @param iOld old i for a pixel
   * @param jOld old j for a pixel
   * @param iNew new i for a pixel
   * @param jNew new j for a pixel
   */
  public void setNewPixel(int iOld, int jOld, int iNew, int jNew) {
    this.pixels[iOld][jOld] = this.pixels[iNew][jNew];
  }

  /**
   * This method sets the new Pixel at a given location equal to a given pixel.
   * It's public so classes like TransformSepia can access it.
   *
   * @param iOld  old i for a pixel
   * @param jOld  old j for a pixel
   * @param pixel new pixel
   */
  public void setNewPixeltoPixel(int iOld, int jOld, Pixel pixel) {
    this.pixels[iOld][jOld] = pixel;
  }

  /**
   * This method create a string in the proper format of a ppm file.
   *
   * @param imgPath the file path of the image
   * @returns a string in proper format.
   */
  public String imageToString(String imgPath) {
    StringBuilder start = new StringBuilder("P3\n");
    start.append("# ").append(imgPath).append("\n");
    start.append(this.getWidth()).append(" ").append(this.getHeight()).append("\n")
            .append(this.getMaxValue()).append("\n");
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        start.append(this.pixels[i][j].getR()).append("\n").append(this.pixels[i][j].getG())
                .append("\n").append(this.pixels[i][j].getB()).append("\n");
      }
    }
    return start.toString();
  }

  /**
   * This method uses the list of pixels to convert the image into a buffered image.
   *
   * @returns a buffered image .
   */
  public BufferedImage toBufferedImage() {

    BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {

        int color = this.pixels[i][j].getB() + (this.pixels[i][j].getG() << 8) +
                (this.pixels[i][j].getR() << 16) + (255 << 24);

        img.setRGB(j, i, color);
      }
    }
    return img;
  }
}