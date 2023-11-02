package imagecontroller;

import java.util.function.Function;

import imageformat.BPMFormat;
import imageformat.JPEGFormat;
import imageformat.PNGFormat;
import imageformat.PPMFormat;

/**
 * The ImageFormatMapper enum maps file extensions to the corresponding ImageFormatController.
 * It allows creating controller instances based on the image format name.
 */
public enum ImageFormatMapper {
  PPM("ppm", input -> new PPMFormat()),
  PNG("png", input -> new PNGFormat()),
  BMP("bmp", input -> new BPMFormat()),
  JPEG("jpeg", input -> new JPEGFormat()),
  JPG("jpg", input -> new JPEGFormat());

  private final String formatName;
  private final Function<String, ImageFormatController> controllerSupplier;

  ImageFormatMapper(String formatName, Function<String, ImageFormatController> controllerSupplier) {
    this.formatName = formatName;
    this.controllerSupplier = controllerSupplier;
  }

  /**
   * Get the format name associated with the enum value.
   *
   * @return The format name.
   */
  public String getFormatName() {
    return formatName;
  }

  /**
   * Create an ImageFormatController instance based on the format name.
   *
   * @param argument The argument associated with the format.
   * @return An instance of ImageFormatController.
   */
  public ImageFormatController createController(String argument) {
    return controllerSupplier.apply(argument);
  }
}
