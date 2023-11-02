package operations;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ImageController.ImageFormatController;
import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;
import ImageModel.RGB;
import format.PNGFormat;
import format.PPMFormat;
import format.BPMFormat;
import format.JPEGFormat;

/**
 * This is saveImage class which saves the image in required format.
 */
public class ImageSaver implements ImageOperationController {
  private final String[] commandList;
  private final Map<String, Function<String, ImageFormatController>> imageOptions = new HashMap<>();

  public ImageSaver(String input) {
    this.commandList = input.split(" ");
    storeImageOptions();
  }


  private void storeImageOptions() {
    this.imageOptions.put("ppm", s -> new PPMFormat());
    this.imageOptions.put("png", s -> new PNGFormat());
    this.imageOptions.put("bmp", s -> new BPMFormat());
    this.imageOptions.put("jpeg", s -> new JPEGFormat());
    this.imageOptions.put("jpg", s -> new JPEGFormat());
  }

  @Override
  public void performOperation(ImageProcessingModel model) {
      ImageFormatController formatObject;
    String imagePath = new String(this.commandList[1]);
    Function<String, ImageFormatController> cmd =
            imageOptions.getOrDefault(imagePath.split(
                    "[.]")[1], null);
    if (cmd == null) {
      throw new IllegalArgumentException("file not supported");
    } else {
      formatObject = cmd.apply(imagePath);
      RGB[][] imageFile = model.saveFile(this.commandList[1], this.commandList[2]);
      formatObject.save(commandList[1], imageFile);
      System.out.println("Image has been save at " + commandList[1]);
      System.out.println("You can proceed to next commands, save another or quit!");
    }

  }


}
