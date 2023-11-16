import org.junit.Test;

import java.io.ByteArrayInputStream;

import imagecontroller.ImageProcessingController;
import imagecontroller.ImageProcessingControllerImpl;
import imagemodel.ImageProcessingModel;
import imagemodel.ImageProcessingModelImpl;
import imagemodel.RGB;

import static org.junit.Assert.assertTrue;

public class TestEnhancedImageManipulations {

  @Test
  public void testRunScript() throws Exception {
    String input = "run output/commands.txt";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
    RGB[][] image = model.retrieveImage("manhattan-small-sepia-level-adjust-bright");
    assertTrue(image.length > 0);
  }

  @Test
  public void testImageColorCorrection() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "color-correct manhattan-small manhattan-small-color-corrected\ns" +
            "save manhattan-small-color-corrected.png manhattan-small-color-corrected-img";
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
    String input="load output/manhattan-small-color-corrected.png manhattan-small\n" +
            "histogram manhattan-small manhattan-small-histogram";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();

  }

  @Test
  public void testLevelAdjustment1() throws Exception {
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
  public void testLevelAdjustment2() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "levels-adjust 56 117 199 manhattan-small manhattan-small-level-adjust-threshold\n" +
            "save manhattan-small-level-adjust-threshold.png manhattan-small-level-adjust-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }
  @Test(expected=Exception.class)
  public void testLevelAdjustInvalidBlackValue() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "levels-adjust 100 20 255 manhattan-small manhattan-small-level-adjust\n" +
            "save manhattan-small-level-adjust.png manhattan-small-level-adjust-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }

  @Test(expected=Exception.class)
  public void testLevelAdjustInvalidBlackValue1() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "levels-adjust -20 100 255 manhattan-small manhattan-small-level-adjust\n" +
            "save manhattan-small-level-adjust.png manhattan-small-level-adjust-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }
  @Test(expected= Exception.class)
  public void testLevelAdjustInvalidWhiteValue() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "levels-adjust 20 255 255 manhattan-small manhattan-small-level-adjust\n" +
            "save manhattan-small-level-adjust.png manhattan-small-level-adjust-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }

  @Test(expected= Exception.class)
  public void testLevelAdjustInvalidWhiteValue1() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "levels-adjust 20 -100 255 manhattan-small manhattan-small-level-adjust\n" +
            "save manhattan-small-level-adjust.png manhattan-small-level-adjust-img";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }


  @Test(expected= Exception.class)
  public void testLevelAdjustInvalidMidValue1() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\n" +
            "levels-adjust 20 100 256 manhattan-small manhattan-small-level-adjust\n" +
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
  public void testHistogramForOriginalImage() throws Exception {
    String input="load output/manhattan-small.png manhattan-small\nhistogram manhattan-small manhattan-small-histogram";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }

  @Test
  public void testHistogramForColorCorrectedImage() throws Exception{
    String input="load output/manhattan-small-color-correct.png manhattan-small-color-correct\nhistogram manhattan-small-color-correct manhattan-small-color-correct-histogram";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }

  @Test
  public void testHistogramForLevelAdjustedImage() throws Exception{
    String input="load output/manhattan-small-level-adjust.png manhattan-small-level-adjust\n" +
            "histogram manhattan-small-level-adjust manhattan-small-level-adjust-histogram";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, model);
    controller.imageOperationSelector();
  }

  @Test
  public void testHistogramForLevelAdjustedImage1() throws Exception{
    String input="load output/manhattan-small-level-adjust-threshold.png manhattan-small-level-adjust\n" +
            "histogram manhattan-small-level-adjust manhattan-small-level-adjust-threshold-histogram";
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
