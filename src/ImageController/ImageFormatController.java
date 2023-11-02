package ImageController;


import ImageModel.RGB;


public interface ImageFormatController {

  void save(String path, RGB[][] imageToSave) throws Exception;

  RGB[][] load(String path, String fileNameToLoad) throws Exception;


}
