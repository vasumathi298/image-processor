package imageoperations;

import imagecontroller.ImageOperationController;
import imagemodel.EnhancedImageProcessingModel;
import imagemodel.ImageProcessingModel;

/**
 * Does level adjustment for the image.
 */
public class LevelAdjustment implements ImageOperationController {

  private final String[] instruction;


  public LevelAdjustment(String input) {
    this.instruction = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) throws Exception {

    try {
      if (instruction.length > 8) {
        throw new IllegalArgumentException("Enter a valid command");
      }
      int b = Integer.valueOf(instruction[1]);
      int m = Integer.valueOf(instruction[2]);
      int w = Integer.valueOf(instruction[3]);

      if (!(b < m && m < w && b < w) ||
              (b < 0 || b > 255 || m < 0 || m > 255 || w < 0 || w > 255)) {
        throw new IllegalArgumentException("Enter a valid b,m/w value");
      }
      EnhancedImageProcessingModel enhancedImageProcessingModel =
              (EnhancedImageProcessingModel) imageProcessingModel;

      if (instruction.length == 6) {
        enhancedImageProcessingModel.levelAdjust(instruction[4], instruction[5],
                b, m, w, 100);
      } else {
        enhancedImageProcessingModel.levelAdjust(instruction[4], instruction[5],
                b, m, w, Double.valueOf(instruction[7]));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
