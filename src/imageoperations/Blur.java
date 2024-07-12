package imageoperations;


import imagecontroller.ImageOperationController;
import imagemodel.ImageProcessingModel;

/**
 * The `BlurSharpen` class implements the `ImageOperationController`
 * interface and is responsible for
 * performing blur and sharpen operations
 * on images based on the provided instructions.
 */
public class Blur implements ImageOperationController {
  private final String[] instruction;

  /**
   * Constructs a `BlurSharpen` object by parsing the input instructions.
   *
   * @param input The input string containing operation instructions.
   */
  public Blur(String input) {
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

  private void inputFilterOperations(String[] operations,
                                     ImageProcessingModel imageProcessingModel) {

    imageBlurrer(instruction, imageProcessingModel);
  }

  private void imageBlurrer(String[] operations, ImageProcessingModel imageProcessingModel) {
    try {
      if (operations.length > 5) {
        throw new IllegalArgumentException("Enter valid command");
      }
      if (operations.length == 3) {
        imageProcessingModel.blurImage(operations[1], operations[2], 100);
      } else {
        imageProcessingModel.blurImage(operations[1], operations[2], Double.valueOf(operations[4]));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
