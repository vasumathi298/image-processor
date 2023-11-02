package ImageModel;


import java.io.FileNotFoundException;


public class ImageProcessingModelImpl implements ImageProcessingModel {

  private final RGBImageStorage storage;
  private int width;
  private int height;

  /**
   * The constructor for our ImageprocessingModel. A hashstorage is created everytime a object of
   * the image processing model is created.
   */
  public ImageProcessingModelImpl() {
    this.storage = new RGBImageStorage();
  }


  @Override
  public void imageLoader(RGB[][] image, String storeFileName) throws FileNotFoundException {
    this.storage.addImage(storeFileName, image);
  }


  private RGB[][] createRed(RGB[][] image) {
    width = image[0].length;
    height = image.length;
    RGB[][] redImage = new RGB[height][width];
    int red = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        red = image[i][j].getPixel(0);
        redImage[i][j] = new RGB(red, 0, 0);
      }
    }
    return redImage;
  }


  private RGB[][] createGreen(RGB[][] image) {
    width = image[0].length;
    height = image.length;
    RGB[][] greenImage = new RGB[height][width];
    int green = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        green = image[i][j].getPixel(1);
        greenImage[i][j] = new RGB(0, green, 0);
      }
    }
    return greenImage;
  }


  private RGB[][] createBlue(RGB[][] image) {
    width = image[0].length;
    height = image.length;
    RGB[][] blueImage = new RGB[height][width];
    int blue = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        blue = image[i][j].getPixel(2);
        blueImage[i][j] = new RGB(0, 0, blue);
      }
    }
    return blueImage;
  }


  @Override
  public void createRedGray(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    RGB[][] redGrayImage = redGrayHelper(image);
    this.storage.addImage(storeKey, redGrayImage);
  }

  private RGB[][] redGrayHelper(RGB[][] image) {
    width = image[0].length;
    height = image.length;
    RGB[][] red = createRed(image);
    RGB[][] redGrayImage = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redGrayImage[i][j] = new RGB(getValue(red[i][j]), getValue(red[i][j]),
                getValue(red[i][j]));
      }
    }
    return redGrayImage;
  }

  @Override
  public void createGreenGray(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    RGB[][] greenGrayImage = greenGrayHelper(image);
    this.storage.addImage(storeKey, greenGrayImage);
  }


  private RGB[][] greenGrayHelper(RGB[][] image) {
    width = image[0].length;
    height = image.length;
    RGB[][] green = createGreen(image);
    RGB[][] greenGrayImage = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        greenGrayImage[i][j] = new RGB(getValue(green[i][j]), getValue(green[i][j]),
                getValue(green[i][j]));
      }
    }
    return greenGrayImage;
  }


  @Override
  public void createBlueGray(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    RGB[][] blueGrayImage = blueGrayHelper(image);
    this.storage.addImage(storeKey, blueGrayImage);
  }


  private RGB[][] blueGrayHelper(RGB[][] image) {
    width = image[0].length;
    height = image.length;
    RGB[][] blue = createBlue(image);
    RGB[][] blueGrayImage = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        blueGrayImage[i][j] = new RGB(getValue(blue[i][j]), getValue(blue[i][j]),
                getValue(blue[i][j]));
      }
    }
    return blueGrayImage;
  }


  private int getValue(RGB pixel) {
    if (pixel.getPixel(0) >= pixel.getPixel(1) && pixel.getPixel(0) >= pixel.getPixel(2)) {
      return pixel.getPixel(0);
    } else if (pixel.getPixel(1) >= pixel.getPixel(0) && pixel.getPixel(1) >= pixel.getPixel(2)) {
      return pixel.getPixel(1);
    } else {
      return pixel.getPixel(2);
    }
  }


  @Override
  public void valueGrayScale(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    width = image[0].length;
    height = image.length;
    RGB[][] value = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        value[i][j] = new RGB(getValue(image[i][j]), getValue(image[i][j]),
                getValue(image[i][j]));
      }
    }
    this.storage.addImage(storeKey, value);
  }

  private int getWeightedSum(RGB pixel) {
    return (int) (0.2126 * pixel.getPixel(0) + 0.7152 * pixel.getPixel(1) + 0.0722 * pixel.getPixel(2));
  }


  @Override
  public void lumaGrayScale(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    RGB[][] lumaGray = new RGB[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        lumaGray[i][j] = new RGB(getWeightedSum(image[i][j]), getWeightedSum(image[i][j]),
                getWeightedSum(image[i][j]));
      }
    }
    this.storage.addImage(storeKey, lumaGray);
  }


  private int getAverage(RGB pixel) {
    return (pixel.getPixel(0) + pixel.getPixel(1) + pixel.getPixel(2)) / 3;
  }

  /**
   * A method to get a grey scale version of the image using the intensity component.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  @Override
  public void intensityGrayScale(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    width = image[0].length;
    height = image.length;
    RGB[][] intensity = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        intensity[i][j] = new RGB(getAverage(image[i][j]), getAverage(image[i][j]),
                getAverage(image[i][j]));
      }
    }
    this.storage.addImage(storeKey, intensity);
  }

  /**
   * A method to flip the image vertically.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the newly flipped image in the Hashmap
   */
  @Override
  public void flipVertically(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    RGB[][] flippedImage = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      System.arraycopy(image[height - i - 1], 0, flippedImage[i], 0, width);
    }
    this.storage.addImage(storeKey, flippedImage);
  }

  /**
   * A method to flip the image horizontally.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the newly flipped image in the Hashmap
   */
  @Override
  public void flipHorizontally(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    RGB[][] flippedImage = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        flippedImage[i][j] = image[i][width - j - 1];
      }
    }
    this.storage.addImage(storeKey, flippedImage);
  }

  @Override
  public void imageBrightenDarken(String searchImageKey, String storeKey, int value) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    RGB[][] brightenImageDarken = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        brightenImageDarken[i][j] = image[i][j];
        int red = value < 0 ? Math.max((image[i][j].getPixel(0) + value), 0)
                : Math.min(image[i][j].getPixel(0) + value, 255);
        int green = value < 0 ? Math.max((image[i][j].getPixel(1) + value), 0)
                : Math.min(image[i][j].getPixel(1) + value, 255);
        int blue = value < 0 ? Math.max((image[i][j].getPixel(2) + value), 0)
                : Math.min(image[i][j].getPixel(2) + value, 255);

        brightenImageDarken[i][j].setPixel(0, red);
        brightenImageDarken[i][j].setPixel(1, green);
        brightenImageDarken[i][j].setPixel(2, blue);
      }
    }
    this.storage.addImage(storeKey, brightenImageDarken);
  }


  private RGB createSepiaValue(RGB pixel) {
    int red = (int) ((0.393 * pixel.getPixel(0)) + (0.769 * pixel.getPixel(1)) + (0.189 * pixel.getPixel(2)));
    int green = (int) ((0.349 * pixel.getPixel(0)) + (0.686 * pixel.getPixel(1)) + (0.168 * pixel.getPixel(2)));
    int blue = (int) ((0.272 * pixel.getPixel(0)) + (0.534 * pixel.getPixel(1)) + (0.131 * pixel.getPixel(2)));
    red = Math.min(red, 255);
    green = Math.min(green, 255);
    blue = Math.min(blue, 255);
    RGB sepia = new RGB(red, green, blue);
    return sepia;
  }


  @Override
  public void createSepiaTone(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    RGB[][] sepiaTone = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sepiaTone[i][j] = createSepiaValue(image[i][j]);
      }
    }
    this.storage.addImage(storeKey, sepiaTone);
  }




  private int overlap(int value) {
    return Math.max(0, Math.min(255, value));
  }


  @Override
  public void createBlur(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    RGB[][] blurredImage = new RGB[height][width];

    double[] gaussianFilter = {
            1.0 / 16, 1.0 / 8, 1.0 / 16,
            1.0 / 8, 1.0 / 4, 1.0 / 8,
            1.0 / 16, 1.0 / 8, 1.0 / 16
    };

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        double sumR = 0;
        double sumG = 0;
        double sumB = 0;
        for (int dx = -1; dx <= 1; dx++) {
          for (int dy = -1; dy <= 1; dy++) {
            int ny = y + dy;
            int nx = x + dx;
            if (ny >= 0 && ny < width && nx >= 0 && nx < height) {
              double weight = gaussianFilter[(dx + 1) * 3 + (dy + 1)];

              sumR += weight * image[nx][ny].getPixel(0);
              sumG += weight * image[nx][ny].getPixel(1);
              sumB += weight * image[nx][ny].getPixel(2);
            }
          }
        }

        blurredImage[x][y] = new RGB((int) sumR, (int) sumG, (int) sumB);
      }
    }
    this.storage.addImage(storeKey, blurredImage);
  }

  /**
   * A method to apply sharpening over the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the sharpened image in the Hashmap
   */
  @Override
  public void sharpenImage(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    RGB[][] result = new RGB[height][width];

    double[][] kernel = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double red = 0.0;
        double green = 0.0;
        double blue = 0.0;
        for (int k = -2; k <= 2; k++) {
          for (int l = -2; l <= 2; l++) {
            int row = i + k;
            int col = j + l;
            if (row >= 0 && row < height && col >= 0 && col < width) {
              red += image[row][col].getPixel(0) * kernel[k + 2][l + 2];
              green += image[row][col].getPixel(1) * kernel[k + 2][l + 2];
              blue += image[row][col].getPixel(2) * kernel[k + 2][l + 2];
            }
          }
        }
        int newRed = Math.min(Math.max((int) red, 0), 255);
        int newGreen = Math.min(Math.max((int) green, 0), 255);
        int newBlue = Math.min(Math.max((int) blue, 0), 255);
        result[i][j] = new RGB(newRed, newGreen, newBlue);
      }
    }

    this.storage.addImage(storeKey, result);
  }


  @Override
  public void splitImage(String searchImageKey, String[] storeKeys) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    RGB[][] redGrayImage = redGrayHelper(image);
    RGB[][] greenGrayImage = greenGrayHelper(image);
    RGB[][] blueGrayImage = blueGrayHelper(image);
    this.storage.addImage(storeKeys[2], redGrayImage);
    this.storage.addImage(storeKeys[3], greenGrayImage);
    this.storage.addImage(storeKeys[4], blueGrayImage);
  }


  @Override
  public void combineImage(String[] searchImageKeys, String storeKey) {
    searchHashStorage(searchImageKeys[1]);
    searchHashStorage(searchImageKeys[2]);
    searchHashStorage(searchImageKeys[3]);
    RGB[][] redGrayScale = storage.fetchImage(searchImageKeys[1]);
    RGB[][] greenGrayScale = storage.fetchImage(searchImageKeys[2]);
    RGB[][] blueGrayScale = storage.fetchImage(searchImageKeys[3]);
    if ((redGrayScale.length == greenGrayScale.length)
            && (greenGrayScale.length == blueGrayScale.length)
            && (redGrayScale.length == blueGrayScale.length)
            && (redGrayScale[0].length == greenGrayScale[0].length)
            && (greenGrayScale[0].length == blueGrayScale[0].length)
            && (redGrayScale[0].length == blueGrayScale[0].length)) {
      RGB[][] combineImage = new RGB[redGrayScale.length][redGrayScale[0].length];
      for (int i = 0; i < redGrayScale.length; i++) {
        for (int j = 0; j < redGrayScale[0].length; j++) {
          combineImage[i][j] = new RGB(redGrayScale[i][j].getPixel(0), greenGrayScale[i][j].getPixel(1),
                  blueGrayScale[i][j].getPixel(2));
        }
      }
      this.storage.addImage(storeKey, combineImage);
    } else {
      System.out.println("All 3 grayscales are not the same image");
    }
  }


  @Override
  public RGB[][] saveFile(String filePath, String searchImageKey) {
    return this.storage.fetchImage(searchImageKey);
  }


  @Override
  public RGB[][] getImage(String searchImageKey) {
    searchHashStorage(searchImageKey);
    RGB[][] image = storage.fetchImage(searchImageKey);
    return image;
  }


  private void searchHashStorage(String searchCommand) {
    if (!this.storage.search(searchCommand)) {
      throw new IllegalArgumentException("There is no image object called "
              + searchCommand + " present in memory to perform operation.");
    }
  }
}
