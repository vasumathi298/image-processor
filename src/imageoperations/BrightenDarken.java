package imageoperations;

import imagecontroller.ImageOperationController;
import imagemodel.ImageProcessingModel;

/**
 * The `BrightenDarken` class implements the `ImageOperationController`
 * interface and is responsible for
 * adjusting the brightness of an image,
 * either by brightening or darkening it,
 * based on the provided instructions.
 */
public class BrightenDarken implements ImageOperationController {
  private final String[] instruction;

  /**
   * Constructs a `BrightenDarken` object by parsing the input instructions.
   *
   * @param input The input string containing operation instructions.
   */
  public BrightenDarken(String input) {
    this.instruction = input.split(" ");
  }

  /**
   * Performs the specified brighten or darken operation
   * on the image using the provided `ImageProcessingModel`.
   * It also prints a message indicating the brightness change.
   *
   * @param imageProcessingModel The image processing model responsible for applying the operation.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 4) {
        throw new IllegalArgumentException("Enter valid command");
      }
      imageProcessingModel.brightenOrDarkenImage(instruction[2],
              instruction[3], Integer.parseInt(instruction[1]));
      String action = Integer.parseInt(instruction[1]) < 0 ? "decreased" : "increased";
      int changeAmount = Math.abs(Integer.parseInt(instruction[1]));

      String outputMsg = "The " + instruction[2] + " brightness has been " + action
              + " by " + changeAmount + " units.";
      System.out.println(outputMsg);

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
