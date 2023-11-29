package imageoperations;

import imagecontroller.ImageOperationController;
import imagemodel.ImageProcessingModel;

/**
 * The `GreyScale` class implements the `ImageOperationController`
 * interface and provides operations
 * to generate various grayscale components
 * of an image based on the given instructions.
 */

public class GreyScale implements ImageOperationController {
  private final String[] instruction;


  /**
   * Constructs a `GreyScale` object by parsing the input instructions.
   *
   * @param input The input string containing operation instructions.
   */
  public GreyScale(String input) {
    this.instruction = input.split(" ");

  }

  /**
   * Performs the grayscale operation based on the type of component specified in the instructions.
   *
   * @param imageProcessingModel The image processing model responsible for applying the operation.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) {
    if (instruction.length > 6) {
      throw new IllegalArgumentException("Enter valid command");
    }
    String ops = instruction[1];

    if ("red-component".equals(ops)) {
      redCompGrayOp(instruction, imageProcessingModel);
    } else if ("green-component".equals(ops)) {
      greenCompGrayOp(instruction, imageProcessingModel);
    } else if ("blue-component".equals(ops)) {
      blueCompGrayOp(instruction, imageProcessingModel);
    } else if ("value-component".equals(ops)) {
      valueCompGrayOp(instruction, imageProcessingModel);
    } else if ("luma-component".equals(ops)) {
      lumaCompGrayOp(instruction, imageProcessingModel);
    } else if ("intensity-component".equals(ops)) {
      intensityCompGrayOp(instruction, imageProcessingModel);
    } else {
      System.out.println("This operation is not supported.");
    }
  }


  private void redCompGrayOp(String[] ops, ImageProcessingModel imageProcessingModel) {
    try {
      imageProcessingModel.constructRedGreyScale(ops[2], ops[3]);
      System.out.println("Red grayscale component of "
              + ops[2] + " has been generated successfully.");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void greenCompGrayOp(String[] ops, ImageProcessingModel imageProcessingModel) {
    try {
      imageProcessingModel.constructGreenGreyScale(ops[2], ops[3]);
      System.out.println("Green grayscale component of "
              + ops[2] + " has been generated successfully.");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void blueCompGrayOp(String[] ops, ImageProcessingModel imageProcessingModel) {
    try {
      imageProcessingModel.constructBlueGreyScale(ops[2], ops[3]);
      System.out.println("Blue grayscale component of "
              + ops[2] + " has been generated successfully.");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void valueCompGrayOp(String[] ops, ImageProcessingModel imageProcessingModel) {
    try {
      imageProcessingModel.greyScaleValue(ops[2], ops[3]);
      System.out.println("Value grayscale component of "
              + ops[2] + " has been generated successfully.");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void lumaCompGrayOp(String[] ops, ImageProcessingModel imageProcessingModel) {
    try {
      if (instruction.length == 4) {
        imageProcessingModel.greyScaleLuma(ops[2], ops[3], 100);
      } else {
        imageProcessingModel.greyScaleLuma(ops[2], ops[3], Double.valueOf(ops[5]));
      }
      System.out.println("Luma grayscale component of "
              + ops[2] + " has been generated successfully.");
    } catch (Exception e) {
      System.out.println(e);
    }
  }


  private void intensityCompGrayOp(String[] ops, ImageProcessingModel imageProcessingModel) {
    try {
      imageProcessingModel.greyScaleIntensity(ops[2], ops[3]);
      System.out.println("Intensity grayscale component of "
              + ops[2] + " has been generated successfully.");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
