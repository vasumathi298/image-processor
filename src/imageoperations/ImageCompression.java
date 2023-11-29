package imageoperations;


import imagecontroller.ImageOperationController;
import imagemodel.EnhancedImageProcessingModel;
import imagemodel.ImageProcessingModel;

/**
 * Compress the image based on the threshold.
 */
public class ImageCompression implements ImageOperationController {


  private final String[] instruction;


  public ImageCompression(String input) {
    this.instruction = input.split(" ");
  }


  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) throws Exception {

    try {
      if (instruction.length != 4) {
        throw new IllegalArgumentException("Enter a valid command");
      }
      EnhancedImageProcessingModel enhancedImageProcessingModel =
              (EnhancedImageProcessingModel) imageProcessingModel;
      enhancedImageProcessingModel.compressImage(instruction[2],
              instruction[3], Double.parseDouble(instruction[1]));
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}

