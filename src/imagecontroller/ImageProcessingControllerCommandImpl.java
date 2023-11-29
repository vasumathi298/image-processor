package imagecontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import imagemodel.EnhancedImageProcessingModel;
import imagemodel.ImageProcessingModel;
import imagemodel.RGB;
import imageview.ImageProcessingView;

/**
 * This controller will call the model from GUI.
 */
public class ImageProcessingControllerCommandImpl implements Features {
  private final ImageProcessingModel model;
  private final Map<String, Function<Scanner, ImageOperationController>> knownCommands =
          new HashMap<>();
  private final Map<String, RGB[][]> combineGreyScaleImages = new HashMap<>();
  int i = 1;
  private String objectName = "image";
  private ImageProcessingView view;
  private Stack<String> currentImage;


  private boolean performed;

  private boolean performedEnhanced;

  /**
   * Constructor to initialize values.
   *
   * @param input input img.
   * @param view  view view.
   * @param model model.
   */
  public ImageProcessingControllerCommandImpl(InputStream input, ImageProcessingView view,
                                              ImageProcessingModel model) {
    InputStream input1;
    input1 = input;
    this.view = view;
    this.model = model;
    List<String> runCommands;

    runCommands = new ArrayList<>();
    loadOperations();
    this.currentImage = new Stack<>();
    performed = false;
  }

  private void loadOperations() {
    for (ImageOperationMapper command : ImageOperationMapper.values()) {
      knownCommands.put(command.getCommand(), s -> command.createController(s.nextLine()));
    }
  }

  @Override
  public void loadImage(String imageName) {
    try {
      String imagePath = view.load();
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("load", null);
      cc = cmd.apply(new Scanner("load" + " "
              + imagePath + " " + imageName));
      cc.performOperation(this.model);
      this.currentImage.push(imageName);
    } catch (IOException e) {
      view.displayError("Error while loading image!");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    view.displayImage(imageName);
  }


  @Override
  public void saveImage() {
    try {
      String imagePath = view.save();
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("save", null);
      cc = cmd.apply(new Scanner("save" + " "
              + currentImage.peek() + ".png" + " " + imagePath));
      cc.performOperation(this.model);
    } catch (IOException e) {
      view.displayError("Error while saving image!");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    view.displayImage(currentImage.peek());
  }


  @Override
  public void verticalFlip() {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("vertical-flip", null);
      cc = cmd.apply(new Scanner("vertical-flip" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
      this.currentImage.push(objectName);
    } catch (Exception e) {
      view.displayError("Error performing vertical flip operation!");
    }
    System.out.println("current image is " + currentImage.peek());
    view.displayImage(currentImage.peek());
  }

  @Override
  public void horizontalFlip() {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("horizontal-flip", null);
      cc = cmd.apply(new Scanner("horizontal-flip" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing horizontal flip operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void valueGrayscale() {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "value-component" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing value greyscale operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void lumaGrayscale(double splitPercentage) {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "luma-component" + " "
              + objectName + " " + currentImage.peek() + " " + "split" + " " + splitPercentage));
      if (!performed) {
        EnhancedImageProcessingModel enhancedModel = (EnhancedImageProcessingModel) this.model;
        enhancedModel.revertImage(objectName, objectName + "-non-split");
        performed = true;
      }
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing luma greyscale operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void intensityGrayscale() {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "intensity-component" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (IOException e) {
      view.displayError("Error performing intensity greyscale operation!");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void redGrayscale() throws FileNotFoundException {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "red-component" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing red greyscale operation!");
    }
    RGB[][] image = model.retrieveImage(currentImage.peek());
    model.imageLoader(image, "red");
    combineGreyScaleImages.put("red", image);
    view.displayImage(currentImage.peek());
  }

  @Override
  public void blueGrayscale() throws FileNotFoundException {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "blue-component" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (IOException e) {
      view.displayError("Error performing blue greyscale operation!");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    RGB[][] image = model.retrieveImage(currentImage.peek());
    model.imageLoader(image, "blue");
    combineGreyScaleImages.put("blue", image);
    view.displayImage(currentImage.peek());
  }

  @Override
  public void greenGrayscale() throws FileNotFoundException {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "green-component" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing green greyscale operation!");
    }
    RGB[][] image = model.retrieveImage(currentImage.peek());
    model.imageLoader(image, "green");
    combineGreyScaleImages.put("green", image);
    view.displayImage(currentImage.peek());
  }

  @Override
  public void getRedComponent() throws FileNotFoundException {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("red-component", null);
      cc = cmd.apply(new Scanner("red-component" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing red-component operation!");
    }
    RGB[][] image = model.retrieveImage(currentImage.peek());
    model.imageLoader(image, "redComp");
    combineGreyScaleImages.put("redComp", image);
    view.displayImage(currentImage.peek());
  }

  @Override
  public void getGreenComponent() throws FileNotFoundException {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("green-component", null);
      cc = cmd.apply(new Scanner("green-component" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing green-component operation!");
    }
    RGB[][] image = model.retrieveImage(currentImage.peek());
    model.imageLoader(image, "greenComp");
    combineGreyScaleImages.put("greenComp", image);
    view.displayImage(currentImage.peek());
  }

  @Override
  public void getBlueComponent() throws FileNotFoundException {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("blue-component", null);
      cc = cmd.apply(new Scanner("blue-component" + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing blue-component operation!");
    }
    RGB[][] image = model.retrieveImage(currentImage.peek());
    model.imageLoader(image, "blueComp");
    combineGreyScaleImages.put("blueComp", image);
    view.displayImage(currentImage.peek());
  }

  @Override
  public void getColorCorrectedImage(double splitPercentage) {
    try {
      ImageOperationController cc;
      EnhancedImageProcessingModel enhancedModel = (EnhancedImageProcessingModel) this.model;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("color-correct", null);
      cc = cmd.apply(new Scanner("color-correct" + " "
              + objectName + " " + currentImage.peek() + " " + "split" + " " + splitPercentage));
      System.out.println("is performed? " + performed);
      if (!performed) {
        enhancedModel.revertImage(objectName, objectName + "-non-split");
        performed = true;
      }
      cc.performOperation(enhancedModel);
    } catch (Exception e) {
      view.displayError("Error performing color-correct operation!");
    }
    view.displayImage(currentImage.peek());
  }


  @Override
  public void blur(double splitPercentage) {
    try {
      ImageOperationController cc;
      EnhancedImageProcessingModel enhancedModel = (EnhancedImageProcessingModel) this.model;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("blur", null);
      cc = cmd.apply(new Scanner("blur" + " "
              + objectName + " " + currentImage.peek() + " " + "split" + " " + splitPercentage));
      if (!performed) {
        enhancedModel.revertImage(objectName, objectName + "-non-split");
        performed = true;
      }
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing blur operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void sharpen(double splitPercentage) {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("sharpen", null);
      cc = cmd.apply(new Scanner("sharpen" + " "
              + objectName + " " + currentImage.peek() + " " + "split" + " " + splitPercentage));
      if (!performed) {
        EnhancedImageProcessingModel enhancedModel = (EnhancedImageProcessingModel) this.model;
        enhancedModel.revertImage(objectName, objectName + "-non-split");
        performed = true;
      }
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing sharpen operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void revert() {
    view.displayImage(objectName + "-non-split");
  }

  @Override
  public void sepiaTone(double splitPercentage) {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("sepia", null);
      cc = cmd.apply(new Scanner("sepia" + " "
              + objectName + " " + currentImage.peek() + " " + "split" + " " + splitPercentage));
      if (!performed) {
        EnhancedImageProcessingModel enhancedModel = (EnhancedImageProcessingModel) this.model;
        enhancedModel.revertImage(objectName, objectName + "-non-split");
        performed = true;
      }
      cc.performOperation(this.model);
      this.currentImage.push(objectName);
    } catch (Exception e) {
      view.displayError("Error performing sepia operation!");
    }
    view.displayImage(currentImage.peek());
  }


  @Override
  public void brightness(int value) {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("brighten", null);
      cc = cmd.apply(new Scanner("brighten" + " " + value + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing brightness operation!");
    }
    view.displayImage(currentImage.peek());
  }


  @Override
  public void levelsAdjust(int b, int m, int w, double splitPercentage) {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("levels-adjust", null);
      cc = cmd.apply(new Scanner("levels-adjust" + " " + b + " " + m + " " + w + " "
              + objectName + " " + currentImage.peek() + " " + "split" + " " + splitPercentage));
      if (!performed) {
        EnhancedImageProcessingModel enhancedModel = (EnhancedImageProcessingModel) this.model;
        enhancedModel.revertImage(objectName, objectName + "-non-split");
        performed = true;
      }
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing levels-adjust operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void combineImage() {
    if (model.retrieveImage("red").length > 0 && model.retrieveImage("green").length > 0
            && model.retrieveImage("blue").length > 0) {
      try {
        ImageOperationController cc;
        Function<Scanner, ImageOperationController> cmd =
                knownCommands.getOrDefault("rgb-combine", null);
        cc = cmd.apply(new Scanner("rgb-combine" + " " + "red" + " "
                + "green" + " " + "blue" + " " + currentImage.peek()));
        cc.performOperation(this.model);
      } catch (Exception e) {
        view.displayError("Error performing combine operation!");
      }
      view.displayImage(currentImage.peek());
    }
  }

  @Override
  public void setView(ImageProcessingView v) {
    this.view = v;
    this.view.addFeatures(this);
  }

  @Override
  public void compression(double threshold) {
    try {
      ImageOperationController cc;
      Function<Scanner, ImageOperationController> cmd =
              knownCommands.getOrDefault("compress", null);
      cc = cmd.apply(new Scanner("compress" + " " + threshold + " "
              + objectName + " " + currentImage.peek()));
      cc.performOperation(this.model);
    } catch (Exception e) {
      view.displayError("Error performing compression operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void operationProcessor(Scanner input) throws Exception {
    while (input.hasNext()) {
      ImageOperationController control;
      String inputCommand = input.next();
      String command = inputCommand.split(" ")[0];
      if (command.equalsIgnoreCase("quit")
              || command.equalsIgnoreCase("exit")) {
        return;
      }
      Function<Scanner, ImageOperationController> cmd =
              this.knownCommands.getOrDefault(command, null);
      if (cmd == null) {
        throw new IllegalArgumentException("Invalid");
      } else {
        control = cmd.apply(input);
        control.performOperation(this.model);
      }
    }
  }

  /**
   * Handing inputs from users.
   *
   * @throws Exception Exception for error.
   */
  @Override
  public void imageOperationSelector() throws Exception {

    // this is empty function.
  }


}
