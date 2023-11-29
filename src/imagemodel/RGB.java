package imagemodel;

/**
 * The `RGB` class represents a pixel with red, green, and blue color channels.
 */
public class RGB {

  private int red;
  private int green;
  private int blue;

  /**
   * Constructs an `RGB` object with the specified red, green, and blue values.
   *
   * @param red   The red component of the pixel.
   * @param green The green component of the pixel.
   * @param blue  The blue component of the pixel.
   */
  public RGB(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Set values to 0.
   */
  public RGB() {
    this.red = 0;
    this.green = 0;
    this.blue = 0;
  }

  /**
   * Gets the value of the specified color channel (0 for red, 1 for green, 2 for blue).
   *
   * @param channel The channel index (0 for red, 1 for green, 2 for blue).
   * @return The value of the specified color channel.
   * @throws IllegalArgumentException if an invalid channel is specified.
   */
  public int getPixel(int channel) throws IllegalArgumentException {
    if (channel == 0) {
      return this.red;
    } else if (channel == 1) {
      return this.green;
    } else if (channel == 2) {
      return this.blue;
    }
    throw new IllegalArgumentException("Invalid channel for pixel!");
  }

  /**
   * Sets the value of the specified color channel (0 for red, 1 for green, 2 for blue).
   *
   * @param channel The channel index (0 for red, 1 for green, 2 for blue).
   * @param value   The new value for the color channel.
   * @throws IllegalArgumentException if an invalid channel is specified.
   */
  public void setPixel(int channel, int value) throws IllegalArgumentException {
    if (channel == 0) {
      this.red = value;
    } else if (channel == 1) {
      this.green = value;
    } else if (channel == 2) {
      this.blue = value;
    } else {
      throw new IllegalArgumentException("Invalid channel for pixel!");
    }
  }
}
