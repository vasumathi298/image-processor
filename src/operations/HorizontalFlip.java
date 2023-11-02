package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class HorizontalFlip implements ImageOperationController {
  private final String[] instruction;


  public HorizontalFlip(String input) {
    this.instruction = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 3) {
        throw new IllegalArgumentException("Enter valid command");
      }
      imageProcessingModel.horizontalImageFlip(instruction[1], instruction[2]);
    } catch (Exception e) {
      System.out.println(e);
    }

  }


}
