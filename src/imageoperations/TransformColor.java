package imageoperations;


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
      if (instructions.length > 5) {
        throw new IllegalArgumentException("Enter valid command");
      }
      System.out.println(instructions[1]);
      System.out.println(instructions[2]);

      if (instructions.length == 3) {
        imageProcessingModel.constructSepia(instructions[1], instructions[2], 100);
      } else {
        imageProcessingModel.constructSepia(instructions[1], instructions[2],
                Double.valueOf(instructions[4]));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
