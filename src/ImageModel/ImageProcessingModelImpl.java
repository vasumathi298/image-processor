package ImageModel;


import java.io.FileNotFoundException;


/**
 * The `ImageProcessingModelImpl` class provides implementations
 * for various image processing operations.
 * It loads, transforms, and saves images while
 * utilizing an `RGBImageStorage` to manage image data.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {

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
  public void imageLoader(RGB[][] image, String storeFileName) throws FileNotFoundException {
    this.rgbImageStore.storeImage(storeFileName, image);
  }

  private int getRGBPixelValue(RGB pixel) {
    if (pixel.getPixel(0) >= pixel.getPixel(1) && pixel.getPixel(0) >= pixel.getPixel(2)) {
      return pixel.getPixel(0);
    } else if (pixel.getPixel(1) >= pixel.getPixel(0) && pixel.getPixel(1) >= pixel.getPixel(2)) {
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


  private RGB[][] constructRedGreyScaleImage(RGB[][] orgImage) {
    imageWidth = orgImage[0].length;
    imageHeight = orgImage.length;
    RGB[][] redComp = buildRedPixelImage(orgImage);
    RGB[][] redGrayScaleImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        redGrayScaleImage[k][l] = new RGB(getRGBPixelValue(redComp[k][l]), getRGBPixelValue(redComp[k][l]),
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
        greenGrayScaleImage[k][l] = new RGB(getRGBPixelValue(greenComp[k][l]), getRGBPixelValue(greenComp[k][l]),
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
        blueGrayScaleImage[k][l] = new RGB(getRGBPixelValue(greenComp[k][l]), getRGBPixelValue(greenComp[k][l]),
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
        valueGrayImage[k][l] = new RGB(getRGBPixelValue(retrieveImage[k][l]), getRGBPixelValue(retrieveImage[k][l]),
                getRGBPixelValue(retrieveImage[k][l]));
      }
    }
    this.rgbImageStore.storeImage(storeFind, valueGrayImage);
  }

  private int findWeightedPixelSum(RGB rgbPixels) {
    return (int) (0.2126 * rgbPixels.getPixel(0) + 0.7152 * rgbPixels.getPixel(1) + 0.0722 * rgbPixels.getPixel(2));
  }


  @Override
  public void greyScaleLuma(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    imageHeight = retrieveImage.length;
    imageWidth = retrieveImage[0].length;
    RGB[][] lumaGrayImage = null;
    lumaGrayImage = new RGB[imageHeight][imageWidth];

    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        lumaGrayImage[k][l] = new RGB(findWeightedPixelSum(retrieveImage[k][l]), findWeightedPixelSum(retrieveImage[k][l]),
                findWeightedPixelSum(retrieveImage[k][l]));
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
        intensityGrayImage[k][l] = new RGB(getPixelAverage(intensityGrayImage[k][l]), getPixelAverage(intensityGrayImage[k][l]),
                getPixelAverage(intensityGrayImage[k][l]));
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
        int redComp, greenComp, blueComp;
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
      System.arraycopy(retrieveImage[imageHeight - k - 1], 0, vFlippedImg[k], 0, imageWidth);
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
    int sepiaRed = (int) ((0.393 * inputPixel.getPixel(0)) + (0.769 * inputPixel.getPixel(1)) + (0.189 * inputPixel.getPixel(2)));
    int sepiaGreen = (int) ((0.349 * inputPixel.getPixel(0)) + (0.686 * inputPixel.getPixel(1)) + (0.168 * inputPixel.getPixel(2)));
    int sepiaBlue = (int) ((0.272 * inputPixel.getPixel(0)) + (0.534 * inputPixel.getPixel(1)) + (0.131 * inputPixel.getPixel(2)));
    sepiaRed = Math.min(sepiaRed, 255);
    sepiaGreen = Math.min(sepiaGreen, 255);
    sepiaBlue = Math.min(sepiaBlue, 255);
    RGB sepiaPixel = new RGB(sepiaRed, sepiaGreen, sepiaBlue);
    return sepiaPixel;
  }


  @Override
  public void constructSepia(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = null;
    retrieveImage = rgbImageStore.retrieveImage(imageFind);
    int imageHeight = retrieveImage.length;
    int imageWidth = retrieveImage[0].length;
    RGB[][] sepiaTonedImage = null;
    sepiaTonedImage = new RGB[imageHeight][imageWidth];
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        sepiaTonedImage[k][l] = generateSepiaTone(retrieveImage[k][l]);
      }
    }
    this.rgbImageStore.storeImage(storeFind, sepiaTonedImage);
  }


  @Override
  public void blurImage(String imageFind, String storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] retrieveImage = rgbImageStore.retrieveImage(imageFind);
    int imageHeight = retrieveImage.length;
    int imageWidth = retrieveImage[0].length;
    RGB[][] blurredImage = new RGB[imageHeight][imageWidth];

    double[] gaussianKernel = {
            1.0 / 16, 1.0 / 8, 1.0 / 16,
            1.0 / 8, 1.0 / 4, 1.0 / 8,
            1.0 / 16, 1.0 / 8, 1.0 / 16
    };

    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        double redSumComp = 0;
        double greenSumComp = 0;
        double blueSumComp = 0;
        for (int x = -1; x <= 1; x++) {
          for (int y = -1; y <= 1; y++) {
            int newY = l + y;
            int newX = k + x;
            if (newY >= 0 && newY < imageWidth && newX >= 0 && newX < imageHeight) {
              double weight = gaussianKernel[(x + 1) * 3 + (y + 1)];
              redSumComp += weight * retrieveImage[newX][newY].getPixel(0);
              greenSumComp += weight * retrieveImage[newX][newY].getPixel(1);
              blueSumComp += weight * retrieveImage[newX][newY].getPixel(2);
            }
          }
        }
        blurredImage[k][l] = new RGB((int) redSumComp, (int) greenSumComp, (int) blueSumComp);
      }
    }


    this.rgbImageStore.storeImage(storeFind, blurredImage);
  }

  @Override
  public void sharpenImage(String imageFind, String storeFind) {
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

    int nR, nG, nB;
    for (int k = 0; k < imageHeight; k++) {
      for (int l = 0; l < imageWidth; l++) {
        double redSumComp = 0.0;
        double greenSumComp = 0.0;
        double blueSumComp = 0.0;
        for (int x = -2; x <= 2; x++) {
          for (int y = -2; y <= 2; y++) {
            int row = k + x;
            int col = l + y;
            if (row >= 0 && row < imageHeight && col >= 0 && col < imageWidth) {
              redSumComp += retrieveImage[row][col].getPixel(0) * kernel[x + 2][y + 2];
              greenSumComp += retrieveImage[row][col].getPixel(1) * kernel[x + 2][y + 2];
              blueSumComp += retrieveImage[row][col].getPixel(2) * kernel[x + 2][y + 2];
            }
          }
        }
        nR = Math.min(Math.max((int) redSumComp, 0), 255);
        nG = Math.min(Math.max((int) greenSumComp, 0), 255);
        nB = Math.min(Math.max((int) blueSumComp, 0), 255);
        sharpenedImage[k][l] = new RGB(nR, nG, nB);
      }
    }
    this.rgbImageStore.storeImage(storeFind, sharpenedImage);
  }


  @Override
  public void imageSplitter(String imageFind, String[] storeFind) {
    findImgFromRGBStorage(imageFind);
    RGB[][] redGrayScaleImage, greenGrayScaleImage, blueGrayScaleImage;
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
          mergedImg[k][l] = new RGB(retrivedRedGrayImg[k][l].getPixel(0), retrivedGreenGrayImg[k][l].getPixel(1),
                  retrivedBlueGrayImg[k][l].getPixel(2));
        }
      }
      this.rgbImageStore.storeImage(storeFind, mergedImg);
    } else {
      System.out.println("3 Gray Scales are not present in the given Image");
    }
  }

  private boolean areGrayImagesCompatible(RGB[][] redGrayImg, RGB[][] greenGrayImg, RGB[][] blueGrayImg) {
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


  private void findImgFromRGBStorage(String findOp) {
    if (!this.rgbImageStore.findImage(findOp)) {
      throw new IllegalArgumentException("Image that is named "
              + findOp + " is not present to perform the operation.");
    }
  }
}
