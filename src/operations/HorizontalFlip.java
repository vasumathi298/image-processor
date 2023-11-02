package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


/**
 * This is Horizontal flip class.
 * This class flips an image horizontally.
 */
public class HorizontalFlip implements ImageOperationController {
  private final String[] commandList;


  public HorizontalFlip(String input) {
    this.commandList = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel model) {
    try {
      if (commandList.length != 3) {
        throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
      }
      model.flipHorizontally(commandList[1], commandList[2]);
      System.out.println(commandList[1] + " has been flipped horizontally!");
      System.out.println("You can proceed to next commands, save, or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }

  }


}
