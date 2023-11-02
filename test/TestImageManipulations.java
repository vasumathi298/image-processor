import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

import imagecontroller.ImageProcessingController;
import imagecontroller.ImageProcessingControllerImpl;
import imagemodel.ImageProcessingModel;
import imagemodel.ImageProcessingModelImpl;
import imagemodel.RGB;

import static org.junit.Assert.assertTrue;


/**
 * Testing Image Manipulation operations.
 */
public class TestImageManipulations {

  @Test
  public void loadImage() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small");
    assertTrue(image.length > 0);
  }

  @Test(expected = FileNotFoundException.class)
  public void testLoadImageWrongObject() throws Exception {
    String input = "load output/a.ppm a";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    model.retrieveImage("a");
  }


  /**
   * This is the test class check if image is flipped horizontally using controller or not.
   */
  @Test
  public void testFlipHorizontal() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\nhorizontal-flip manhattan-small manhattan-small-horizontal";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-horizontal");
    assertTrue(image.length > 0);
  }
}
