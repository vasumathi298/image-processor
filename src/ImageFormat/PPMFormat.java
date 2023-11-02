package ImageFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ImageController.ImageFormatController;
import ImageModel.RGB;


import java.io.FileOutputStream;
import java.io.PrintWriter;




public class PPMFormat implements ImageFormatController {

  @Override
  public void save(String path, RGB[][] saveThisImage) {
    try {
      PrintWriter imageWriter = new PrintWriter(new FileOutputStream(path));
      imageWriter.println("P3");
      imageWriter.println(saveThisImage[0].length + " " + saveThisImage.length);
      imageWriter.println("255");

      int w,h;
      w=saveThisImage.length;
      h= saveThisImage[0].length;
      for (int k = 0; k < w; k++) {
        for (int l = 0;  l < h ; l ++) {
          imageWriter.println(saveThisImage[k][l].getPixel(0));
          imageWriter.println(saveThisImage[k][l].getPixel(1));
          imageWriter.println(saveThisImage[k][l].getPixel(2));
        }
        imageWriter.println();
      }
      imageWriter.close();

    } catch (FileNotFoundException e) {
      System.out.println("File is not present in the Directory.");
    }
  }
  @Override
  public RGB[][] load(String path, String name) throws FileNotFoundException {
    File file = new File(path);
    RGB[][] loadedImage;
    if (!path.toLowerCase().endsWith(".ppm")) {
      throw new IllegalArgumentException("Input only PPM files");
    }
    if (!file.exists()) {
      throw new FileNotFoundException("File is not present in the Directory.");
    }

    loadedImage= readPPM(path);
    return loadedImage;
  }

  public static RGB[][] readPPM(String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println(filename);
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.length() > 0 && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new RuntimeException("Invalid PPM file");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    RGB[][] pixels = new RGB[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        if (r < 0 || g < 0 || b < 0) {
          throw new IllegalArgumentException("r,g,b cannot be negative in PPM files");
        }

        pixels[i][j] = new RGB(r, g, b);
      }
    }
    return pixels;
  }



}