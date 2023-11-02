package imageoperations;

import imagecontroller.ImageOperationController;
import imagemodel.ImageProcessingModel;

/**
 * The `ExtractRGB` class implements the `ImageOperationController`
 * interface and is responsible for
 * extracting the individual red, green, and blue
 * grayscale components of an image based on the provided instructions.
 */
public class ExtractRGB implements ImageOperationController {
  private final String[] instruction;

  /**
   * Constructs an `ExtractRGB` object by parsing the input instructions.
   *
   * @param input The input string containing operation instructions.
   */
  public ExtractRGB(String input) {
    this.instruction = input.split(" ");
  }

  /**
   * Performs the extraction of individual red, green,
   * and blue grayscale components of the image
   * using the provided `ImageProcessingModel`.
   * It also prints a message indicating the successful extraction.
   *
   * @param imageProcessingModel The image processing model responsible for applying the operation.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 5) {
        throw new IllegalArgumentException("Incorrect command!! Please enter a valid command");
      }
      imageProcessingModel.imageSplitter(instruction[1], instruction);
      String component = instruction[1];
      String printResult = "The input " + component +
              " has been separated into individual red, green, and blue grayscale components.";
      System.out.println(printResult);

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
