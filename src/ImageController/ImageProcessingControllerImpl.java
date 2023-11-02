package ImageController;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import ImageModel.ImageProcessingModel;



public class ImageProcessingControllerImpl implements ImageProcessingController {
  private final InputStream inputImageStream;
  private final ImageProcessingModel imageModel;
  private final Map<String, Function<Scanner, ImageOperationController>> knownCommands = new HashMap<>();
  private List<String> operations;


  public ImageProcessingControllerImpl(InputStream input,
                                       ImageProcessingModel model) {
    this.inputImageStream = input;
    this.operations = new ArrayList<>();
    this.imageModel = model;
    loadOperations();
  }


  private void loadOperations() {
    for (ImageOperationMapper command : ImageOperationMapper.values()) {
      knownCommands.put(command.getCommand(), s -> command.createController(s.nextLine()));
    }
  }

  @Override
  public void imageOperationSelector() throws Exception {
    Scanner userIO = null;
    userIO = new Scanner(this.inputImageStream);
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
    } while (userIO.hasNext());
  }

}
