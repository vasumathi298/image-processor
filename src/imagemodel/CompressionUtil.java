package imagemodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Compression utility functions.
 */
public class CompressionUtil {
  private static final double THRESHOLD_EPSILON = 0.0000001;
  private final int rows;
  private final int cols;
  private final ArrayList<double[][]> imageTransform = new ArrayList<>();
  private double compressionThreshold;

  /**
   * This is a constructor of ImageCompressionFunction class.
   */
  public CompressionUtil(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }


  /**
   * Send har 2D transformed image.
   * @param originalImage original name.
   * @return 2d double array of har 2D transformed image.
   */
  public static double[][] har2DTransformation(double[][] originalImage) {
    int imageWidth = originalImage.length;
    int imageHeight = originalImage[0].length;
    double[][] har2DTranformedArray = new double[imageWidth][imageHeight];
    for (int k = 0; k < imageWidth; k++) {
      har2DTranformedArray[k] = har1DTransformRow(originalImage[k]);
    }

    for (int k = 0; k < imageHeight; k++) {
      double[] aColumn = new double[imageWidth];
      for (int l = 0; l < imageWidth; l++) {
        aColumn[l] = har2DTranformedArray[l][k];
      }
      aColumn = har1DTransformRow(aColumn);
      for (int m = 0; m < imageWidth; m++) {
        har2DTranformedArray[m][k] = aColumn[m];
      }
    }

    return har2DTranformedArray;
  }

  /**
   * This method is used to perform an inverse of haar transform on a 2D image.
   */
  public static double[][] har2DInverseTransform(double[][] originalImage) {
    int imageWidth = originalImage.length;
    int imageHeight = originalImage[0].length;

    double[][] har2DInverseTransformedArray = new double[imageWidth][imageHeight];
    for (int k = 0; k < imageHeight; k++) {
      double[] aColumn = new double[imageWidth];
      for (int l = 0; l < imageWidth; l++) {
        aColumn[l] = originalImage[l][k];
      }
      aColumn = har1DInverseTransformRow(aColumn);
      for (int m = 0; m < imageWidth; m++) {
        har2DInverseTransformedArray[m][k] = aColumn[m];
      }
    }
    for (int o = 0; o < imageWidth; o++) {
      har2DInverseTransformedArray[o] = har1DInverseTransformRow(har2DInverseTransformedArray[o]);
    }
    return har2DInverseTransformedArray;
  }

  /**
   * This method is used to transform a 1D array by parsing it by length.
   */
  public static double[] har1DTransform(double[] sArray) {
    double[] finalArray = Arrays.copyOf(sArray, sArray.length);
    for (int k = 0; k < sArray.length; k += 2) {
      double a = (sArray[k] + sArray[k + 1]) / Math.sqrt(2);
      double d = (sArray[k] - sArray[k + 1]) / Math.sqrt(2);
      finalArray[k / 2] = a;
      finalArray[k / 2 + sArray.length / 2] = d;
    }
    return finalArray;
  }

  /**
   * This method is used to perform a haar transform on the rows of the array.
   */
  public static double[] har1DTransformRow(double[] sArray) {
    int n = sArray.length;
    double[] har1DTransformedArray = Arrays.copyOf(sArray, sArray.length);
    while (n > 1) {
      double[] leftSArray = har1DTransform(Arrays.copyOfRange(har1DTransformedArray, 0, n));
      double[] rightSArray = Arrays.copyOfRange(har1DTransformedArray,
              n, har1DTransformedArray.length);
      System.arraycopy(leftSArray, 0, har1DTransformedArray, 0, leftSArray.length);
      System.arraycopy(rightSArray, 0, har1DTransformedArray,
              leftSArray.length, rightSArray.length);
      n = n / 2;
    }
    return har1DTransformedArray;
  }

  /**
   * This method is used to perform an inverse of the haar transform on the rows of the array.
   */
  public static double[] har1DInverseTransformRow(double[] sArray) {
    int n = 1;
    double[] har1DInverseTransformedArray = Arrays.copyOf(sArray, sArray.length);
    while (n < sArray.length) {
      double[] leftArray = Arrays.copyOfRange(har1DInverseTransformedArray, 0, n);
      double[] secondHalf = Arrays.copyOfRange(har1DInverseTransformedArray, n, 2 * n);

      for (int k = 0; k < n; k++) {
        double aArray = leftArray[k];
        double dArray = secondHalf[k];
        har1DInverseTransformedArray[k * 2] = (aArray + dArray) / Math.sqrt(2);
        har1DInverseTransformedArray[k * 2 + 1] = (aArray - dArray) / Math.sqrt(2);
      }
      n *= 2;
    }
    return har1DInverseTransformedArray;
  }


  void retrieveThresholdValue(double thresholdPercentage) {
    ArrayList<Double> thresholdValues = new ArrayList<>();
    for (double[][] transformedImage : imageTransform) {
      for (int k = 0; k < transformedImage.length; k++) {
        for (int l = 0; l < transformedImage[k].length; l++) {
          if (!checkPrecision(thresholdValues, Math.abs(transformedImage[k][l]))
                  && Math.abs(transformedImage[k][l]) >= 0.001) {
            thresholdValues.add(Math.abs(transformedImage[k][l]));
          }
        }
      }
    }
    Collections.sort(thresholdValues);
    int thresholdIndex = thresholdPercentage == 100.0 ? thresholdValues.size() - 1 :
            (int) ((thresholdPercentage / 100) * (thresholdValues.size()));
    compressionThreshold = thresholdValues.get(thresholdIndex);
  }

  /**
   * This method checks the precision of the values.
   */
  private boolean checkPrecision(List<Double> list, double value) {
    for (double listItem : list) {
      if (Math.abs(listItem - value) < THRESHOLD_EPSILON) {
        return true;
      }
    }
    return false;
  }


  void applyAfterThreshold() {
    for (double[][] transformedImage : imageTransform) {
      for (int k = 0; k < transformedImage.length; k++) {
        for (int l = 0; l < transformedImage[k].length; l++) {
          if (Math.abs(transformedImage[k][l]) <= compressionThreshold) {
            transformedImage[k][l] = 0.0;
          }
        }
      }
    }
  }

  /**
   * Unpad the inverse.
   * @return returns array of RGB.
   */

  ArrayList<RGB[][]> unpadInverse() {
    ArrayList<RGB[][]> unpaddedInversedImage = new ArrayList<>();
    ArrayList<double[][]> unpassedInversedDoubleArray = new ArrayList<>();
    for (double[][] transformedImage : imageTransform) {
      double[][] invertedImage = har2DInverseTransform(transformedImage);
      double[][] unpaddedImage = getUnpaddedImage(invertedImage, rows, cols);
      unpassedInversedDoubleArray.add(unpaddedImage);
    }
    unpaddedInversedImage.add(convertDoubleArrayToRGB(unpassedInversedDoubleArray.get(0),
            "red"));
    unpaddedInversedImage.add(convertDoubleArrayToRGB(unpassedInversedDoubleArray.get(1),
            "green"));
    unpaddedInversedImage.add(convertDoubleArrayToRGB(unpassedInversedDoubleArray.get(2),
            "blue"));
    return unpaddedInversedImage;
  }

  /**
   * Compress the image using threshold.
   * @param originalImage RGB Pixels.
   */

  public void compressionThresholding(RGB[][] originalImage) {
    double[][] doubleArrayedImage = convertRGBTODoubleArray(originalImage);
    double[][] paddedImage = getPaddedImage(doubleArrayedImage);
    double[][] transformedImage = har2DTransformation(paddedImage);
    imageTransform.add(transformedImage);
  }

  /**
   * Padding the image using zeros.
   * @param originalImage original Image to be paded.
   * @return return double array.
   */

  public double[][] getPaddedImage(double[][] originalImage) {
    int imageWidth = originalImage.length;
    int imageHeight = originalImage[0].length;
    int dimension = Math.max(imageWidth, imageHeight);
    int dimensionToPad = 1;
    while (dimensionToPad < dimension) {
      dimensionToPad *= 2;
    }
    double[][] paddedImage = new double[dimensionToPad][dimensionToPad];

    for (int k = 0; k < dimensionToPad; k++) {
      for (int l = 0; l < dimensionToPad; l++) {
        if (k < imageWidth && l < imageHeight) {
          paddedImage[k][l] = originalImage[k][l];
        } else {
          paddedImage[k][l] = 0;
        }
      }
    }
    return paddedImage;
  }

  /**
   * Get the unpadded image.
   * @param originalImage original image.
   * @param originalRows image width.
   * @param originalCols image height.
   * @return double array.
   */
  public double[][] getUnpaddedImage(double[][] originalImage,
                                     int originalRows, int originalCols) {
    double[][] unpaddedImage = new double[originalRows][originalCols];
    int rows = originalImage.length;
    int cols = originalImage[0].length;

    for (int k = 0; k < originalRows; k++) {
      for (int l = 0; l < originalCols; l++) {
        if (k < rows && l < cols) {
          unpaddedImage[k][l] = originalImage[k][l];
        }
      }
    }
    return unpaddedImage;
  }

  /**
   * convert RGB To double array.
   * @param originalImage source image.
   * @return double array.
   */

  public double[][] convertRGBTODoubleArray(RGB[][] originalImage) {
    int rows = originalImage.length;
    int cols = originalImage[0].length;
    double[][] doubleArrayFromRGB = new double[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        doubleArrayFromRGB[i][j] = (originalImage[i][j].getPixel(0) +
                originalImage[i][j].getPixel(1) + originalImage[i][j].getPixel(2));
      }
    }
    return doubleArrayFromRGB;
  }


  /**
   * Double array to RGB.
   * @param doubleArray double array.
   * @param channel channel R/G/B.
   * @return RGB pixels.
   */
  public RGB[][] convertDoubleArrayToRGB(double[][] doubleArray, String channel) {
    RGB rgb = null;
    int imageWidth = doubleArray.length;
    int imageHeight = doubleArray[0].length;
    RGB[][] rgbImage = new RGB[imageWidth][imageHeight];
    for (int k = 0; k < imageWidth; k++) {
      for (int l = 0; l < imageHeight; l++) {
        if (Objects.equals(channel, "red")) {
          rgb = new RGB((int) Math.round(doubleArray[k][l]), 0, 0);
        } else if (Objects.equals(channel, "green")) {
          rgb = new RGB(0, (int) Math.round(doubleArray[k][l]), 0);
        } else if (Objects.equals(channel, "blue")) {
          rgb = new RGB(0, 0, (int) Math.round(doubleArray[k][l]));
        }
        rgbImage[k][l] = rgb;
      }
    }
    return  rgbImage;
  }

}
