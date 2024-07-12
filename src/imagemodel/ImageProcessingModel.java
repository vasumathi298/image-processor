package imagemodel;


import java.io.FileNotFoundException;

/**
 * The ImageProcessingModel interface defines the contract for performing various image
 * processing operations. Implementations of this interface are responsible for loading,
 * transforming, and saving images.
 */
public interface ImageProcessingModel {

  /**
   * Load an image and store it with the given name.
   *
   * @param img   2D array of RGB pixels representing the image.
   * @param name  A name associated with the image.
   * @throws FileNotFoundException If the specified image file is not found.
   */

  void imageLoader(RGB[][] img, String name) throws FileNotFoundException;


  /**
   * Construct a red grayscale version of the image and store it with the provided name.
   *
   * @param img     The name of the source image.
   * @param storage The name to associate with the generated red grayscale image.
   */
  void constructRedGreyScale(String img, String storage);


  /**
   * Construct a blue grayscale version of the image and store it with the provided name.
   *
   * @param img     The name of the source image.
   * @param storage The name to associate with the generated blue grayscale image.
   */
  void constructBlueGreyScale(String img, String storage);


  /**
   * Construct a green grayscale version of the image and store it with the provided name.
   *
   * @param img     The name of the source image.
   * @param storage The name to associate with the generated green grayscale image.
   */
  void constructGreenGreyScale(String img, String storage);


  /**
   * Convert the image to grayscale using luma values and store it with the provided name.
   *
   * @param img     The name of the source image.
   * @param storage The name to associate with the generated luma grayscale image.
   */
  void greyScaleLuma(String img, String storage,double splitPercentage);

  /**
   * Convert the image to grayscale using value component and store it with the provided name.
   *
   * @param img     The name of the source image.
   * @param storage The name to associate with the generated value grayscale image.
   */

  void greyScaleValue(String img, String storage);

  /**
   * Convert the image to grayscale using intensity values and store it with the provided name.
   *
   * @param img     The name of the source image.
   * @param storage The name to associate with the generated intensity grayscale image.
   */

  void greyScaleIntensity(String img, String storage);

  /**
   * Flip the image vertically and store the result with the provided name.
   *
   * @param img     The name of the source image to flip vertically.
   * @param storage The name to associate with the vertically flipped image.
   */
  void verticalImageFlip(String img, String storage);

  /**
   * Flip the image horizontally and store the result with the provided name.
   *
   * @param img     The name of the source image to flip horizontally.
   * @param storage The name to associate with the horizontally flipped image.
   */
  void horizontalImageFlip(String img, String storage);

  /**
   * Adjust the brightness of the image by a specified increment or decrement value and store it.
   *
   * @param img        The name of the source image to adjust brightness.
   * @param storage    The name to associate with the adjusted brightness image.
   * @param incOrDecVal The increment or decrement value for brightness adjustment.
   */
  void brightenOrDarkenImage(String img, String storage, int incOrDecVal);

  /**
   * Apply sharpening filter to the image and store the result with the provided name.
   *
   * @param img     The name of the source image to sharpen.
   * @param storage The name to associate with the sharpened image.
   */
  void sharpenImage(String img, String storage,double splitPercentage);

  /**
   * Convert the image to sepia tone and store it with the provided name.
   *
   * @param img     The name of the source image to convert to sepia.
   * @param storage The name to associate with the sepia-toned image.
   */
  void constructSepia(String img, String storage, double splitPercentage);

  /**
   * Apply a blur effect to the image and store the result with the provided name.
   *
   * @param img     The name of the source image to apply the blur effect.
   * @param storage The name to associate with the blurred image.
   */
  void blurImage(String img, String storage, double splitPercentage);

  /**
   * Save an image to a specified path with the provided name.
   *
   * @param path The file path to save the image.
   * @param img  The name of the image to be saved.
   * @return A 2D array of RGB pixels representing the saved image.
   */
  RGB[][] saveFile(String path, String img);

  /**
   * Split an image into three red, green, and blue components
   * and store them with the provided names.
   *
   * @param img      The name of the source image to split.
   * @param storage  An array containing three names to associate with the split components.
   */
  void imageSplitter(String img, String[] storage);

  /**
   * Merge three images (red, green, blue components) into a single RGB image and store it.
   *
   * @param img     An array containing names of the red, green, and blue components.
   * @param storage The name to associate with the merged RGB image.
   */
  void imageMerger(String[] img, String storage);

  /**
   * Retrieve an image with the specified name.
   *
   * @param img The name of the image to retrieve.
   * @return A 2D array of RGB pixels representing the retrieved image.
   */
  RGB[][] retrieveImage(String img);


  /**
   * Return red component of image.
   * @param fileName source name
   * @param destImage destination name.
   */
  void redComponent(String fileName, String destImage);

  /**
   * Return green component of image.
   * @param fileName source name
   * @param destImage destination name.
   */
  void greenComponent(String fileName, String destImage);

  /**
   * Return blue component of image.
   * @param fileName source name
   * @param destImage destination name.
   */
  void blueComponent(String fileName, String destImage);
}

