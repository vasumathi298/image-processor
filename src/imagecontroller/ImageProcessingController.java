package imagecontroller;

import java.util.Scanner;

/**
 * The ImageProcessingController interface represents the operations of a controller that will be
 * handled by the controller.
 */
public interface ImageProcessingController {

  /**
   * Perform the selection of image processing operations.
   *
   * @throws Exception If an error occurs during the operation selection.
   */
  void imageOperationSelector() throws Exception;

  /**
   * Process the selected image operation based on the provided Scanner input.
   *
   * @param operation The Scanner input containing the operation details.
   * @throws Exception If an error occurs during the operation processing.
   */
  void operationProcessor(Scanner operation) throws Exception;

}
