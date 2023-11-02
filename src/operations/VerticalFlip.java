package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;

public class VerticalFlip implements ImageOperationController {
  private final String[] commandList;


  public VerticalFlip(String input) {
    this.commandList = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel model) {
    try {
      if (commandList.length != 3) {
        throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
      }
      model.flipVertically(commandList[1], commandList[2]);
      System.out.println(commandList[1] + " has been flipped vertically!");
      System.out.println("You can proceed to next commands, save, or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }


}
