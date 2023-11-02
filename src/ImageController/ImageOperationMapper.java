package ImageController;

import java.util.function.Function;

import ImageOperations.ImageLoader;
import ImageOperations.BrightenDarken;
import ImageOperations.VerticalFlip;
import ImageOperations.HorizontalFlip;
import ImageOperations.MergeRGB;
import ImageOperations.ExtractRGB;
import ImageOperations.GreyScale;
import ImageOperations.ScriptRunnable;
import ImageOperations.ImageSaver;
import ImageOperations.TransformColor;

/**
 * The ImageOperationMapper enum maps command strings to their corresponding ImageOperationController
 * instances for performing various image operations.
 */
public enum ImageOperationMapper {
  LOAD("load", ImageLoader::new),
  BRIGHTEN("brighten", BrightenDarken::new),
  HORIZONTAL_FLIP("horizontal-flip", HorizontalFlip::new),
  VERTICAL_FLIP("vertical-flip", VerticalFlip::new),
  RGB_COMBINE("rgb-combine", MergeRGB::new),
  RGB_SPLIT("rgb-split", ExtractRGB::new),
  GREYSCALE("greyscale", GreyScale::new),
  RUN("run", ScriptRunnable::new),
  SAVE("save", ImageSaver::new),
  COLOR_TRANSFORM("color-transform", TransformColor::new);

  private final String command;
  private final Function<String, ImageOperationController> controllerSupplier;

  ImageOperationMapper(String command, Function<String, ImageOperationController> controllerSupplier) {
    this.command = command;
    this.controllerSupplier = controllerSupplier;
  }

  /**
   * Get the command string associated with this mapping.
   *
   * @return The command string.
   */
  public String getCommand() {
    return command;
  }

  /**
   * Create an ImageOperationController instance based on the command string.
   *
   * @param argument The argument for the operation.
   * @return An ImageOperationController instance.
   */
  public ImageOperationController createController(String argument) {
    return controllerSupplier.apply(argument);
  }
}
