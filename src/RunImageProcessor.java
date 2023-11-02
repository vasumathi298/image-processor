import imagecontroller.ImageProcessingController;
import imagecontroller.ImageProcessingControllerImpl;
import imagemodel.ImageProcessingModel;
import imagemodel.ImageProcessingModelImpl;
import imageoperations.ScriptRunnable;


/**
 * This is the main function that will initiate and run the application.
 */
public class RunImageProcessor {
  /**
   * Returns void.
   *
   * @param args takes arguments.
   */
  public static void main(String[] args) throws Exception {

    ImageProcessingModel model = new ImageProcessingModelImpl();
    if (args.length > 0 && args[0].equals("-file")) {
      if (args.length != 2) {
        System.out.println("Usage: java Main -file name-of-script.txt");
        return;
      }

      String scriptFileName = args[1];
      ScriptRunnable scriptFile = new ScriptRunnable("-file " + scriptFileName);
      scriptFile.performOperation(model);
      return;
    }
    ImageProcessingController controller = new ImageProcessingControllerImpl(System.in,
            model);
    controller.imageOperationSelector();
  }
}