package imagecontroller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import imagemodel.ImageProcessingModel;
import imageview.ImageProcessingView;
import imageview.ImageProcessingViewImpl;

/**
 * The ImageProcessingControllerImpl class implements the ImageProcessingController interface and
 * handles the selection and processing of image operations.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private final InputStream inputImageStream;
  private final ImageProcessingModel imageModel;
  private final Map<String, Function<Scanner,
          ImageOperationController>> knownCommands = new HashMap<>();
  private List<String> operations;

  private ImageProcessingView feature;
  /**
   * Constructs an ImageProcessingControllerImpl with the specified input stream and image model.
   *
   * @param input The input stream for user commands.
   * @param model The ImageProcessingModel to perform operations on.
   */
  public ImageProcessingControllerImpl(InputStream input, ImageProcessingModel model) {
    this.inputImageStream = input;
    this.operations = new ArrayList<>();
    this.imageModel = model;
    loadOperations();
  }

  public ImageProcessingControllerImpl(InputStream input, ImageProcessingView view, ImageProcessingModel model) {
    this.inputImageStream = input;
    this.operations = new ArrayList<>();
    this.imageModel = model;
    this.feature = view;
    loadOperations();
  }
  private void loadOperations() {
    for (ImageOperationMapper command : ImageOperationMapper.values()) {
      knownCommands.put(command.getCommand(), s -> command.createController(s.nextLine()));
    }
  }

  @Override
  public void imageOperationSelector() throws Exception {
    Scanner userIO = new Scanner(this.inputImageStream);
    if (this.operations.size() > 0) {
      for (String ops : this.operations) {
        userIO = new Scanner(ops);
        operationProcessor(userIO);
      }
      this.operations = new ArrayList<>();
    } else {
      operationProcessor(userIO);
    }
  }

  @Override
  public void operationProcessor(Scanner userIO) throws Exception {
    do {
      ImageOperationController operationController;
      String userInputCommand = userIO.next();
      String command = userInputCommand.split(" ")[0];
      if (command.equalsIgnoreCase("quit")
              || command.equalsIgnoreCase("exit")) {
        break; // Exit the loop
      }
      Function<Scanner, ImageOperationController> commandLine =
              this.knownCommands.getOrDefault(command, null);
      if (commandLine == null) {
        throw new IllegalArgumentException("Invalid");
      } else {
        operationController = commandLine.apply(userIO);
        operationController.performOperation(this.imageModel);
      }
    }
    while (userIO.hasNext());
  }
}
