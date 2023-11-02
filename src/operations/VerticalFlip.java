package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;

public class VerticalFlip implements ImageOperationController {
  private final String[] instruction;


  public VerticalFlip(String input) {
    this.instruction = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 3) {
        throw new IllegalArgumentException("Enter valid command");
      }
      imageProcessingModel.verticalImageFlip(instruction[1], instruction[2]);
      System.out.println(instruction[1] + " has been flipped vertically!");
      System.out.println("You can proceed to next commands, save, or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }


}
