package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class MergeRGB implements ImageOperationController {
  private final String[] instruction;


  public MergeRGB(String input) {
    this.instruction = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 5) {
        throw new IllegalArgumentException("Enter valid command");
      }
      imageProcessingModel.imageMerger(instruction, instruction[4]);
      System.out.println(instruction[1] + " " + instruction[2] + " " + instruction[2] +
              " has been combined!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }


}
