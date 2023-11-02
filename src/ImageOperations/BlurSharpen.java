package ImageOperations;


import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;

public class BlurSharpen implements ImageOperationController {
  private final String[] instruction;

  public BlurSharpen(String input) {
    this.instruction = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    inputFilterOperations(instruction, imageProcessingModel);
  }

  private void inputFilterOperations(String[] operations, ImageProcessingModel imageProcessingModel) {
    if ("blur".equals(operations[1])) {
      imageBlurrer(instruction, imageProcessingModel);
    } else {
      imageSharpener(instruction, imageProcessingModel);
    }
  }


  private void imageBlurrer(String[] operations, ImageProcessingModel imageProcessingModel) {
    try {
      if (operations.length != 4) {
        throw new IllegalArgumentException("Enter valid command");
      }
      imageProcessingModel.blurImage(operations[2], operations[3]);
    } catch (Exception e) {
      System.out.println(e);
    }
  }


  private void imageSharpener(String[] operations, ImageProcessingModel imageProcessingModel) {
    try {
      if (operations.length != 4) {
        throw new IllegalArgumentException("Enter valid command");
      }
      imageProcessingModel.sharpenImage(operations[2], operations[3]);
    } catch (Exception e) {
      System.out.println(e);
    }
  }


}

