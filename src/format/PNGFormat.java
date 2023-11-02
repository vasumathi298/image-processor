package format;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import ImageController.ImageFormatController;
import ImageModel.RGB;


/**
 * This is PNG class.
 */

public class PNGFormat implements ImageFormatController {



  @Override
  public RGB[][] load(String filePath, String storeFileName) throws FileNotFoundException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new FileNotFoundException("File not found!");
    }
    if (!filePath.toLowerCase().endsWith(".png")) {
      throw new IllegalArgumentException("Not a valid file. Only PNG, PPM, BMP, " +
              "JPEG files are supported");
    }
    RGB[][] image;
    try {
      BufferedImage img = ImageIO.read(new File(filePath));
      int width = img.getWidth();
      int height = img.getHeight();
      image = new RGB[height][width];
      //int maxPixelValue = (1 << img.getColorModel().getPixelSize()) - 1;
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int rgb = img.getRGB(j, i);
          //          int alpha = (rgb >> 24) & 0xff;
          int r = (rgb >> 16) & 0xFF;
          int g = (rgb >> 8) & 0xFF;
          int b = rgb & 0xFF;
          image[i][j] = new RGB(r, g, b);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return image;
  }

  /**
   * A method to save an image into a particular format.
   *
   * @param filePath the path where the file should be saved.
   * @param image    the key of the image in Hashmap to save.
   */
  @Override
  public void save(String filePath, RGB[][] image) {
    int width = image[0].length;
    int height = image.length;
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = image[y][x].getPixel(0);
        int green = image[y][x].getPixel(1);
        int blue = image[y][x].getPixel(2);
        int argb = (red << 16) | (green << 8) | blue;
        newImage.setRGB(x, y, argb);
      }
    }
    File output = new File(filePath);
    try {
      ImageIO.write(newImage, "png", output);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}