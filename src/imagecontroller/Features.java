package imagecontroller;

import java.io.FileNotFoundException;

import imageview.ImageProcessingView;

/**
 * This interface represents the operations that can be done using the GUI that will be handled by
 * controller.
 */
public interface Features extends ImageProcessingController {


  /**
   * A method to load the image from the view from the GUI.
   *
   * @param imageName the path of the image.
   */
  void loadImage(String imageName);

  /**
   * A method to call the save image function from the GUI.
   */
  void saveImage();

  /**
   * A method to vertically flip the image from the GUI.
   */
  void verticalFlip();

  /**
   * A method to horizontally flip the image from the GUI.
   */
  void horizontalFlip();


  /**
   * A method to generate a greyscale of the image based on value component from the GUI.
   */
  void valueGrayscale();

  /**
   * A method to generate a grayscale of the image based on luma component from the GUI.
   */
  void lumaGrayscale(double splitPercentage);

  /**
   * A method to generate a grayscale of the image based on intensity component from the GUI.
   */
  void intensityGrayscale();

  /**
   * A method to generate a grayscale of the image based on red component from the GUI.
   */
  void redGrayscale() throws FileNotFoundException;

  /**
   * A method to generate a grayscale of the image based on blue component from the GUI.
   */
  void blueGrayscale() throws FileNotFoundException;

  /**
   * A method to generate a grayscale of the image based on green component from the GUI.
   */
  void greenGrayscale() throws FileNotFoundException;

  void getRedComponent() throws FileNotFoundException;

  void getGreenComponent() throws FileNotFoundException;

  void getBlueComponent() throws FileNotFoundException;

  void getColorCorrectedImage(double splitPercentage) throws FileNotFoundException;

  /**
   * A method to perform blur operation on the image from the GUI.
   */
  void blur(double splitPercentage);




  void sepiaTone(double splitPercentage);


  /**
   * A method to perform sharpen operation on the image from the GUI.
   */
  void sharpen(double splitPercentage);

  /**
   * A method to increase or decrease the brightness of the image.
   *
   * @param value the value based on which the brightness of the image will be changed.
   */
  void brightness(int value);

  /**
   * Level adjust the image.
   * @param b b value.
   * @param m m value.
   * @param w w value.
   * @param splitPercentage split percentage.
   */
  void levelsAdjust(int b, int m, int w, double splitPercentage);

  /**
   * This method will combine image and display the combined image in GUI.
   */
  void combineImage();


  /**
   * Generate the GUI and link all the features to it.
   *
   * @param view an ImageProcessingView object.
   */
  void setView(ImageProcessingView view);

  /**
   * Compress the image.
   * @param threshold threshold as percentage.
   */
  void compression( double threshold);

  /**
   * Revert the image to view only split.
   */
  void revert();

}

