package ImageOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ImageController.ImageFormatController;
import ImageController.ImageFormatMapper;
import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;
import ImageModel.RGB;

/**
 * The `ImageSaver` class implements the `ImageOperationController` interface and provides functionality
 * to save and process images based on the provided instructions.
 */
public class ImageSaver implements ImageOperationController {
  private final String[] instruction;
  private final Map<String, Function<String, ImageFormatController>> imageFormatOptions = new HashMap<>();

  /**
   * Constructs an `ImageSaver` object and parses the input instructions.
   *
   * @param input The input string containing image saving instructions.
   */
  public ImageSaver(String input) {
    this.instruction = input.split(" ");
    storeImageOptions();
  }

  private void storeImageOptions() {
    for (ImageFormatMapper formatOption : ImageFormatMapper.values()) {
      imageFormatOptions.put(formatOption.getFormatName(), formatOption::createController);
    }
  }

  /**
   * Performs the image saving operation and saves the processed image to the specified file.
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
      RGB[][] image = imageProcessingModel.saveFile(this.instruction[1], this.instruction[2]);
      imageFormatController.save(instruction[1], image);
      System.out.println("Image has been saved at " + instruction[1]);
    }
  }
}
