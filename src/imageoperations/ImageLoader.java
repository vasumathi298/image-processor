package imageoperations;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import imagecontroller.ImageFormatController;
import imagecontroller.ImageFormatMapper;
import imagecontroller.ImageOperationController;
import imagemodel.ImageProcessingModel;
import imagemodel.RGB;

/**
 * The `ImageLoader` class implements the `ImageOperationController`
 * interface and provides functionality
 * to load and process images based on the provided instructions.
 */
public class ImageLoader implements ImageOperationController {
  private final String[] instruction;
  private final Map<String, Function<String,
          ImageFormatController>> imageFormatOptions = new HashMap<>();

  /**
   * Constructs an `ImageLoader` object and parses the input instructions.
   *
   * @param input The input string containing image loading instructions.
   */
  public ImageLoader(String input) {
    this.instruction = input.split(" ");
    storeImageOptions();
  }

  private void storeImageOptions() {
    for (ImageFormatMapper formatOption : ImageFormatMapper.values()) {
      imageFormatOptions.put(formatOption.getFormatName(), formatOption::createController);
    }
  }

  /**
   * Performs the image loading operation and processes the loaded image.
   *
   * @param imageProcessingModel The image processing model responsible for applying the operation.
   * @throws Exception If the operation encounters an error or the file format is not supported.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) throws Exception {
    ImageFormatController imageFormatController;
    String imagePath = new String(this.instruction[1]);
    Function<String, ImageFormatController> ops =
            imageFormatOptions.getOrDefault(imagePath.split(
                    "[.]")[1], null);
    if (ops == null) {
      throw new IllegalArgumentException("File format not supported");
    } else {
      imageFormatController = ops.apply(imagePath);
      RGB[][] image = imageFormatController.load(instruction[1], instruction[2]);
      imageProcessingModel.imageLoader(image, instruction[2]);
    }
  }
}
