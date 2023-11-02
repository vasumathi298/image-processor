package imageoperations;

import imagecontroller.ImageOperationController;
import imagemodel.ImageProcessingModel;

/**
 * The `VerticalFlip` class implements the `ImageOperationController`
 * interface and is used to perform a
 * vertical flip operation on images.
 */
public class VerticalFlip implements ImageOperationController {
  private final String[] instruction;

  /**
   * Constructs a `VerticalFlip` object with the provided input instructions.
   *
   * @param input The input string specifying the vertical flip operation.
   */
  public VerticalFlip(String input) {
    this.instruction = input.split(" ");
  }

  /**
   * Performs a vertical flip operation on the image using the provided `ImageProcessingModel`.
   *
   * @param imageProcessingModel model responsible for applying the vertical flip.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 3) {
        throw new IllegalArgumentException("Enter a valid command");
      }
      imageProcessingModel.verticalImageFlip(instruction[1], instruction[2]);
      System.out.println(instruction[1] + " has been flipped vertically!");
      System.out.println("You can proceed to next commands, save, or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
