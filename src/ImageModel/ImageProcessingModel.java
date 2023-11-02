package ImageModel;


import java.io.FileNotFoundException;


public interface ImageProcessingModel {


  void imageLoader(RGB[][] img, String name) throws FileNotFoundException;


  void constructRedGreyScale(String img, String storage);


  void constructBlueGreyScale(String img, String storage);


  void constructGreenGreyScale(String img, String storage);


  void greyScaleLuma(String img, String storage);


  void greyScaleValue(String img, String storage);


  void greyScaleIntensity(String img, String storage);


  void verticalImageFlip(String img, String storage);


  void horizontalImageFlip(String img, String storage);


  void brightenOrDarkenImage(String img, String storage, int incOrDecVal);


  void sharpenImage(String img, String storage);


  void constructSepia(String img, String storage);


  void blurImage(String img, String storage);


  RGB[][] saveFile(String path, String img);

  void imageSplitter(String img, String[] storage);


  void imageMerger(String[] img, String storage);


  RGB[][] retrieveImage(String img);


}
