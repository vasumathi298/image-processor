package ImageController;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This interface represents the operations of controller that will be handled by controller.
 */
public interface ImageProcessingController {


  void commandExecution(Scanner input) throws FileNotFoundException;


  void inputSelection() throws FileNotFoundException;
}