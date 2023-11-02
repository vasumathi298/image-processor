package imagemodel;

import java.util.HashMap;
import java.util.Map;

/**
 * The `RGBImageStorage` class is responsible for storing and retrieving pixel arrays associated
 * with image names using a key-value map.
 */
public class RGBImageStorage {

  private final Map<String, RGB[][]> imageStorageMap;

  /**
   * Constructs an `RGBImageStorage` object and initializes the internal storage map.
   */
  public RGBImageStorage() {
    this.imageStorageMap = new HashMap<>();
  }

  /**
   * Stores the provided pixel array under the specified image name.
   *
   * @param imageName   The name under which the image is stored.
   * @param inputImage  The pixel array representing the image.
   */
  public void storeImage(String imageName, RGB[][] inputImage) {
    this.imageStorageMap.put(imageName, inputImage);
  }

  /**
   * Retrieves the pixel array associated with the given image name.
   *
   * @param imageName  The name of the image to retrieve.
   * @return The pixel array representing the image, or null if the image is not found.
   */
  public RGB[][] retrieveImage(String imageName) {
    return imageStorageMap.get(imageName);
  }

  /**
   * Checks if an image with the specified name exists in the storage.
   *
   * @param imageName  The name of the image to check for existence.
   * @return `true` if the image exists in the storage, `false` otherwise.
   */
  public boolean findImage(String imageName) {
    return imageStorageMap.containsKey(imageName);
  }
}
