package operations;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ImageController.ImageFormatController;
import ImageController.ImageFormatMapper;
import ImageController.ImageOperationController;
import ImageModel.ImageProcessingModel;
import ImageModel.RGB;


public class ImageSaver implements ImageOperationController {
  private final String[] instruction;
  private final Map<String, Function<String, ImageFormatController>> imageFormatOptions = new HashMap<>();

  public ImageSaver(String input) {
    this.instruction = input.split(" ");
    storeImageOptions();
  }


  private void storeImageOptions() {
    for (ImageFormatMapper formatOption : ImageFormatMapper.values()) {
      imageFormatOptions.put(formatOption.getFormatName(), formatOption::createController);
    }
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) throws Exception {
    ImageFormatController imageFormatController;
    String imagePath = new String(this.instruction[1]);
    Function<String, ImageFormatController> ops =
            imageFormatOptions.getOrDefault(imagePath.split(
                    "[.]")[1], null);
    if (ops == null) {
      throw new IllegalArgumentException("file not supported");
    } else {
      imageFormatController = ops.apply(imagePath);
      RGB[][] image = imageProcessingModel.saveFile(this.instruction[1], this.instruction[2]);
      imageFormatController.save(instruction[1], image);
      System.out.println("Image has been save at " + instruction[1]);
    }

  }


}
