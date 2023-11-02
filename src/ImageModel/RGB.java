package ImageModel;

public class RGB {

  public int red;
  public int green;
  public int blue;

  public RGB(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }


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




