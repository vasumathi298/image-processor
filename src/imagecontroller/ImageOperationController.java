package imagecontroller;

import imagemodel.ImageProcessingModel;

/**
 * The ImageOperationController interface defines the contract for performing image operations
 * on an ImageProcessingModel.
 */
public interface ImageOperationController {

  /**
   * Perform the image operation on the given ImageProcessingModel.
   *
   * @param imageProcessingModel The model to operate on.
   * @throws Exception If an error occurs during the operation.
   */
  void performOperation(ImageProcessingModel imageProcessingModel) throws Exception;
}
