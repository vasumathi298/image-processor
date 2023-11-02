package ImageController;


import java.io.FileNotFoundException;

import ImageModel.RGB;

/**
 * This is ImageFormatOperation interface.
 * It performs load and save image for all commands.
 */
public interface ImageFormatController {

  RGB[][] load(String path, String name) throws FileNotFoundException;

  void save(String path, RGB[][] img);

}
