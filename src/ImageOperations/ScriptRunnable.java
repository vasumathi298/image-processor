package ImageOperations;

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
  private final String[] instruction;
  private final List<String> instructionsToRun;


  public ScriptRunnable(String input) {
    this.instruction = input.split(" ");
    this.instructionsToRun = new ArrayList<>();
    runScriptInstrcutions();
  }

  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) throws Exception {
    for (String runCommand : this.instructionsToRun) {
      ImageProcessingController controller = new ImageProcessingControllerImpl(null,
               imageProcessingModel);
      Scanner sc = new Scanner(runCommand);
      controller.operationProcessor(sc);
    }

  }

  private void runScriptInstrcutions() {
    try {
      File file = new File(instruction[1]);
      if (!file.exists()) {
        throw new FileNotFoundException("File Not Found!");
      }
      if (!instruction[1].endsWith(".txt")) {
        throw new IllegalArgumentException("Only .txt scripts are accepted!");
      }
      if (instruction.length != 2) {
        throw new IllegalArgumentException("Enter a valid command");
      }

      BufferedReader br = new BufferedReader(new FileReader(file));

      String line = br.readLine();
      while (line != null) {
        if (!line.isBlank()) {
          if (line.charAt(0) != '#' && !line.trim().isEmpty()) {
            this.instructionsToRun.add(line);
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