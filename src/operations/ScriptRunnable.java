package operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ImageController.ImageOperationController;
import ImageController.ImageProcessingController;
import ImageController.ImageProcessingControllerImpl;
import ImageModel.ImageProcessingModel;


/**
 * This is run script class which runs the text file.
 */
public class ScriptRunnable implements ImageOperationController {
  private final String[] commandList;
  private final List<String> runCommands;


  public ScriptRunnable(String input) {
    this.commandList = input.split(" ");
    this.runCommands = new ArrayList<>();
    processScript();
  }

  @Override
  public void performOperation(ImageProcessingModel model) throws FileNotFoundException {
    for (String runCommand : this.runCommands) {
      ImageProcessingController controller = new ImageProcessingControllerImpl(null,
               model);
      Scanner sc = new Scanner(runCommand);
      controller.commandExecution(sc);
    }

  }

  /**
   * This is processes all the inputs received from text file.
   */
  private void processScript() {
    try {
      File file = new File(commandList[1]);
      if (!file.exists()) {
        throw new FileNotFoundException("File Not Found!");
      }
      if (commandList.length != 2) {
        throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
      }
      if (!commandList[1].endsWith(".txt")) {
        throw new IllegalArgumentException("Only .txt scripts are accepted!");
      }
      BufferedReader br = new BufferedReader(new FileReader(file));

      String line = br.readLine();
      while (line != null) {
        if (!line.isBlank()) {
          if (line.charAt(0) != '#' && !line.trim().isEmpty()) {
            this.runCommands.add(line);
          }
        }
        line = br.readLine();
      }
      br.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }


}