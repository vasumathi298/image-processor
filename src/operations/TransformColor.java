package operations;

import java.util.Objects;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class TransformColor implements ImageOperationController {
  private final String[] commandList;

  public TransformColor(String input) {
    this.commandList = input.split(" ");

  }

  @Override
  public void performOperation(ImageProcessingModel model) {
    try {
      if (Objects.equals(commandList[1], "sepia")) {
        if (commandList.length != 4) {
          throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
        }
        model.createSepiaTone(commandList[2], commandList[3]);
        System.out.println("Sepia tone for " + commandList[2] + " has been generated!");
        System.out.println("You can proceed to next commands, save, or quit!");
      } else {
        throw new IllegalArgumentException("Invalid command");
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
