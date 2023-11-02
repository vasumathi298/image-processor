package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class BrightenDarken implements ImageOperationController {
  private final String[] commandList;


  public BrightenDarken(String input) {
    this.commandList = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel model) {
    try {
      if (commandList.length != 4) {
        throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
      }
      model.imageBrightenDarken(commandList[2], commandList[3], Integer.parseInt(commandList[1]));
      String printResult = "Brightness of " + commandList[2] + " " +
              "has been " + (Integer.parseInt(commandList[1]) < 0 ? "decreased" : "increased")
              + " by " + Math.abs(Integer.parseInt(commandList[1])) + " points!";
      System.out.println(printResult);
      System.out.println("You can proceed to next commands, save, or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}