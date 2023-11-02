package ImageOperations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class ExtractRGB implements ImageOperationController {
  private final String[] instruction;


  public ExtractRGB(String input) {
    this.instruction = input.split(" ");

  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length != 5) {
        throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
      }
      imageProcessingModel.imageSplitter(instruction[1], instruction);
      String component = instruction[1];
      String printResult = "The input " + component + " has been separated into individual red, green, and blue grayscale components.";
      System.out.println(printResult);

    } catch (Exception e) {
      System.out.println(e);
    }
  }


}