package imageoperations;

import imagecontroller.ImageOperationController;
import imagemodel.EnhancedImageProcessingModel;
import imagemodel.ImageProcessingModel;

public class ImageColorCorrection implements ImageOperationController {

  private final String[] instruction;


  public ImageColorCorrection(String input) {
    this.instruction = input.split(" ");
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) throws Exception {
    try {
      if (instruction.length > 5) {
        throw new IllegalArgumentException("Enter valid command");
      }
      EnhancedImageProcessingModel enhancedImageProcessingModel= (EnhancedImageProcessingModel) imageProcessingModel;
      if(instruction.length == 3){
        enhancedImageProcessingModel.imageColorCorrection(instruction[1], instruction[2],0);
      }
      else{
        enhancedImageProcessingModel.imageColorCorrection(instruction[1], instruction[2],Double.valueOf(instruction[4]));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  }



