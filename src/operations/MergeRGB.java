package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class MergeRGB implements ImageOperationController {
  private final String[] commandList;


  public MergeRGB(String input) {
    this.commandList = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel model) {
    try {
      if (commandList.length != 5) {
        throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
      }
      model.combineImage(commandList, commandList[4]);
      System.out.println(commandList[1] + " " + commandList[2] + " " + commandList[2] +
              " has been combined!");
      System.out.println("You can proceed to next commands, save another or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }


}
