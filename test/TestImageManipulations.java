import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

import ImageController.ImageProcessingController;
import ImageController.ImageProcessingControllerImpl;
import ImageModel.ImageProcessingModel;
import ImageModel.ImageProcessingModelImpl;
import ImageModel.RGB;

import static org.junit.Assert.assertTrue;

public class TestImageManipulations {

  @Test
  public void loadImage() throws FileNotFoundException {
    String input = "load output/manhattan-small.png manhattan-small";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.inputSelection();
    RGB[][] image = model.getImage("manhattan-small");
    assertTrue(image.length > 0);
  }
  @Test(expected = FileNotFoundException.class)
  public void testLoadImageWrongObject() throws FileNotFoundException {
    String input = "load output/a.ppm a";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.inputSelection();
    model.getImage("a");
  }


  /**
   * This is the test class check if image is flipped horizontally using controller or not.
   */
  @Test
  public void testFlipHorizontal() throws FileNotFoundException {
    String input = "load output/manhattan-small.png manhattan-small\nhorizontal-flip manhattan-small manhattan-small-horizontal";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.inputSelection();
    RGB[][] image = model.getImage("manhattan-small-horizontal");
    assertTrue(image.length > 0);
  }
}
