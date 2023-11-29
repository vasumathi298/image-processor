package imagemodel;


import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

import javax.imageio.ImageIO;


/**
 * The `ImageProcessingModelImpl` class provides implementations
 * for various image processing operations.
 * It loads, transforms, and saves images while
 * utilizing an `RGBImageStorage` to manage image data.
 */
public class ImageProcessingModelImpl implements
        ImageProcessingModel, EnhancedImageProcessingModel {

  private final RGBImageStorage rgbImageStore;
  private int imageWidth;
  private int imageHeight;


  /**
   * Constructs an `ImageProcessingModelImpl` and initializes an
   * `RGBImageStorage` instance for managing images.
   */
  public ImageProcessingModelImpl() {
    this.rgbImageStore = new RGBImageStorage();
  }

  @Override
  public void redComponent(String fileName, String destImage) {

    RGB[][] retrieveImage = rgbImageStore.retrieveImage(fileName);
    RGB[][] redComp = buildRedPixelImage(retrieveImage);
    this.rgbImageStore.storeImage(destImage, redComp);
  }

  @Override
  public void greenComponent(String fileName, String destImage) {
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(fileName);
    RGB[][] redComp = buildGreenPixelImage(retrieveImage);
    this.rgbImageStore.storeImage(destImage, redComp);
  }

  @Override
  public void blueComponent(String fileName, String destImage) {
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(fileName);
    RGB[][] redComp = buildBluePixelImage(retrieveImage);
    this.rgbImageStore.storeImage(destImage, redComp);
  }

  @Override
  public void imageLoader(RGB[][] image, String storeFileName) throws FileNotFoundException {
    this.rgbImageStore.storeImage(storeFileName, image);
  }

  private int getRGBPixelValue(RGB pixel) {
    if (pixel.getPixel(0) >= pixel.getPixel(1) &&
            pixel.getPixel(0) >= pixel.getPixel(2)) {
      return pixel.getPixel(0);
    } else if (pixel.getPixel(1) >= pixel.getPixel(0) &&
            pixel.getPixel(1) >= pixel.getPixel(2)) {
      return pixel.getPixel(1);
    } else {
      return pixel.getPixel(2);
    }
  }


  private RGB[][] buildRedPixelImage(RGB[][] orgImage) {
    imageWidth = orgImage[0].length;
    imageHeight = orgImage.length;
    int rComponent = 0;
    RGB[][] redImage = null;
    redImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        rComponent = orgImage[k][l].getPixel(0);
        redImage[k][l] = new RGB(rComponent, 0, 0);
      }
    }
    return redImage;
  }


  private RGB[][] buildGreenPixelImage(RGB[][] orgImage) {
    imageWidth = orgImage[0].length;
    imageHeight = orgImage.length;
    int gComponent = 0;
    RGB[][] greenImage = null;
    greenImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        gComponent = orgImage[k][l].getPixel(1);
        greenImage[k][l] = new RGB(0, gComponent, 0);
      }
    }
    return greenImage;
  }


  private RGB[][] buildBluePixelImage(RGB[][] orgImage) {
    imageWidth = orgImage[0].length;
    imageHeight = orgImage.length;
    int bComponent = 0;
    RGB[][] blueImage = null;
    blueImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        bComponent = orgImage[k][l].getPixel(2);
        blueImage[k][l] = new RGB(0, 0, bComponent);
      }
    }
    return blueImage;
  }


  @Override
  public void constructRedGreyScale(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrivedImage = null;
    retrivedImage = rgbImageStore.retrieveImage(imageFind);
    RGB[][] redScaledGrayImage = null;
    redScaledGrayImage = constructRedGreyScaleImage(retrivedImage);
    this.rgbImageStore.storeImage(storeFind, redScaledGrayImage);
  }


  @Override
  public void revertImage(String imageFind, String revertImageName) {
    RGB[][] retrivedImage = null;
    retrivedImage = rgbImageStore.retrieveImage(imageFind);
    this.rgbImageStore.storeImage(revertImageName, retrivedImage);
  }

  private RGB[][] constructRedGreyScaleImage(RGB[][] orgImage) {
    imageWidth = orgImage[0].length;
    imageHeight = orgImage.length;
    RGB[][] redComp = buildRedPixelImage(orgImage);
    RGB[][] redGrayScaleImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        redGrayScaleImage[k][l] = new RGB(getRGBPixelValue(redComp[k][l]),
                getRGBPixelValue(redComp[k][l]),
                getRGBPixelValue(redComp[k][l]));
      }
    }
    return redGrayScaleImage;
  }

  @Override
  public void constructGreenGreyScale(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrivedImage = null;
    retrivedImage = rgbImageStore.retrieveImage(imageFind);
    RGB[][] greenScaledGrayImage = null;
    greenScaledGrayImage = constructGreenGreyScaleImage(retrivedImage);
    this.rgbImageStore.storeImage(storeFind, greenScaledGrayImage);
  }


  private RGB[][] constructGreenGreyScaleImage(RGB[][] orgImage) {
    imageWidth = orgImage[0].length;
    imageHeight = orgImage.length;
    RGB[][] greenComp = buildGreenPixelImage(orgImage);
    RGB[][] greenGrayScaleImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        greenGrayScaleImage[k][l] = new RGB(getRGBPixelValue(greenComp[k][l]),
                getRGBPixelValue(greenComp[k][l]),
                getRGBPixelValue(greenComp[k][l]));
      }
    }
    return greenGrayScaleImage;
  }


  @Override
  public void constructBlueGreyScale(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrivedImage = null;
    retrivedImage = rgbImageStore.retrieveImage(imageFind);
    RGB[][] blueScaledGrayImage = constructBlueGreyScaleImage(retrivedImage);
    this.rgbImageStore.storeImage(storeFind, blueScaledGrayImage);
  }


  private RGB[][] constructBlueGreyScaleImage(RGB[][] orgImage) {
    imageWidth = orgImage[0].length;
    imageHeight = orgImage.length;
    RGB[][] greenComp = null;
    greenComp = buildBluePixelImage(orgImage);
    RGB[][] blueGrayScaleImage = null;
    blueGrayScaleImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        blueGrayScaleImage[k][l] = new RGB(getRGBPixelValue(greenComp[k][l]),
                getRGBPixelValue(greenComp[k][l]),
                getRGBPixelValue(greenComp[k][l]));
      }
    }
    return blueGrayScaleImage;
  }


  @Override
  public void greyScaleValue(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    imageWidth = retrieveImage[0].length;
    imageHeight = retrieveImage.length;
    RGB[][] valueGrayImage = null;
    valueGrayImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        valueGrayImage[k][l] = new RGB(getRGBPixelValue(retrieveImage[k][l]),
                getRGBPixelValue(retrieveImage[k][l]),
                getRGBPixelValue(retrieveImage[k][l]));
      }
    }
    this.rgbImageStore.storeImage(storeFind, valueGrayImage);
  }

  private int findWeightedPixelSum(RGB rgbPixels) {
    return (int) (0.2126 * rgbPixels.getPixel(0)
            + 0.7152 * rgbPixels.getPixel(1)
            + 0.0722 * rgbPixels.getPixel(2));
  }


  @Override
  public void greyScaleLuma(String imageFind, String storeFind, double splitPercentage) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    int imageHeight = retrieveImage.length;
    int imageWidth = retrieveImage[0].length;
    RGB[][] lumaGrayImage = new RGB[imageHeight][imageWidth];

    int splitPosition = (int) (imageWidth * (splitPercentage / 100.0));

    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        // Check if the current pixel is on the left side of the image
        if (l < splitPosition) {
          int lumaValue = findWeightedPixelSum(retrieveImage[k][l]);
          lumaGrayImage[k][l] = new RGB(lumaValue, lumaValue, lumaValue);
        } else {
          // If the pixel is on the right side, copy the original pixel
          lumaGrayImage[k][l] = new RGB(retrieveImage[k][l].getPixel(0),
                  retrieveImage[k][l].getPixel(1), retrieveImage[k][l].getPixel(2));
        }
      }
    }

    this.rgbImageStore.storeImage(storeFind, lumaGrayImage);
  }


  private int getPixelAverage(RGB pixel) {
    return (pixel.getPixel(0) + pixel.getPixel(1) + pixel.getPixel(2)) / 3;
  }


  @Override
  public void greyScaleIntensity(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = null;
    retrieveImage = rgbImageStore.retrieveImage(imageFind);
    imageWidth = retrieveImage[0].length;
    imageHeight = retrieveImage.length;
    RGB[][] intensityGrayImage = null;
    intensityGrayImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        intensityGrayImage[k][l] = new RGB(getPixelAverage(retrieveImage[k][l]),
                getPixelAverage(retrieveImage[k][l]),
                getPixelAverage(retrieveImage[k][l]));
      }
    }
    this.rgbImageStore.storeImage(storeFind, intensityGrayImage);
  }

  @Override
  public void brightenOrDarkenImage(String imageFind, String storeFind, int value) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    int imageHeight = retrieveImage.length;
    int imageWidth = retrieveImage[0].length;
    RGB[][] bOrDImage = null;
    bOrDImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        bOrDImage[k][l] = retrieveImage[k][l];
        int redComp;
        int greenComp;
        int blueComp;
        if (value < 0) {
          redComp = Math.max((retrieveImage[k][l].getPixel(0) + value), 0);
          greenComp = Math.max((retrieveImage[k][l].getPixel(1) + value), 0);
          blueComp = Math.max((retrieveImage[k][l].getPixel(2) + value), 0);
        } else {
          redComp = Math.min(retrieveImage[k][l].getPixel(0) + value, 255);
          greenComp = Math.min(retrieveImage[k][l].getPixel(1) + value, 255);
          blueComp = Math.min(retrieveImage[k][l].getPixel(2) + value, 255);
        }

        bOrDImage[k][l].setPixel(0, redComp);
        bOrDImage[k][l].setPixel(2, blueComp);
        bOrDImage[k][l].setPixel(1, greenComp);
      }
    }

    this.rgbImageStore.storeImage(storeFind, bOrDImage);
  }

  @Override
  public void verticalImageFlip(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    int imageHeight = retrieveImage.length;
    int imageWidth = retrieveImage[0].length;
    RGB[][] vFlippedImg = null;
    vFlippedImg = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      System.arraycopy(retrieveImage[imageHeight - k - 1],
              0, vFlippedImg[k], 0, imageWidth);
    }
    this.rgbImageStore.storeImage(storeFind, vFlippedImg);
  }


  @Override
  public void horizontalImageFlip(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    int imageHeight = retrieveImage.length;
    int imageWidth = retrieveImage[0].length;
    RGB[][] hFlippedImg = null;
    hFlippedImg = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        hFlippedImg[k][l] = retrieveImage[k][imageWidth - l - 1];
      }
    }
    this.rgbImageStore.storeImage(storeFind, hFlippedImg);
  }


  private RGB generateSepiaTone(RGB inputPixel) {
    int sepiaRed = (int) ((0.393 * inputPixel.getPixel(0))
            + (0.769 * inputPixel.getPixel(1))
            + (0.189 * inputPixel.getPixel(2)));
    int sepiaGreen = (int) ((0.349 * inputPixel.getPixel(0))
            + (0.686 * inputPixel.getPixel(1))
            + (0.168 * inputPixel.getPixel(2)));
    int sepiaBlue = (int) ((0.272 * inputPixel.getPixel(0))
            + (0.534 * inputPixel.getPixel(1))
            + (0.131 * inputPixel.getPixel(2)));
    sepiaRed = Math.min(sepiaRed, 255);
    sepiaGreen = Math.min(sepiaGreen, 255);
    sepiaBlue = Math.min(sepiaBlue, 255);
    RGB sepiaPixel = new RGB(sepiaRed, sepiaGreen, sepiaBlue);
    return sepiaPixel;
  }


  @Override
  public void constructSepia(String imageFind, String storeFind, double splitPercentage) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    int imageHeight = retrieveImage.length;
    int imageWidth = retrieveImage[0].length;
    RGB[][] sepiaTonedImage = new RGB[imageHeight][imageWidth];

    int splitPosition = (int) (imageWidth * (splitPercentage / 100.0));

    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        // Apply sepia only to the left half of the image
        if (l < splitPosition) {
          sepiaTonedImage[k][l] = generateSepiaTone(retrieveImage[k][l]);
        } else {
          // If the pixel is on the right half, copy the original pixel
          sepiaTonedImage[k][l] = new RGB(retrieveImage[k][l].getPixel(0),
                  retrieveImage[k][l].getPixel(1), retrieveImage[k][l].getPixel(2));
        }
      }
    }

    this.rgbImageStore.storeImage(storeFind, sepiaTonedImage);
  }

  @Override
  public void blurImage(String imageFind, String storeFind, double splitPercentage) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    int imageHeight = retrieveImage.length;
    int imageWidth = retrieveImage[0].length;
    RGB[][] blurredImage = new RGB[imageHeight][imageWidth];
    double[] gaussianKernel = {
      1.0 / 16, 1.0 / 8,
      1.0 / 16, 1.0 / 8,
      1.0 / 4, 1.0 / 8,
      1.0 / 16, 1.0 / 8, 1.0 / 16};

    int splitPosition = (int) (imageWidth * (splitPercentage / 100.0));

    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        double redSumComp = 0;
        double greenSumComp = 0;
        double blueSumComp = 0;
        for (int x = -1; x <= 1; x++) {
          for (int y = -1; y <= 1; y++) {
            int newY = l + y;
            int newX = k + x;

            if (splitPercentage == 0
                    || (l < splitPosition && newX >= 0 && newX < imageHeight)) {
              if (newY >= 0 && newY < imageWidth && newX >= 0 && newX < imageHeight) {
                double weight = gaussianKernel[(x + 1) * 3 + (y + 1)];
                redSumComp += weight * retrieveImage[newX][newY].getPixel(0);
                greenSumComp += weight * retrieveImage[newX][newY].getPixel(1);
                blueSumComp += weight * retrieveImage[newX][newY].getPixel(2);
              }
            } else {
              redSumComp = retrieveImage[k][l].getPixel(0);
              greenSumComp = retrieveImage[k][l].getPixel(1);
              blueSumComp = retrieveImage[k][l].getPixel(2);
            }
          }
        }
        blurredImage[k][l] = new RGB((int) redSumComp, (int) greenSumComp, (int) blueSumComp);
      }
    }

    this.rgbImageStore.storeImage(storeFind, blurredImage);
  }

  @Override
  public void imageColorCorrection(String imageName, String destImage,
                                   double splitPercentage) throws IOException {
    RGB[][] imagePixel = rgbImageStore.retrieveImage(imageName);
    int imageHeight = imagePixel.length;
    int imageWidth = imagePixel[0].length;

    // Fetch the histogram
    int[][] imgHistogram = fetchHistogram(imageName, destImage);

    // Find meaningful peaks for each channel
    int[] redPeaks = findMeaningfulPeaks(imgHistogram[0]);
    int[] greenPeaks = findMeaningfulPeaks(imgHistogram[1]);
    int[] bluePeaks = findMeaningfulPeaks(imgHistogram[2]);

    // Compute the average peak value
    int averageRedPeak = redPeaks[1];
    int averageGreenPeak = greenPeaks[1];
    int averageBluePeak = bluePeaks[1];

    int avg = (averageRedPeak + averageBluePeak + averageGreenPeak) / 3;

    int splitPosition = (int) (imageWidth * (splitPercentage / 100.0));

    // Offset each channel's values to align with the average peak only for the left half
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        if (l < splitPosition) {
          imagePixel[k][l] = offsetValues(imagePixel[k][l], 0, redPeaks[1], avg);
          imagePixel[k][l] = offsetValues(imagePixel[k][l], 1, greenPeaks[1], avg);
          imagePixel[k][l] = offsetValues(imagePixel[k][l], 2, bluePeaks[1], avg);
        }
      }
    }

    // Update the RGB image store with the corrected image
    this.rgbImageStore.storeImage(destImage, imagePixel);
  }

  // Helper method to offset values for a specific channel
  private RGB offsetValues(RGB pixel, int channel, int peak, int avg) {
    int offset = avg - peak;
    int newValue = pixel.getPixel(channel) + offset;
    newValue = Math.min(Math.max(newValue, 0), 255); // Ensure the value stays within [0, 255]
    pixel.setPixel(channel, newValue);
    return pixel;
  }


  private int[] findMeaningfulPeaks(int[] channelHistogram) {
    int peakValue = 0;
    int peakPosition = 0;

    // Find the peak within the meaningful range (10 to 245)
    for (int i = 10; i < 245; i++) {
      if (channelHistogram[i] > peakValue) {
        peakValue = channelHistogram[i];
        peakPosition = i;
      }
    }

    return new int[]{peakValue, peakPosition};
  }



  @Override
  public void sharpenImage(String imageFind, String storeFind, double splitPercentage) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    int imageHeight = retrieveImage.length;
    int imageWidth = retrieveImage[0].length;
    RGB[][] sharpenedImage = new RGB[imageHeight][imageWidth];

    double[][] kernel = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };

    int splitPosition = (int) (imageWidth * (splitPercentage / 100.0));

    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        double redSumComp = 0.0;
        double greenSumComp = 0.0;
        double blueSumComp = 0.0;
        for (int x = -2; x <= 2; x++) {
          for (int y = -2; y <= 2; y++) {
            int row = k + x;
            int col = l + y;

            // Apply sharpening only to the left half of the image
            if (l < splitPosition) {
              if (row >= 0 && row < imageHeight && col >= 0 && col < imageWidth) {
                redSumComp += retrieveImage[row][col].getPixel(0) * kernel[x + 2][y + 2];
                greenSumComp += retrieveImage[row][col].getPixel(1) * kernel[x + 2][y + 2];
                blueSumComp += retrieveImage[row][col].getPixel(2) * kernel[x + 2][y + 2];
              }
            } else {
              // If the pixel is on the right half of the image, copy the original pixel
              redSumComp = retrieveImage[k][l].getPixel(0);
              greenSumComp = retrieveImage[k][l].getPixel(1);
              blueSumComp = retrieveImage[k][l].getPixel(2);
            }
          }
        }
        int nR = Math.min(Math.max((int) redSumComp, 0), 255);
        int nG = Math.min(Math.max((int) greenSumComp, 0), 255);
        int nB = Math.min(Math.max((int) blueSumComp, 0), 255);
        sharpenedImage[k][l] = new RGB(nR, nG, nB);
      }
    }
    this.rgbImageStore.storeImage(storeFind, sharpenedImage);
  }


  @Override
  public void imageSplitter(String imageFind, String[] storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] redGrayScaleImage;
    RGB[][] greenGrayScaleImage;
    RGB[][] blueGrayScaleImage;
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    redGrayScaleImage = constructRedGreyScaleImage(retrieveImage);
    this.rgbImageStore.storeImage(storeFind[2], redGrayScaleImage);

    greenGrayScaleImage = constructGreenGreyScaleImage(retrieveImage);
    this.rgbImageStore.storeImage(storeFind[3], greenGrayScaleImage);

    blueGrayScaleImage = constructBlueGreyScaleImage(retrieveImage);
    this.rgbImageStore.storeImage(storeFind[4], blueGrayScaleImage);
  }


  @Override
  public void imageMerger(String[] imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind[1]);
    findImgFromRGBStorage(imageFind[2]);
    findImgFromRGBStorage(imageFind[3]);
    RGB[][] mergedImg = null;
    RGB[][] retrivedRedGrayImg = rgbImageStore.retrieveImage(imageFind[1]);
    RGB[][] retrivedGreenGrayImg = rgbImageStore.retrieveImage(imageFind[2]);
    RGB[][] retrivedBlueGrayImg = rgbImageStore.retrieveImage(imageFind[3]);
    if (areGrayImagesCompatible(retrivedRedGrayImg, retrivedGreenGrayImg, retrivedBlueGrayImg)) {
      mergedImg = new RGB[retrivedRedGrayImg.length][retrivedRedGrayImg[0].length];
      for (int k = 0; k < retrivedRedGrayImg.length; k++) {
        for (int l = 0; l < retrivedRedGrayImg[0].length; l++) {
          mergedImg[k][l] = new RGB(retrivedRedGrayImg[k][l].getPixel(0),
                  retrivedGreenGrayImg[k][l].getPixel(1),
                  retrivedBlueGrayImg[k][l].getPixel(2));
        }
      }
      this.rgbImageStore.storeImage(storeFind, mergedImg);
    } else {
      System.out.println("3 Gray Scales are not present in the given Image");
    }
  }

  private boolean areGrayImagesCompatible(RGB[][] redGrayImg,
                                          RGB[][] greenGrayImg, RGB[][] blueGrayImg) {
    return redGrayImg.length == greenGrayImg.length
            && greenGrayImg.length == blueGrayImg.length
            && redGrayImg.length == blueGrayImg.length
            && redGrayImg[0].length == greenGrayImg[0].length
            && greenGrayImg[0].length == blueGrayImg[0].length
            && redGrayImg[0].length == blueGrayImg[0].length;
  }


  @Override
  public RGB[][] saveFile(String path, String imageFind) {
    return this.rgbImageStore.retrieveImage(imageFind);
  }


  @Override
  public RGB[][] retrieveImage(String searchImageKey) {
    findImgFromRGBStorage(searchImageKey);
    RGB[][] image = rgbImageStore.retrieveImage(searchImageKey);
    return image;
  }

  @Override
  public int[][] fetchHistogram(String imageName, String destImageName) throws IOException {
    int[][] imgHistogram = new int[4][256];
    RGB[][] imagePixel = rgbImageStore.retrieveImage(imageName);
    int imgHeight = imagePixel.length;
    int imgWidth = imagePixel[0].length;

    for (int k = 0; k < imgHeight; k++) {
      for (int l = 0; l < imgWidth; l++) {
        RGB rgb = imagePixel[k][l];
        int rComponent = rgb.getPixel(0);
        int gComponent = rgb.getPixel(1);
        int bComponent = rgb.getPixel(2);
        int iComponent = (rComponent + gComponent + bComponent) / 3;
        imgHistogram[0][rComponent]++;
        imgHistogram[1][gComponent]++;
        imgHistogram[2][bComponent]++;
        imgHistogram[3][iComponent]++;
      }
    }

    // Create a BufferedImage with a white background
    int width = 256;
    int height = 256;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = image.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, width, height);

    // Draw grid lines
    g2d.setColor(Color.GRAY);
    for (int i = 0; i < width; i += 32) {
      g2d.drawLine(i, 0, i, height);
      g2d.drawLine(0, i, width, i);
    }

    // Draw histograms for each channel
    drawHistogram(g2d, imgHistogram[0], Color.RED, height);
    drawHistogram(g2d, imgHistogram[1], Color.GREEN, height);
    drawHistogram(g2d, imgHistogram[2], Color.BLUE, height);

    // Save the image
    ImageIO.write(image, "png", new File(destImageName + ".png"));

    return imgHistogram;
  }

  private void drawHistogram(Graphics2D g2d, int[] channelHistogram, Color color, int height) {
    // Normalize the histogram values
    int maxCount = getMaxValue(channelHistogram);
    double scale = (double) height / maxCount;

    // Draw the histogram line graph
    g2d.setColor(color);
    for (int i = 0; i < 255; i++) {
      int x1 = i;
      int y1 = height - (int) (channelHistogram[i] * scale);
      int x2 = i + 1;
      int y2 = height - (int) (channelHistogram[i + 1] * scale);

      // Draw only if the histogram values are not zero
      if (channelHistogram[i] != 0 && channelHistogram[i + 1] != 0) {
        g2d.drawLine(x1, y1, x2, y2);
      }
    }
  }

  private int getMaxValue(int[] array) {
    int max = array[0];
    for (int value : array) {
      if (value > max) {
        max = value;
      }
    }
    return max;
  }

  @Override
  public void compressImage(String fileName, String destName, double threshold) {

    ArrayList<RGB[][]> splittedImages = getAllComponentImages(fileName);
    RGB[][] originalImage = rgbImageStore.retrieveImage(fileName);
    CompressionUtil imageCompression = new CompressionUtil(originalImage.length,
            originalImage[0].length);
    imageCompression.compressionThresholding(splittedImages.get(0));
    imageCompression.compressionThresholding(splittedImages.get(1));
    imageCompression.compressionThresholding(splittedImages.get(2));
    imageCompression.retrieveThresholdValue(threshold);
    imageCompression.applyAfterThreshold();
    ArrayList<RGB[][]> results = imageCompression.unpadInverse();
    RGB[][] result = combineAllComponents(results.get(0), results.get(1), results.get(2));
    this.rgbImageStore.storeImage(destName, result);

  }

  RGB[][] combineAllComponents(RGB[][] image1, RGB[][] image2, RGB[][] image3) {

    int referenceWidth = image1.length;
    int referenceHeight = image1[0].length;
    RGB[][] resultImagePixels = new RGB[referenceWidth][referenceHeight];
    int red;
    int blue;
    int green;
    for (int x = 0; x < referenceWidth; x++) {
      for (int y = 0; y < referenceHeight; y++) {
        red = image1[x][y].getPixel(0) + image2[x][y].getPixel(0)
                + image3[x][y].getPixel(0);
        green = image1[x][y].getPixel(1) + image2[x][y].getPixel(1)
                + image3[x][y].getPixel(1);
        blue = image1[x][y].getPixel(2) + image2[x][y].getPixel(2)
                + image3[x][y].getPixel(2);
        resultImagePixels[x][y] = new RGB(red, green, blue);
      }
    }
    return resultImagePixels;
  }

  private ArrayList<RGB[][]> getAllComponentImages(String imageName) {
    ArrayList<RGB[][]> images = new ArrayList<>();
    RGB[][] image1 = applyOperationToAllPixels(pixel -> new RGB(pixel.getPixel(0), 0,
            0), imageName);
    RGB[][] image2 = applyOperationToAllPixels(pixel -> new RGB(0, pixel.getPixel(1),
            0), imageName);
    RGB[][] image3 = applyOperationToAllPixels(pixel -> new RGB(0, 0,
            pixel.getPixel(2)), imageName);
    images.add(image1);
    images.add(image2);
    images.add(image3);
    return images;
  }


  private RGB[][] applyOperationToAllPixels(Function<RGB, RGB> operation, String imageName) {
    RGB[][] image = rgbImageStore.retrieveImage(imageName);
    if (image == null) {
      throw new IllegalStateException("Image Not found or name entered in wrong syntax");
    }
    RGB[][] resultImagePixels = new RGB[image.length][image[0].length];
    for (int x = 0; x < image.length; x++) {
      for (int y = 0; y < image[0].length; y++) {
        resultImagePixels[x][y] = operation.apply(image[x][y]);
      }
    }
    return resultImagePixels;
  }


  private static double[] getAllValues(RGB[][] channels) {
    int height = channels.length;
    int width = channels[0].length;

    double[] values = new double[3 * height * width];
    int index = 0;

    for (int c = 0; c < 3; c++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          values[index++] = channels[i][j].getPixel(c);
        }
      }
    }

    return values;
  }


  private static RGB[][] thresholdImage(RGB[][] image, double thresholdPercentage) {
    double[] allValues = getAllValues(image);

    Arrays.sort(allValues);

    System.out.println(thresholdPercentage);

    int thresholdIndex = (int) (allValues.length * thresholdPercentage);

    thresholdIndex = Math.min(thresholdIndex, allValues.length - 1);

    double thresholdValue = allValues[thresholdIndex];
    for (int c = 0; c < 3; c++) {
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          if (Math.abs(image[i][j].getPixel(c)) >= thresholdValue) {
            image[i][j].setPixel(c, image[i][j].getPixel(c));
          } else {
            image[i][j].setPixel(c, 0);
          }
        }
      }
    }

    return image;
  }

  static void padZerosIfNeeded(RGB[] s, int c) {
    int originalSize = s.length;
    int newSize = Integer.highestOneBit(originalSize - 1) << 1;

    for (int i = originalSize; i < newSize; i++) {
      s[i] = new RGB();
      s[i].setPixel(c, (int) 0.0);
    }
  }


  private static RGB[][] transformImage(RGB[][] image) {
    int width = image.length;
    int height = image[0].length;

    for (int c = 0; c < 3; c++) {
      for (int size = Math.max(width, height); size > 1; size /= 2) {
        for (int i = 0; i < size; i++) {
          padZerosIfNeeded(image[i], c);
          image[i] = transform(image[i], size, c);
        }

        for (int j = 0; j < size; j++) {
          RGB[] column = new RGB[size];
          for (int i = 0; i < size; i++) {
            column[i] = new RGB();
            column[i].setPixel(c, image[i][j].getPixel(c));
          }
          padZerosIfNeeded(column, c);
          column = transform(column, size, c);
          for (int i = 0; i < size; i++) {
            image[i][j].setPixel(c, (int) Math.round(column[i].getPixel(c)));
          }
        }
      }
    }

    return image;
  }

  private static RGB[] invert(RGB[] sequence, int size, int c) {
    double[] avg = new double[size / 2];
    double[] diff = new double[size / 2];

    for (int i = 0, j = size / 2; j < size; i++, j++) {
      if (i < sequence.length && j < sequence.length) {
        double av = sequence[i].getPixel(c);
        double di = sequence[j].getPixel(c);
        double a = (av + di) / Math.sqrt(2);
        double b = (av - di) / Math.sqrt(2);
        avg[i] = Math.max(0, Math.min(255, a));
        diff[i] = Math.max(0, Math.min(255, b));
      }
    }

    RGB[] result = new RGB[size];
    for (int i = 0, j = 0; i < size - 1; i += 2, j++) {
      if (j < avg.length) {
        result[i].setPixel(c, (int) Math.round(avg[j]));
        result[i + 1].setPixel(c, (int) Math.round(diff[j]));
      }
    }

    return result;
  }

  private static RGB[][] invertImage(RGB[][] image) {
    int width = image.length;
    int height = image[0].length;

    for (int c = 0; c < 3; c++) {
      for (int size = 2; size <= Math.max(width, height); size *= 2) {
        for (int j = 0; j < size; j++) {
          image[j] = invert(image[j], size, c);
        }

        for (int i = 0; i < size; i++) {
          RGB[] column = new RGB[size];
          for (int j = 0; j < size; j++) {
            column[j] = image[j][i];
          }
          column = invert(column, size, c);
          for (int j = 0; j < size; j++) {
            image[j][i].setPixel(c, column[j].getPixel(c));
          }
        }
      }
    }

    return image;
  }

  private static RGB[] transform(RGB[] sequence, int size, int c) {
    double[] avg = new double[size / 2];
    double[] diff = new double[size / 2];

    for (int i = 0, j = 0; i < size - 1; i += 2, j++) {
      double a = sequence[i].getPixel(c);
      double b = sequence[i + 1].getPixel(c);
      double av = (a + b) / Math.sqrt(2);
      double di = (a - b) / Math.sqrt(2);
      avg[j] = av;
      diff[j] = di;
    }

    RGB[] result = new RGB[size];
    for (int i = 0; i < size / 2; i++) {
      result[i].setPixel(c, (int) Math.round(avg[i]));
      result[i + size / 2].setPixel(c, (int) Math.round(diff[i]));
    }

    return result;
  }


  @Override
  public void levelAdjust(String imageName, String destImageName,
                          int b, int m, int w, double splitPercentage) {
    RGB[][] imagePixel = rgbImageStore.retrieveImage(imageName);
    int imageHeight = imagePixel.length;
    int imageWidth = imagePixel[0].length;

    int splitPosition = (int) (imageWidth * (splitPercentage / 100.0));

    // Apply levels adjustment for each channel only for the left half
    for (int channel = 0; channel < 3; channel++) {
      double a = computeA(b, m, w);
      double aA = computeAa(b, m, w);
      double bA = computeAb(b, m, w);
      double cA = computeAc(b, m, w);

      for (int i = 0; i < imageHeight; i++) {
        for (int j = 0; j < imageWidth; j++) {
          // Check if the current pixel is on the left side of the image
          if (j < splitPosition) {
            RGB rgb = imagePixel[i][j];
            int oldValue = rgb.getPixel(channel);
            int newValue = applyQuadraticCurve(a, aA, bA, cA, oldValue);
            rgb.setPixel(channel, newValue);
          }
        }
      }
    }

    this.rgbImageStore.storeImage(destImageName, imagePixel);
  }

  private static double computeA(int b, int m, int w) {
    return (b * b * (m - w)) - b * ((m * m) - (w * w)) + (w * m * m) - (m * w * w);
  }

  private static double computeAa(int b, int m, int w) {
    return (-b * (128 - 255)) + (128 * w) - (255 * m);
  }

  private static double computeAb(int b, int m, int w) {
    return (b * b * (128 - 255)) + (255 * m * m) - (128 * w * w);
  }

  private static double computeAc(int b, int m, int w) {
    return (b * b * ((255 * m) - (128 * w))) - (b * ((255 * m * m) - (128 * w * w)));
  }

  private static int applyQuadraticCurve(double a, double aA, double bA, double cA, int x) {
    double aa = computeA(0, 128, 255);  // A for the default black, mid, white values
    double numerator = aA * x * x + bA * x + cA;
    double result = numerator / aa;

    // Ensure the result is within the valid range (0-255)
    result = Math.max(0, Math.min(255, result));

    // Round the result to the nearest integer
    int newValue = (int) Math.round(result);

    return newValue;
  }


  private void findImgFromRGBStorage(String findOp) {
    if (!this.rgbImageStore.findImage(findOp)) {
      throw new IllegalArgumentException("Image that is named "
              + findOp + " is not present to perform the operation.");
    }
  }
}
