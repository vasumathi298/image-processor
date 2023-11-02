package format;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import ImageController.ImageFormatController;
import ImageModel.RGB;


/**
 * This is JPEG class.
 */
public class JPEGFormat implements ImageFormatController {


  @Override
  public RGB[][] load(String filePath, String storeFileName) throws FileNotFoundException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new FileNotFoundException("File not found!");
    }

    RGB[][] image;
    if ((filePath.toLowerCase().endsWith(".jpeg")) || (filePath.toLowerCase().endsWith(".jpg"))) {
      try {
        BufferedImage img = ImageIO.read(new File(filePath));
        int width = img.getWidth();
        int height = img.getHeight();
        //int maxPixelValue = (1 << img.getColorModel().getPixelSize()) - 1;
        image = new RGB[height][width];
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            int rgb = img.getRGB(j, i);
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;

            image[i][j] = new RGB(r, g, b);
          }
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new IllegalArgumentException("Not a valid file. Only JPEG or JPG files are supported");
    }

    return image;
  }


  @Override
  public void save(String filePath, RGB[][] image) {
    int width = image[0].length;
    int height = image.length;
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image[i][j].getPixel(0);
        int green = image[i][j].getPixel(1);
        int blue = image[i][j].getPixel(2);
        int rgb = (red << 16) | (green << 8) | blue;
        newImage.setRGB(j, i, rgb);
      }
    }
    File output = new File(filePath);
    try {
      ImageIO.write(newImage, "jpg", output); //JPG or JPEG both are the same format.
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}