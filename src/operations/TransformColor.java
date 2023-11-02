package operations;

import java.util.Objects;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class TransformColor implements ImageOperationController {
  private final String[] instructions;

  public TransformColor(String input) {
    this.instructions = input.split(" ");

  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    try {
      if (Objects.equals(instructions[1], "sepia")) {
        if (instructions.length != 4) {
          throw new IllegalArgumentException("Enter valid command");
        }
        imageProcessingModel.constructSepia(instructions[2], instructions[3]);
        System.out.println("Sepia tone for " + instructions[2] + " has been generated!");
      } else {
        throw new IllegalArgumentException("Invalid command");
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
