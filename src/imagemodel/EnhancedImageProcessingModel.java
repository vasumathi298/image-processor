package imagemodel;

import java.io.IOException;

public interface EnhancedImageProcessingModel extends ImageProcessingModel{


  public int[][] fetchHistogram(String imageName, String destImageName) throws IOException;

  public void compressImage(String fileName, String destName, double threshold) ;

  public void imageColorCorrection(String imageName, String destImageName, double splitPercentage) throws IOException;

  public void levelAdjust( String imageName, String destImageName, int b, int m, int w, double splitPercentage);

  }
