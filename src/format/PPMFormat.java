package format;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ImageController.ImageFormatController;
import ImageModel.RGB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;




public class PPMFormat implements ImageFormatController {

  @Override
  public RGB[][] load(String filePath, String storeFileName) throws FileNotFoundException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new FileNotFoundException("File not found!");
    }
    if (!filePath.toLowerCase().endsWith(".ppm")) {
      throw new IllegalArgumentException("Not a valid file. Only PNG, PPM, BMP, " +
              "JPEG files are supported");
    }
    RGB[][] pixelData = readPPM(filePath);
    return pixelData;
  }

  public static RGB[][] readPPM(String filename) {
    Scanner sc;
    List<RGB[][]> rgbValues = new ArrayList<>();
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println(filename);
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.length() > 0 && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new RuntimeException("Invalid PPM file: plain RAW file should begin with P3");
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

  /**
   * A method to save an image into PPM format.
   *
   * @param filePath the path where the file should be saved.
   */
  @Override
  public void save(String filePath, RGB[][] image) {
    try {
      //      Pixel[][] image = storage.fetchImage(searchImageKey);
      PrintWriter writer = new PrintWriter(new FileOutputStream(filePath));
      // Write ppm header information
      writer.println("P3");
      writer.println(image[0].length + " " + image.length);
      writer.println("255");

      // Write pixel data
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          writer.println(image[i][j].getPixel(0));
          writer.println(image[i][j].getPixel(1));
          writer.println(image[i][j].getPixel(2));
        }
        writer.println();
      }

      writer.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
  }


}