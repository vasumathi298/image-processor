package operations;

import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;


public class GreyScale implements ImageOperationController {
  private final String[] commandList;


  public GreyScale(String input) {
    this.commandList = input.split(" ");

  }

  @Override
  public void performOperation(ImageProcessingModel model) {
    if (commandList.length != 4) {
      throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
    }
    switch (commandList[1]) {
      case "red-component":
        redGrayScale(commandList, model);
        break;
      case "green-component":
        greenGrayScale(commandList, model);
        break;
      case "blue-component":
        //blue-component koala koala-value-grayscale
        blueGrayScale(commandList, model);
        break;
      case "value-component":
        //value-component koala koala-value-grayscale
        valueGrayScale(commandList, model);
        break;
      case "luma-component":
        //luma-grayscale koala koala-value-grayscale
        lumaGrayScale(commandList, model);
        break;
      case "intensity-component":
        //intensity-grayscale koala koala-value-grayscale
        intensityGrayScale(commandList, model);
        break;
      default:
        System.out.println("Not a supported greyscale command!");
        break;
    }

  }



  private void redGrayScale(String[] command, ImageProcessingModel model) {
    try {
      model.createRedGray(command[2], command[3]);
      System.out.println("red grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void greenGrayScale(String[] command, ImageProcessingModel model) {
    try {
      model.createGreenGray(command[2], command[3]);
      System.out.println("green grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void blueGrayScale(String[] command, ImageProcessingModel model) {
    try {
      model.createBlueGray(command[2], command[3]);
      System.out.println("blue grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void valueGrayScale(String[] command, ImageProcessingModel model) {
    try {
      model.valueGrayScale(command[2], command[3]);
      System.out.println("Value grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void lumaGrayScale(String[] command, ImageProcessingModel model) {
    try {
      model.lumaGrayScale(command[2], command[3]);
      System.out.println("Value grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }


  private void intensityGrayScale(String[] command, ImageProcessingModel model) {
    try {
      model.intensityGrayScale(command[2], command[3]);
      System.out.println("Value grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
