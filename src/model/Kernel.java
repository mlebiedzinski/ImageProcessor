package model;

/**
 * This class represents a singular cell in a Kernel's 2D array. It only has one field, which is
 * the (originally a fraction) value that is multiplied with a pixel's R/G/B value.
 */
public class Kernel {

  private final double multipliedVal;

  /**
   * This is the constructor. It only has one field, the multiplied value.
   *
   * @param multipliedVal value that is multiplied with a pixel's R/G/B value.
   */
  public Kernel(double multipliedVal) {
    this.multipliedVal = multipliedVal;
  }


  /**
   * This getter method returns the multiplied value of the cell in the kernel array. It is public
   * so other classes like Sharpen or Blur can access the multiplied value.
   *
   * @return multiplied value
   */
  public double getMultipliedVal() {
    return multipliedVal;
  }
}

