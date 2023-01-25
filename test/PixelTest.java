import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for the Pixel class.
 */
public class PixelTest {
  Pixel pixel1;
  Pixel pixel2;
  Pixel pixel3;

  // initializes pixels for testing
  @Test
  public void init1() {
    this.pixel1 = new Pixel(30, 50, 45);
    this.pixel3 = new Pixel(0, 50, 255);
    assertEquals(this.pixel1, this.pixel1);
  }

  @Test
  public void testConstructor() {
    init1();
    assertEquals(42, this.pixel1.getIntensity());
    assertEquals(45, this.pixel1.getLuma());
  }

  /**
   * tests for the IllegalArgumentException in the constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException() {
    new Pixel(2, 0, 256);
    new Pixel(400, 50, 50);
    new Pixel(20, -87, 10);
  }

  @Test
  public void testBrightenOrDarken() {
    init1();
    this.pixel2 = this.pixel1.brightenOrDarken(10);
    assertEquals(40, this.pixel2.getR());
    assertEquals(60, this.pixel2.getG());
    assertEquals(55, this.pixel2.getB());
    assertEquals(60, this.pixel2.getValue());
    assertEquals(52, this.pixel2.getIntensity());
    assertEquals(55, this.pixel2.getLuma());

    this.pixel2 = this.pixel2.brightenOrDarken(-20);
    assertEquals(20, this.pixel2.getR());
    assertEquals(40, this.pixel2.getG());
    assertEquals(35, this.pixel2.getB());

    this.pixel3 = this.pixel3.brightenOrDarken(-51);
    assertEquals(0, this.pixel3.getR());
    assertEquals(0, this.pixel3.getG());
    assertEquals(204, this.pixel3.getB());

    this.pixel3 = this.pixel3.brightenOrDarken(55);
    assertEquals(55, this.pixel3.getR());
    assertEquals(55, this.pixel3.getG());
    assertEquals(255, this.pixel3.getB());
  }


  @Test
  public void testConvertPixelGreyscale() {
    init1();
    assertEquals(30, this.pixel1.getR());
    assertEquals(50, this.pixel1.getG());
    assertEquals(45, this.pixel1.getB());

    Pixel pixel1New = this.pixel1.convertPixelGreyscale("red");
    assertEquals(30, pixel1New.getR());
    assertEquals(30, pixel1New.getG());
    assertEquals(30, pixel1New.getB());

    init1();
    Pixel pixel1New2 = this.pixel1.convertPixelGreyscale("luma");
    assertEquals(45, pixel1New2.getR());
    assertEquals(45, pixel1New2.getG());
    assertEquals(45, pixel1New2.getB());
  }

  @Test
  public void testGetR() {
    init1();
    assertEquals(30, this.pixel1.getR());
  }

  @Test
  public void testGetG() {
    init1();
    assertEquals(50, this.pixel1.getG());
  }

  @Test
  public void testGetB() {
    init1();
    assertEquals(45, this.pixel1.getB());
  }

  @Test
  public void testGetValue() {
    init1();
    assertEquals(50, this.pixel1.getValue());
  }

  @Test
  public void testGetIntensity() {
    init1();
    assertEquals(42, this.pixel1.getIntensity());
  }

  @Test
  public void testGetLuma() {
    init1();
    assertEquals(45, this.pixel1.getLuma());
  }

}
