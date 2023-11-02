package imageformat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import imagecontroller.ImageFormatController;
import imagemodel.RGB;

/**
 * The PNGFormat class implements the ImageFormatController interface
 * for saving and loading images in PNG format.
 */
public class PNGFormat implements ImageFormatController {

  /**
   * Saves the specified image data in PNG format to the given file path.
   *
   * @param path          The path where the image should be saved.
   * @param saveThisImage The RGB pixel data to be saved.
   * @throws Exception    If an error occurs during saving.
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
        int argb = (r << 16) | (g << 8) | b;
        newImage.setRGB(l, k, argb);
      }
    }
    try {
      ImageIO.write(newImage, "png", savedImage);
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  /**
   * Loads an image from the given file path in PNG format.
   *
   * @param path          The path to the PNG image file.
   * @param name          A name associated with the image.
   * @return              A 2D array of RGB pixels representing the loaded image.
   * @throws Exception    If an error occurs during loading.
   */
  @Override
  public RGB[][] load(String path, String name) throws Exception {
    File fileToLoad = new File(path);
    if (!path.toLowerCase().endsWith(".png")) {
      throw new IllegalArgumentException("Input Only PNG files");
    }

    if (!fileToLoad.exists()) {
      throw new FileNotFoundException("File is not present in the Directory.");
    }

    RGB[][] loadingPNGImage;
    int w;
    int h;
    try {
      BufferedImage img = ImageIO.read(new File(path));
      w = img.getWidth();
      h = img.getHeight();
      loadingPNGImage = new RGB[h][w];
      for (int k = 0; k < h; k++) {
        for (int l = 0; l < w; l++) {
          int pixels = img.getRGB(l, k);
          int r = 0xFF & (pixels >> 16);
          int g = 0xFF & (pixels >> 8);
          int b = 0xFF & pixels;
          loadingPNGImage[k][l] = new RGB(r, g, b);
        }
      }
    } catch (Exception e) {
      throw new Exception(e);
    }
    return loadingPNGImage;
  }
}
