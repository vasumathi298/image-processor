package ImageController;


import java.io.FileNotFoundException;

import ImageModel.ImageProcessingModel;


public interface ImageOperationController {

  void performOperation(ImageProcessingModel imageProcessingModel) throws Exception;

}

