package imagecontroller;

import java.util.function.Function;

import imageoperations.BlueComponent;
import imageoperations.Blur;
import imageoperations.GreenComponent;
import imageoperations.Histogram;
import imageoperations.ImageColorCorrection;
import imageoperations.ImageCompression;
import imageoperations.ImageLoader;
import imageoperations.BrightenDarken;
import imageoperations.LevelAdjustment;
import imageoperations.RedComponent;
import imageoperations.Sharpen;
import imageoperations.VerticalFlip;
import imageoperations.HorizontalFlip;
import imageoperations.MergeRGB;
import imageoperations.ExtractRGB;
import imageoperations.GreyScale;
import imageoperations.ScriptRunnable;
import imageoperations.ImageSaver;
import imageoperations.TransformColor;

/**
 * The ImageOperationMapper enum maps command strings to their corresponding
 * ImageOperationController instances for performing various image operations.
 */
public enum ImageOperationMapper {
  LOAD("load", ImageLoader::new),
  BRIGHTEN("brighten", BrightenDarken::new),
  HORIZONTAL_FLIP("horizontal-flip", HorizontalFlip::new),
  VERTICAL_FLIP("vertical-flip", VerticalFlip::new),
  BLUR("blur", Blur::new),
  SHARPEN("sharpen", Sharpen::new),
  RGB_COMBINE("rgb-combine", MergeRGB::new),
  RGB_SPLIT("rgb-split", ExtractRGB::new),
  GREYSCALE("greyscale", GreyScale::new),
  RUN("run", ScriptRunnable::new),
  SAVE("save", ImageSaver::new),
  RED_COMPONENT("red-component", RedComponent::new),
  BLUE_COMPONENT("blue-component", BlueComponent::new),
  GREEN_COMPONENT("green-component", GreenComponent::new),
  COLOR_TRANSFORM("sepia", TransformColor::new),
  HISTOGRAM("histogram", Histogram::new),
  IMAGE_COMPRESSION("compress", ImageCompression::new),
  COLOR_CORRECTION("color-correct", ImageColorCorrection::new),
  LEVEL_ADJUST("levels-adjust", LevelAdjustment::new)
  ;

  

  private final String command;
  private final Function<String, ImageOperationController> controllerSupplier;

  ImageOperationMapper(String command,
                       Function<String, ImageOperationController> controllerSupplier) {
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
