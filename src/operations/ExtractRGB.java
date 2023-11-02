package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class ExtractRGB implements ImageOperationController {
  private final String[] commandList;


  public ExtractRGB(String input) {
    this.commandList = input.split(" ");

  }

  @Override
  public void performOperation(ImageProcessingModel model) {
    try {
      if (commandList.length != 5) {
        throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
      }
      model.splitImage(commandList[1], commandList);
      System.out.println(commandList[1] + " has been split to three red, green, blue " +
              "greyscale components!");
      System.out.println("You can proceed to next commands, save another or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }


}