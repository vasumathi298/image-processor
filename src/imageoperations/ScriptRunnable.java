package imageoperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import imagecontroller.ImageOperationController;
import imagecontroller.ImageProcessingController;
import imagecontroller.ImageProcessingControllerImpl;
import imagemodel.ImageProcessingModel;

/**
 * The `ScriptRunnable` class implements the `ImageOperationController`
 * interface and is used to run
 * a script file containing a sequence of image processing instructions.
 */
public class ScriptRunnable implements ImageOperationController {
  private final String[] instruction;
  private final List<String> instructionsToRun;

  /**
   * Constructs a `ScriptRunnable` object and prepares the list of instructions to be executed from
   * the specified script file.
   *
   * @param input The input string containing the script file path.
   */
  public ScriptRunnable(String input) {
    this.instruction = input.split(" ");
    this.instructionsToRun = new ArrayList<>();
    runScriptInstructions();
  }

  /**
   * Executes the list of image processing instructions
   * from the script using the provided ImageProcessingModel`.
   *
   * @param imageProcessingModel  model responsible for applying the instructions.
   * @throws Exception If an error occurs during script execution.
   */
  @Override
  public void performOperation(ImageProcessingModel imageProcessingModel) throws Exception {
    for (String runCommand : this.instructionsToRun) {
      ImageProcessingController controller =
              new ImageProcessingControllerImpl(null, imageProcessingModel);
      Scanner sc = new Scanner(runCommand);
      controller.operationProcessor(sc);
    }
  }

  private void runScriptInstructions() {
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
