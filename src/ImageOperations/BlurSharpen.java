package ImageOperations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;

/**
 * The `BlurSharpen` class implements the `ImageOperationController` interface and is responsible for
 * performing blur and sharpen operations on images based on the provided instructions.
 */
public class BlurSharpen implements ImageOperationController {
  private final String[] instruction;

  /**
   * Constructs a `BlurSharpen` object by parsing the input instructions.
   *
   * @param input The input string containing operation instructions.
   */
  public BlurSharpen(String input) {
    this.instruction = input.split(" ");
  }

  /**
   * Performs the specified blur or sharpen operation on the image using the provided
   * `ImageProcessingModel`.
   *
   * @param imageProcessingModel The image processing model responsible for applying the operations.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    inputFilterOperations(instruction, imageProcessingModel);
  }

  private void inputFilterOperations(String[] operations, ImageProcessingModel imageProcessingModel) {
    if ("blur".equals(operations[1])) {
      imageBlurrer(instruction, imageProcessingModel);
    } else {
      imageSharpener(instruction, imageProcessingModel);
    }
  }

  private void imageBlurrer(String[] operations, ImageProcessingModel imageProcessingModel) {
    try {
      if (operations.length != 4) {
        throw new IllegalArgumentException("Enter valid command");
      }
      imageProcessingModel.blurImage(operations[2], operations[3]);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void imageSharpener(String[] operations, ImageProcessingModel imageProcessingModel) {
    try {
      if (operations.length != 4) {
        throw new IllegalArgumentException("Enter valid command");
      }
      imageProcessingModel.sharpenImage(operations[2], operations[3]);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
