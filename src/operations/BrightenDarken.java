package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class BrightenDarken implements ImageOperationController {
  private final String[] instruction;


  public BrightenDarken(String input) {
    this.instruction = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 4) {
        throw new IllegalArgumentException("Enter valid command");
      }
      imageProcessingModel.brightenOrDarkenImage(instruction[2], instruction[3], Integer.parseInt(instruction[1]));
      String action = Integer.parseInt(instruction[1]) < 0 ? "decreased" : "increased";
      int changeAmount = Math.abs(Integer.parseInt(instruction[1]));

      String outputMsg = "The " + instruction[2] + " brightness has been " + action
              + " by " + changeAmount + " units.";
      System.out.println(outputMsg);

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}