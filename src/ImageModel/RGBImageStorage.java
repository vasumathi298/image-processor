package ImageModel;


import java.util.HashMap;
import java.util.Map;

/**
 * This is our HashstorageImpl class.
 * This class holds the methods for adding, fetching pixel array objects and string as key-value
 * pairs.
 */
public class RGBImageStorage {

  private final Map<String, RGB[][]> computedCommands;

  public RGBImageStorage() {
    this.computedCommands = new HashMap<>();
  }

  public void addImage(String imageName, RGB[][] inputImage) {
    this.computedCommands.put(imageName, inputImage);
  }

  public RGB[][] fetchImage(String imageName) {
    return computedCommands.get(imageName);
  }

  public boolean search(String imageName) {
    return computedCommands.containsKey(imageName);
  }
}
