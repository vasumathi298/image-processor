package imageoperations;

import imagecontroller.ImageOperationController;
import imagemodel.EnhancedImageProcessingModel;
import imagemodel.ImageProcessingModel;

/**
 * Generate Histogram of the image.
 */
public class Histogram implements ImageOperationController {


  private final String[] instruction;


  public Histogram(String input) {
    this.instruction = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) throws Exception {
    try {
      if (instruction.length != 3) {
        throw new IllegalArgumentException("Enter a valid command");
      }
      EnhancedImageProcessingModel enhancedImageProcessingModel =
              (EnhancedImageProcessingModel) imageProcessingModel;
      enhancedImageProcessingModel.fetchHistogram(instruction[1], instruction[2]);
    } catch (Exception e) {
      System.out.println(e);
    }
  }


}
