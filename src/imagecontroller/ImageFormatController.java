package imagecontroller;

import imagemodel.RGB;

/**
 * The ImageFormatController interface defines the contract for handling different image formats.
 * Implementations of this interface provide methods to save and load images in various formats.
 */
public interface ImageFormatController {

  /**
   * Saves the provided image as a specific format at the specified file path.
   *
   * @param path         The file path where the image will be saved.
   * @param imageToSave  The 2D array of RGB pixels representing the image to be saved.
   * @throws Exception if an error occurs during the saving process.
   */
  void save(String path, RGB[][] imageToSave) throws Exception;

  /**
   * Loads an image from the specified file path and returns it as an RGB array.
   *
   * @param path           The file path of the image to be loaded.
   * @param fileNameToLoad The name of the image file to load.
   * @return An RGB array representing the loaded image.
   * @throws Exception if an error occurs during the loading process.
   */
  RGB[][] load(String path, String fileNameToLoad) throws Exception;
}
