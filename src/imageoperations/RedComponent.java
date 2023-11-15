package imageoperations;

import imagecontroller.ImageOperationController;
import imagemodel.ImageProcessingModel;

/**
 * The `HorizontalFlip` class implements the `ImageOperationController`
 * interface and provides an operation
 * to horizontally flip an image based on the given instructions.
 */
public class RedComponent implements ImageOperationController {
  private final String[] instruction;

  /**
   * Constructs a `HorizontalFlip` object by parsing the input instructions.
   *
   * @param input The input string containing operation instructions.
   */
  public RedComponent(String input) {
    this.instruction = input.split(" ");
  }

  /**
   * Performs a horizontal flip operation on the specified image.
   *
   * @param imageProcessingModel The image processing model responsible for applying the operation.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 3) {
        throw new IllegalArgumentException("Enter a valid command");
      }
      imageProcessingModel.redComponent(instruction[1], instruction[2]);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
