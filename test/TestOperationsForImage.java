import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

import imagecontroller.ImageProcessingController;
import imagecontroller.ImageProcessingControllerImpl;
import imagemodel.ImageProcessingModel;
import imagemodel.ImageProcessingModelImpl;
import imagemodel.RGB;

import static org.junit.Assert.assertEquals;
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

  @Test
  public void testImageColorCorrection() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "color-correct manhattan-small manhattan-small-color-corrected\ns" +
            "ave manhattan-small-color-corrected.png manhattan-small-color-corrected-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-color-corrected");
    assertTrue(image.length > 0);
  }

  @Test
  public void testImageColorCorrectionSplit() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "color-correct manhattan-small manhattan-small-color-corrected-split split 40\n" +
            "save manhattan-small-color-corrected-split.png manhattan-small-color-corrected-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-color-corrected-split");
    assertTrue(image.length > 0);
  }

  @Test
  public void testImageHistogram() throws Exception {
    String input="load output/manhattan-small-color-corrected.png manhattan-small\nhistogram manhattan-small manhattan-small-histogram";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();

  }

  @Test
  public void testLevelAdjustment() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "levels-adjust 20 100 255 manhattan-small manhattan-small-level-adjust\n" +
            "save manhattan-small-level-adjust.png manhattan-small-level-adjust-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }

  @Test
  public void testLevelAdjustmentSplit() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "levels-adjust 20 100 255 manhattan-small manhattan-small-level-adjust-split split 60\n" +
            "save manhattan-small-level-adjust-split.png manhattan-small-level-adjust-split-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }


  @Test
  public void testImageCompression() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\ncompress 20 manhattan-small manhattan-small-compress\nsave manhattan-small-compress.png manhattan-small-compress-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }

  /**
   * Simple Operations
   */

  @Test
  public void testVerticalFlip() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "vertical-flip manhattan-small manhattan-small-vertical-flip\n"
            +"save manhattan-small-vertical-flip.png manhattan-vertical-flip-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-vertical-flip");
    assertTrue(image.length > 0);
  }

  @Test
  public void testHorizontalFlip() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "horizontal-flip manhattan-small manhattan-small-horizontal-flip\n"
            +"save manhattan-small-horizontal-flip.png manhattan-horizontal-flip-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-horizontal-flip");
    assertTrue(image.length > 0);
  }

  @Test
  public void testColorCorrect() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "color-correct manhattan-small manhattan-small-color-correct\n"
            +"save manhattan-small-color-correct.png manhattan-color-correct-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-color-correct");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLevelAdjust() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "levels-adjust 20 100 255 manhattan-small manhattan-small-level-adjust\n"
            +"save manhattan-small-level-adjust.png manhattan-level-adjust-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-level-adjust");
    assertTrue(image.length > 0);
  }

  @Test
  public void testHistogram() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\nhistogram manhattan-small manhattan-small-histogram";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }

  @Test
  public void testBrighten() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "brighten 100 manhattan-small manhattan-small-brighten\n"
            +"save manhattan-small-brighten.png manhattan-brighten-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-brighten");
    assertTrue(image.length > 0);
  }

  @Test
  public void testDarken() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "brighten -100 manhattan-small manhattan-small-darken\n"
            +"save manhattan-small-darken.png manhattan-darken-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-darken");
    assertTrue(image.length > 0);
  }

  @Test
  public void testRedComp() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "red-component manhattan-small manhattan-small-red-comp\n"
            +"save manhattan-small-red-comp.png manhattan-red-comp-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-red-comp");
    assertTrue(image.length > 0);
  }

  @Test
  public void testBlueComp() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "blue-component manhattan-small manhattan-small-blue-comp\n"
            +"save manhattan-small-blue-comp.png manhattan-blue-comp-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-blue-comp");
    assertTrue(image.length > 0);
  }


  @Test
  public void testGreenComp() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "green-component manhattan-small manhattan-small-green-comp\n"
            +"save manhattan-small-green-comp.png manhattan-green-comp-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-green-comp");
    assertTrue(image.length > 0);
  }

  @Test
  public void testSepia() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "sepia manhattan-small manhattan-small-sepia\n"
            +"save manhattan-small-sepia.png manhattan-sepia-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-sepia");
    assertTrue(image.length > 0);
  }

  @Test
  public void testSepiaSplit() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "sepia manhattan-small manhattan-small-sepia-split split 70\n"
            +"save manhattan-small-sepia-split.png manhattan-sepia-split-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-sepia-split");
    assertTrue(image.length > 0);
  }

  @Test
  public void testBlur() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur\n"
            +"save manhattan-small-blur.png manhattan-blur-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-blur");
    assertTrue(image.length > 0);
  }

  @Test
  public void testBlurSplit() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "blur manhattan-small manhattan-small-blur-split split 50\n"
            +"save manhattan-small-blur-split.png manhattan-blur-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-blur-split");
    assertTrue(image.length > 0);
  }

  @Test
  public void testSharpen() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "sharpen manhattan-small manhattan-small-sharpen\n"
            +"save manhattan-small-sharpen.png manhattan-sharpen-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-sharpen");
    assertTrue(image.length > 0);
  }

  @Test
  public void testSharpenSplit() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "sharpen manhattan-small manhattan-small-sharpen-split split 50\n"
            +"save manhattan-small-sharpen-split.png manhattan-sharpen-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-sharpen-split");
    assertTrue(image.length > 0);
  }

  @Test
  public void testRgbSplitImage() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\nrgb-split manhattan-small "
            + "manhattan-small-red-grey manhattan-small-green-grey manhattan-small-blue-grey\n"
            +"save manhattan-small-red-grey.png manhattan-small-red-grey-img\n"
            +"save manhattan-small-green-grey.png manhattan-small-green-grey-img\n"
            +"save manhattan-small-blue-grey.png manhattan-small-blue-grey-img\n";
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
  public void testRgbMergeImage() throws Exception {
    String input = "load manhattan-small-red-comp.png manhattan-small-red-comp\n" +
            "load manhattan-small-green-comp.png manhattan-small-green-comp\n" +
            "load manhattan-small-blue-comp.png manhattan-small-blue-comp\n" +
            "rgb-combine manhattan-small-red-comp " +
            "manhattan-small-green-comp manhattan-small-blue-comp manhattan-small-combine\n"
            +"save manhattan-small-combine.png manhattan-small-combine-img\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-combine");
    assertTrue(image.length > 0);
  }


  @Test
  public void testRedComponent() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale red-component manhattan-small manhattan-small-red-component\n"
            +"save manhattan-small-red-component.png manhattan-small-red-component-img\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-red-component");
    assertTrue(image.length > 0);
  }

  @Test
  public void testGreenComponent() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale green-component manhattan-small manhattan-small-green-component\n"
            +"save manhattan-small-green-component.png manhattan-small-green-component-img\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-green-component");
    assertTrue(image.length > 0);
  }


  @Test
  public void testBlueComponent() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale blue-component manhattan-small manhattan-small-blue-component\n"
            +"save manhattan-small-blue-component.png manhattan-small-blue-component-img\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-blue-component");
    assertTrue(image.length > 0);
  }

  @Test
  public void testValueComponent() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale value-component manhattan-small manhattan-small-value-component\n"
            +"save manhattan-small-value-component.png manhattan-small-value-component-img\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-value-component");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLumaComponent() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale luma-component manhattan-small manhattan-small-luma-component\n"
            +"save manhattan-small-luma-component.png manhattan-small-luma-component-img\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-luma-component");
    assertTrue(image.length > 0);
  }

  @Test
  public void testLumaComponentSplit() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale luma-component manhattan-small manhattan-small-luma-split-component split 35\n"
            +"save manhattan-small-luma-split-component.png manhattan-small-luma-component-img\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-luma-split-component");
    assertTrue(image.length > 0);
  }

  @Test
  public void testIntensityComponent() throws Exception {
    String input = "load output/manhattan-small.png manhattan-small\n"
            + "greyscale intensity-component manhattan-small manhattan-small-intensity-component\n"
            +"save manhattan-small-intensity-component.png manhattan-small-intensity-component-img\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-intensity-component");
    assertTrue(image.length > 0);
  }


}
