package ImageModel;


import java.io.FileNotFoundException;


public interface ImageProcessingModel {


  void imageLoader(RGB[][] image, String storeFileName) throws FileNotFoundException;


  void createRedGray(String searchImageKey, String storeKey);


  void createGreenGray(String searchImageKey, String storeKey);

  void createBlueGray(String searchImageKey, String storeKey);


  void valueGrayScale(String searchImageKey, String storeKey);


  void lumaGrayScale(String searchImageKey, String storeKey);


  void intensityGrayScale(String searchImageKey, String storeKey);


  void flipVertically(String searchImageKey, String storeKey);


  void flipHorizontally(String searchImageKey, String storeKey);


  void imageBrightenDarken(String searchImageKey, String storeKey, int value);


  void createSepiaTone(String searchImageKey, String storeKey);




  void createBlur(String searchImageKey, String storeKey);


  void sharpenImage(String searchImageKey, String storeKey);


  void splitImage(String searchImageKey, String[] storeKeys);


  void combineImage(String[] searchImageKeys, String storeKey);


  RGB[][] getImage(String searchImageKey);


  RGB[][] saveFile(String filePath, String searchImageKey);

}
