package ImageController;

import java.util.function.Function;

import format.BPMFormat;
import format.JPEGFormat;
import format.PNGFormat;
import format.PPMFormat;

public enum ImageFormatMapper{
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

  public String getFormatName() {
    return formatName;
  }

  public ImageFormatController createController(String argument) {
    return controllerSupplier.apply(argument);
  }
}