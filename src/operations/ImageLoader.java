package operations;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ImageController.ImageFormatController;
import ImageController.ImageFormatMapper;
import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;
import ImageModel.RGB;
import format.BPMFormat;
import format.JPEGFormat;
import format.PNGFormat;
import format.PPMFormat;


/**
 * This is Load image class which performs load image operation.
 */
public class ImageLoader implements ImageOperationController {
  private final String[] commandList;
  private final Map<String, Function<String, ImageFormatController>> imageOptions = new HashMap<>();



  public ImageLoader(String input) {
    this.commandList = input.split(" ");
    storeImageOptions();
  }

  private void storeImageOptions() {
    for (ImageFormatMapper formatOption : ImageFormatMapper.values()) {
      imageOptions.put(formatOption.getFormatName(), formatOption::createController);
    }
  }

  @Override
  public void performOperation(ImageProcessingModel model) throws FileNotFoundException {
    ImageFormatController formatObject;
    String imagePath = new String(this.commandList[1]);
    Function<String, ImageFormatController> cmd =
            imageOptions.getOrDefault(imagePath.split(
                    "[.]")[1], null);
    if (cmd == null) {
      throw new IllegalArgumentException("file not supported");
    } else {
      formatObject = cmd.apply(imagePath);
      RGB[][] inputImage = formatObject.load(commandList[1], commandList[2]);
      model.imageLoader(inputImage, commandList[2]);
    }
  }
}