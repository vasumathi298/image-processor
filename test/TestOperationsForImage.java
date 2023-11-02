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
 * The `TestOperationsForImage` class is a  class
 * intended for testing various image operations
 * for images in the PNG (Portable Network Graphics) format.
 * This class is used for testing and verifying the functionality
 * of different image processing operations on PNG images.
 */
public class TestOperationsForImage {

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
    String input = "load output/a.png a";
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
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "horizontal-flip manhattan-small manhattan-small-horizontal";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-horizontal");
    assertTrue(image.length > 0);
  }


  @Test
  public void testFlipVertical() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "vertical-flip manhattan-small manhattan-small-vertical";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-vertical");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is brighten or not.
   */
  @Test
  public void testBrightenImage() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "brighten +50 manhattan-small manhattan-small-brighter-by-50";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-brighter-by-50");
    assertTrue(image.length > 0);
  }


  @Test
  public void testDarkenImage() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "brighten -50 manhattan-small manhattan-small-darker-by-50";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-darker-by-50");
    assertTrue(image.length > 0);
  }

  @Test
  public void testFlipVerticalHorizontal() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "vertical-flip manhattan-small manhattan-small-vertical"
            + "\nhorizontal-flip manhattan-small manhattan-small-vertical-horizontal";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-vertical-horizontal");
    assertTrue(image.length > 0);
  }


  @Test
  public void testRedGreyScale() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale red-component manhattan-small manhattan-small-red-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-red-grey");
    assertTrue(image.length > 0);
  }

  @Test
  public void testGreenGreyScale() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale green-component manhattan-small manhattan-small-green-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-green-grey");
    assertTrue(image.length > 0);
  }

  @Test
  public void testBlueGreyScale() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale blue-component manhattan-small manhattan-small-blue-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-blue-grey");
    assertTrue(image.length > 0);
  }


  @Test
  public void testLumaGreyScale() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale luma-component manhattan-small manhattan-small-luma-greyscale";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-luma-greyscale");
    assertTrue(image.length > 0);
  }

  @Test
  public void testIntensityGreyScale() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale intensity-component manhattan-small manhattan-small-intensity-greyscale";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-intensity-greyscale");
    assertTrue(image.length > 0);
  }

  @Test
  public void testValueyGreyScale() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale value-component manhattan-small manhattan-small-value-greyscale";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-value-greyscale");
    assertTrue(image.length > 0);
  }

  @Test
  public void testRgbSplit() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\nrgb-split manhattan-small "
            + "manhattan-small-red-grey manhattan-small-green-grey manhattan-small-blue-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-red-grey");
    RGB[][] image2 = model.retrieveImage("manhattan-small-green-grey");
    RGB[][] image3 = model.retrieveImage("manhattan-small-blue-grey");
    assertTrue(image.length > 0);
    assertTrue(image2.length > 0);
    assertTrue(image3.length > 0);

  }

  @Test
  public void testRgbCombine() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\nrgb-split manhattan-small "
            + "manhattan-small-red-grey manhattan-small-green-grey manhattan-small-blue-grey\n"
            + "rgb-combine manhattan-small-red-grey manhattan-small-green-grey "
            + "manhattan-small-blue-grey rgb-combine-image";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("rgb-combine-image");
    assertTrue(image.length > 0);
  }

  @Test
  public void testSepiaTone() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "color-transform sepia manhattan-small manhattan-small-sepia";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-sepia");
    assertTrue(image.length > 0);
  }

  @Test
  public void testFilterBlur() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "filter blur manhattan-small manhattan-small-blur";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-blur");
    assertTrue(image.length > 0);
  }

  @Test
  public void testFilterSharpen() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "filter sharpen manhattan-small manhattan-small-sharpen";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-sharpen");
    assertTrue(image.length > 0);
  }

  @Test
  public void testSaveImage() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "brighten 20 manhattan-small manhattan-small-brighter-test\n"
            + "save output/manhattan-small.png manhattan-small-brighter-test\n"
            + "load output/manhattan-small-brighter-test.png manhattan-copy";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-copy");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadPNGSavePPM() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "save output/manhattan-small.png manhattan-small-ppm.png\n"
            + "load output/manhattan-small-ppm.ppm manhattan-small-png-ppm";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-png-ppm");
    assertTrue(image.length > 0);
  }


  @Test
  public void testLoadPNGSaveBMP() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "save output/manhattan-small.bmp manhattan-small\n"
            + "load output/manhattan-small.bmp manhattan-small-bmp";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-bmp");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadPNGSaveJPEG() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "save output/manhattan-small.jpeg manhattan-small-jpeg\n"
            + "load output/manhattan-small-jpeg.jpeg manhattan-small-jpeg-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-jpeg-test");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadJPEGSavePNG() throws Exception {
    String input = "load output/manhattan-small.jpeg manhattan-small\n"
            + "save output/manhattan-small.png manhattan-small-png\n"
            + "load output/manhattan-small-png.png manhattan-small-png-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-png-test");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadJPEGSaveBPM() throws Exception {
    String input = "load output/manhattan-small.jpeg manhattan-small\n"
            + "save output/manhattan-small.bpm manhattan-small-bpm\n"
            + "load output/manhattan-small-bpm.bpm manhattan-small-bpm-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-bpm-test");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadJPEGSavePPM() throws Exception {
    String input = "load output/manhattan-small.jpeg manhattan-small\n"
            + "save output/manhattan-small.ppm manhattan-small-ppm\n"
            + "load output/manhattan-small-ppm.bpm manhattan-small-ppm-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-ppm-test");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadPPMtoJPEG() throws Exception {
    String input = "load output/manhattan-small.ppm manhattan-small\n"
            + "save output/manhattan-small.jpeg manhattan-small-jpeg\n"
            + "load output/manhattan-small-jpeg.jpeg manhattan-small-jpeg-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-ppm-test");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadPPMtoPNG() throws Exception {
    String input = "load output/manhattan-small.ppm manhattan-small\n"
            + "save output/manhattan-small.png manhattan-small-png\n"
            + "load output/manhattan-small-png.png manhattan-small-png-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-png-test");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadPPMtoBPM() throws Exception {
    String input = "load output/manhattan-small.ppm manhattan-small\n"
            + "save output/manhattan-small.bpm manhattan-small-bpm\n"
            + "load output/manhattan-small-bpm.bpm manhattan-small-bpm-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-bpm-test");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadBPMtoJPEG() throws Exception {
    String input = "load output/manhattan-small.bpm manhattan-small\n"
            + "save output/manhattan-small.bpm manhattan-small-bpm\n"
            + "load output/manhattan-small-bpm.bpm manhattan-small-bpm-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-bpm-test");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadBPMtoPNG() throws Exception {
    String input = "load output/manhattan-small.bpm manhattan-small\n"
            + "save output/manhattan-small.png manhattan-small-png\n"
            + "load output/manhattan-small-png.png manhattan-small-png-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-png-test");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLoadBPMtoPPM() throws Exception {
    String input = "load output/manhattan-small.bpm manhattan-small\n"
            + "save output/manhattan-small.ppm manhattan-small-ppm\n"
            + "load output/manhattan-small-ppm.ppm manhattan-small-ppm-test";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-ppm-test");
    assertTrue(image.length > 0);
  }


  @Test
  public void testRunScript() throws Exception {
    String input = "run output/commands.txt";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("output-image");
    assertTrue(image.length > 0);
  }

}
