package imageoperations;

import imagecontroller.ImageOperationController;
import imagemodel.ImageProcessingModel;

/**
 * The `MergeRGB` class implements the `ImageOperationController`
 * interface and provides functionality
 * to merge RGB components of an image based on the provided instructions.
 */
public class MergeRGB implements ImageOperationController {
  private final String[] instruction;

  /**
   * Constructs a `MergeRGB` object and parses the input instructions.
   *
   * @param input The input string containing instructions for merging RGB components.
   */
  public MergeRGB(String input) {
    this.instruction = input.split(" ");
  }

  /**
   * Performs the image merging operation by combining the specified RGB components.
   *
   * @param imageProcessingModel The image processing model responsible for applying the operation.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 5) {
        throw new IllegalArgumentException("Enter a valid command");
      }
      imageProcessingModel.imageMerger(instruction, instruction[4]);
      System.out.println(instruction[1] + " " + instruction[2] + " " + instruction[3] +
              " has been combined!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
