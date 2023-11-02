package imageoperations;

import java.util.Objects;

import imagecontroller.ImageOperationController;
import imagemodel.ImageProcessingModel;

/**
 * The `TransformColor` class implements the `ImageOperationController`
 * interface and is used to apply
 * color transformation operations to images.
 */
public class TransformColor implements ImageOperationController {
  private final String[] instructions;

  /**
   * Constructs a `TransformColor` object with the provided input instructions.
   *
   * @param input The input string containing color transformation instructions.
   */
  public TransformColor(String input) {
    this.instructions = input.split(" ");
  }

  /**
   * Performs a color transformation operation on the
   * image using the provided `ImageProcessingModel`.
   *
   * @param imageProcessingModel model responsible for applying the transformation.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (Objects.equals(instructions[1], "sepia")) {
        if (instructions.length != 4) {
          throw new IllegalArgumentException("Enter a valid command");
        }
        imageProcessingModel.constructSepia(instructions[2], instructions[3]);
        System.out.println("Sepia tone for " + instructions[2] + " has been generated!");
      } else {
        throw new IllegalArgumentException("Invalid command");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
