package imageformat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import imagecontroller.ImageFormatController;
import imagemodel.RGB;

/**
 * The BPMFormat class implements the ImageFormatController interface
 * for saving and loading images in BMP format.
 */
public class BPMFormat implements ImageFormatController {

  /**
   * Saves the specified image data in BMP format to the given file path.
   *
   * @param filePath       The path where the image should be saved.
   * @param saveThisImage  The RGB pixel data to be saved.
   * @throws Exception      If an error occurs during saving.
   */
  @Override
  public void save(String filePath, RGB[][] saveThisImage) throws Exception {
    int w;
    int h;
    File savedImage = new File(filePath);
    w = saveThisImage[0].length;
    h = saveThisImage.length;
    BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    for (int k = 0; k < w; k++) {
      for (int l = 0; l < h; l++) {
        int r = saveThisImage[k][l].getPixel(0);
        int g = saveThisImage[k][l].getPixel(1);
        int b = saveThisImage[k][l].getPixel(2);
        int pixels = (r << 16) | b | (g << 8);
        newImage.setRGB(l, k, pixels);
      }
    }
    try {
      ImageIO.write(newImage, "bmp", savedImage);
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  /**
   * Loads an image from the given file path in BMP format.
   *
   * @param path  The path to the BMP image file.
   * @param name  A name associated with the image.
   * @return      A 2D array of RGB pixels representing the loaded image.
   * @throws Exception  If an error occurs during loading.
   */
  @Override
  public RGB[][] load(String path, String name) throws Exception {
    File fileToLoad = new File(path);
    if (!path.toLowerCase().endsWith(".bmp")) {
      throw new IllegalArgumentException("Input Only BMP files.");
    }
    if (!fileToLoad.exists()) {
      throw new FileNotFoundException("File is not present in the Directory.");
    }

    RGB[][] loadingBMPImage;
    int w;
    int h;
    try {
      BufferedImage readImage = ImageIO.read(new File(path));
      w = readImage.getWidth();
      h = readImage.getHeight();
      loadingBMPImage = new RGB[h][w];
      for (int k = 0; k < h; k++) {
        for (int l = 0; l < w; l++) {
          int pixels = readImage.getRGB(l, k);

          int r = (pixels >> 16) & 0xFF;
          int b = pixels & 0xFF;
          int g = (pixels >> 8) & 0xFF;

          loadingBMPImage[k][l] = new RGB(r, g, b);
        }
      }
    } catch (Exception e) {
      throw new Exception(e);
    }
    return loadingBMPImage;
  }
}
