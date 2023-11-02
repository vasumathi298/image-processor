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
  private final InputStream input;
  private final ImageProcessingModel model;
  private final Map<String, Function<Scanner, ImageOperationController>> knownCommands = new HashMap<>();
  private List<String> runCommands;


  public ImageProcessingControllerImpl(InputStream input,
                                       ImageProcessingModel model) {
    this.input = input;
    this.model = model;
    this.runCommands = new ArrayList<>();
    storeCommands();
  }


  private void storeCommands() {
    for (ImageOperationMapper command : ImageOperationMapper.values()) {
      knownCommands.put(command.getCommand(), s -> command.createController(s.nextLine()));
    }
  }

  @Override
  public void inputSelection() throws FileNotFoundException {
    Scanner input = new Scanner(this.input);
    if (this.runCommands.size() > 0) {
      for (String commands : this.runCommands) {
        input = new Scanner(commands);
        commandExecution(input);
      }
      this.runCommands = new ArrayList<>();
    } else {
      commandExecution(input);
    }

  }

  @Override
  public void commandExecution(Scanner input) throws FileNotFoundException {
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


}
