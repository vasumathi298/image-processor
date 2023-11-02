package imageformat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import imagecontroller.ImageFormatController;
import imagemodel.RGB;

/**
 * The JPEGFormat class implements the ImageFormatController interface
 * for saving and loading images in JPEG format.
 */
public class JPEGFormat implements ImageFormatController {

  /**
   * Saves the specified image data in JPEG format to the given file path.
   *
   * @param path       The path where the image should be saved.
   * @param saveThisImage  The RGB pixel data to be saved.
   * @throws Exception      If an error occurs during saving.
   */
  @Override
  public void save(String path, RGB[][] saveThisImage) throws Exception {
    int w;
    int h;
    File savedImage = new File(path);
    w = saveThisImage[0].length;
    h = saveThisImage.length;
    BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    for (int k = 0; k < h; k++) {
      for (int l = 0; l < w; l++) {
        int r = saveThisImage[k][l].getPixel(0);
        int g = saveThisImage[k][l].getPixel(1);
        int b = saveThisImage[k][l].getPixel(2);
        int pixels = (r << 16) | b | (g << 8);
        newImage.setRGB(l, k, pixels);
      }
    }
    try {
      ImageIO.write(newImage, "jpg", savedImage); // JPG or JPEG are the same format.
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  /**
   * Loads an image from the given file path in JPEG format
   * (both .jpg and .jpeg extensions are supported).
   *
   * @param path  The path to the JPEG image file.
   * @param name  A name associated with the image.
   * @return      A 2D array of RGB pixels representing the loaded image.
   * @throws Exception  If an error occurs during loading.
   */
  @Override
  public RGB[][] load(String path, String name) throws Exception {
    File fileToLoad = new File(path);
    if (!fileToLoad.exists()) {
      throw new FileNotFoundException("File is not present in the Directory.");
    }

    RGB[][] loadingJPEGImage;
    int w;
    int h;
    if ((path.toLowerCase().endsWith(".jpeg")) || (path.toLowerCase().endsWith(".jpg"))) {
      try {
        BufferedImage img = ImageIO.read(new File(path));
        w = img.getWidth();
        h = img.getHeight();
        loadingJPEGImage = new RGB[h][w];
        for (int k = 0; k < h; k++) {
          for (int l = 0; l < w; l++) {
            int pixels = img.getRGB(l, k);
            int r = 0xFF & (pixels >> 16);
            int b = 0xFF & pixels;
            int g = 0xFF & (pixels >> 8);

            loadingJPEGImage[k][l] = new RGB(r, g, b);
          }
        }
      } catch (Exception e) {
        throw new Exception(e);
      }
    } else {
      throw new IllegalArgumentException("Input Only JPEG/JPG files.");
    }

    return loadingJPEGImage;
  }
}
