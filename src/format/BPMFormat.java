package format;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import ImageController.ImageFormatController;
import ImageModel.RGB;

/**
 * This is BMP class.
 */
public class BPMFormat implements ImageFormatController {


  @Override
  public RGB[][] load(String filePath, String storeFileName) throws FileNotFoundException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new FileNotFoundException("File not found!");
    }
    if (!filePath.toLowerCase().endsWith(".bmp")) {
      throw new IllegalArgumentException("Not a valid file. Only BMP files are supported");
    }
    RGB[][] newImage;
    try {
      BufferedImage image = ImageIO.read(new File(filePath));
      int width = image.getWidth();
      int height = image.getHeight();
      newImage = new RGB[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int rgb = image.getRGB(j, i);

          int red = (rgb >> 16) & 0xFF;
          int green = (rgb >> 8) & 0xFF;
          int blue = rgb & 0xFF;

          newImage[i][j] = new RGB(red, green, blue);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return newImage;
  }


  @Override
  public void save(String filePath, RGB[][] image) {
    int width = image[0].length;
    int height = image.length;
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = image[y][x].getPixel(0);
        int green = image[y][x].getPixel(1);
        int blue = image[y][x].getPixel(2);
        int rgb = (red << 16) | (green << 8) | blue;
        img.setRGB(x, y, rgb);
      }
    }
    File output = new File(filePath);
    try {
      ImageIO.write(img, "bmp", output);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
