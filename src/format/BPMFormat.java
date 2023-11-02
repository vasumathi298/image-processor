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
  public void save(String filePath, RGB[][] saveThisImage) throws Exception {
    int w,h;
    File savedImage = new File(filePath);
    w= saveThisImage[0].length;
    h= saveThisImage.length;
    BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    for (int k = 0; k < w; k++) {
      for (int l = 0; l< h; l++) {
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

  @Override
  public RGB[][] load(String path, String name) throws Exception {
    File fileToLoad = new File(path);
    if (!path.toLowerCase().endsWith(".bmp")) {
      throw new IllegalArgumentException("Input Only BPM files.");
    }
    if (!fileToLoad.exists()) {
      throw new FileNotFoundException("File is not present in the Directory.");
    }

    RGB[][] loadingBMPImage;
    int w, h;
    try {
      BufferedImage readImage = ImageIO.read(new File(path));
      w= readImage.getWidth();
      h= readImage.getHeight();
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
