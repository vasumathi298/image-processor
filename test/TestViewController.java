import org.junit.Test;

import java.io.ByteArrayInputStream;

import imagecontroller.Features;
import imagecontroller.ImageProcessingControllerCommandImpl;
import imagemodel.ImageProcessingModel;
import imagemodel.ImageProcessingModelImpl;
import imagemodel.RGB;
import imageview.ImageProcessingView;
import imageview.ImageProcessingViewImpl;

import static org.junit.Assert.assertTrue;


/**
 * View controller test cases.
 */
public class TestViewController {

  @Test
  public void testSepia() {
    String input = "sepia manhattan-small manhattan-small-sepia-split";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sepiaTone(50);
    RGB[][] image = model.retrieveImage("manhattan-small-sepia-split");
    assertTrue(image.length > 0);
  }


  @Test
  public void testSepiaWithoutSplit() {
    String input = "sepia manhattan-small manhattan-small-sepia";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sepiaTone(100);
    RGB[][] image = model.retrieveImage("manhattan-small-sepia");
    assertTrue(image.length > 0);
  }

  @Test
  public void testBlur() {
    String input = "blur manhattan-small manhattan-small-blur-split";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.blur(50);
    RGB[][] image = model.retrieveImage("manhattan-small-blur-split");
    assertTrue(image.length > 0);
  }

  @Test
  public void testBlurWithoutSplit() {
    String input = "blur manhattan-small manhattan-small-blur";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.blur(100);
    RGB[][] image = model.retrieveImage("manhattan-small-blur");
    assertTrue(image.length > 0);
  }


  @Test
  public void testSharpen() {
    String input = "sharpen manhattan-small manhattan-small-sharpen-split";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sharpen(50);
    RGB[][] image = model.retrieveImage("manhattan-small-sharpen-split");
    assertTrue(image.length > 0);
  }

  @Test
  public void testSharpenWithoutSplit() {
    String input = "sharpen manhattan-small manhattan-small-sharpen";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sharpen(100);
    RGB[][] image = model.retrieveImage("manhattan-small-sharpen");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLevelsAdjust() {
    String input = "levels-adjust 10 120 252 manhattan-small manhattan-small-level-adjust-split";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sharpen(50);
    RGB[][] image = model.retrieveImage("manhattan-small-level-adjust-split");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLevelsAdjustWithoutSplit() {
    String input = "levels-adjust 10 120 252 manhattan-small manhattan-small-level-adjust";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sharpen(100);
    RGB[][] image = model.retrieveImage("manhattan-small-level-adjust");
    assertTrue(image.length > 0);
  }

  @Test
  public void testColorCorrect() {
    String input = "color-correct manhattan-small manhattan-small-color-correct-split";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sharpen(50);
    RGB[][] image = model.retrieveImage("manhattan-small-color-correct-split");
    assertTrue(image.length > 0);
  }

  @Test
  public void testColorCorrectWithoutSplit() {
    String input = "color-correct manhattan-small manhattan-small-color-correct";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sharpen(100);
    RGB[][] image = model.retrieveImage("manhattan-small-color-correct");
    assertTrue(image.length > 0);
  }


  @Test
  public void testBrightenWithoutSplit() {
    String input = "brighten 20 manhattan-small manhattan-small-brighten";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sharpen(100);
    RGB[][] image = model.retrieveImage("manhattan-small-brighten");
    assertTrue(image.length > 0);
  }

  @Test
  public void testBrightenSplit() {
    String input = "brighten -20 manhattan-small manhattan-small-brighten-split";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sharpen(50);
    RGB[][] image = model.retrieveImage("manhattan-small-brighten-split");
    assertTrue(image.length > 0);
  }


  @Test
  public void testCompress() {
    String input = "compress 20 manhattan-small manhattan-small-compress";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerCommandImpl(in, view, model);
    controller.sharpen(50);
    RGB[][] image = model.retrieveImage("manhattan-small-compress");
    assertTrue(image.length > 0);
  }
}
