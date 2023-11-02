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
  public void save(String path, RGB[][] saveThisImage) throws Exception {
    int w,h;
    File savedImage = new File(path);
    w= saveThisImage[0].length;
    h = saveThisImage.length;
    BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    for (int k = 0; k < h; k++) {
      for (int l = 0; l < w; l++) {
        int r = saveThisImage[k][l].getPixel(0);
        int g = saveThisImage[k][l].getPixel(1);
        int b = saveThisImage[k][l].getPixel(2);
        int pixels = (r << 16) | b  | (g << 8);
        newImage.setRGB(l, k, pixels);
      }
    }
    try {
      ImageIO.write(newImage, "jpg", savedImage); //JPG or JPEG both are the same format.
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  @Override
  public RGB[][] load(String path, String name) throws Exception {
    File fileToLoad = new File(path);
    if (!fileToLoad.exists()) {
      throw new FileNotFoundException("File is not present in the Directory.");
    }

    RGB[][] loadingJPEGImage;
    int w, h;
    if ((path.toLowerCase().endsWith(".jpeg")) || (path.toLowerCase().endsWith(".jpg"))) {
      try {
        BufferedImage img = ImageIO.read(new File(path));
        w = img.getWidth();
        h = img.getHeight();
        //int maxPixelValue = (1 << img.getColorModel().getPixelSize()) - 1;
        loadingJPEGImage = new RGB[h][w];
        for (int k = 0; k < h; k++) {
          for (int l = 0; l < w; l++) {
            int pixels = img.getRGB(l, k);
            int r =  0xFF & (pixels >> 16);
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