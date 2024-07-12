
import javax.swing.UIManager;

import imagecontroller.Features;
import imagecontroller.ImageProcessingController;
import imagecontroller.ImageProcessingControllerCommandImpl;
import imagecontroller.ImageProcessingControllerImpl;
import imagemodel.ImageProcessingModel;
import imagemodel.ImageProcessingModelImpl;
import imageoperations.ScriptRunnable;
import imageview.ImageProcessingView;
import imageview.ImageProcessingViewImpl;


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
    else if (args.length > 0 && args[0].equals("-text")) {
      if (args.length != 2) {
        System.out.println("Usage: java Main -file name-of-script.txt");
        return;
      }
      ImageProcessingController controller = new ImageProcessingControllerImpl(System.in,
              model);
      controller.imageOperationSelector();
      return;
    }
    else {
      ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
      Features controller1 = new ImageProcessingControllerCommandImpl(System.in, view, model);
      controller1.setView(view);
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
  }
}