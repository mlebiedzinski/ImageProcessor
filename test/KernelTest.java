import org.junit.Test;

import model.Kernel;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for Kernel.
 */
public class KernelTest {

  Kernel kernel;

  @Test
  public void init() {
    this.kernel = new Kernel(0.5);
    assertEquals(this.kernel, this.kernel);
  }

  @Test
  public void testKernelCons() {
    init();
    assertEquals(0.5, this.kernel.getMultipliedVal(), 0.0001);
  }

  @Test
  public void testGetMultipliedValue() {
    init();
    assertEquals(0.5, this.kernel.getMultipliedVal(), 0.0001);
  }
}
