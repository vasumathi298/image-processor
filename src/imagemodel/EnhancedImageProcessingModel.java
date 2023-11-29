package imagemodel;

import java.io.IOException;

/**
 * Enhanced image processing model.
 */
public interface EnhancedImageProcessingModel extends ImageProcessingModel {

  /**
   * Fetch the histogram of the image.
   * @param imageName source image
   * @param destImageName destination image
   * @return return histogram for red, blue, green.
   * @throws IOException throws I0Exception
   */

  public int[][] fetchHistogram(String imageName, String destImageName) throws IOException;

  /**
   * Compress the image.
   * @param fileName source image
   * @param destName destination image.
   * @param threshold threshold pecentage.
   */
  public void compressImage(String fileName, String destName, double threshold);

  /**
   * Image color correction.
   * @param imageName source name
   * @param destImageName destination image
   * @param splitPercentage split percentage
   * @throws IOException I0Exception if any error occurs
   */
  public void imageColorCorrection(String imageName,
                                   String destImageName, double splitPercentage) throws IOException;

  /**
   * Level adjustment of the image.
   * @param imageName source name.
   * @param destImageName destination image.
   * @param b b value.
   * @param m m value.
   * @param w w value.
   * @param splitPercentage split percentage.
   */
  public void levelAdjust(String imageName, String destImageName,
                          int b, int m, int w, double splitPercentage);

  /**
   * Revert image.
   * @param imageFind source image.
   * @param revertImageName destination image.
   */
  public void revertImage(String imageFind, String revertImageName);

}
