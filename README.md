# *Assigment 5*

### *Description*

### *Architecture Overview*

### *Interfaces and Classes*

#### Classes:

##### RGB:

The RGB class stores the rgb value of a pixel in an image. Each object represents the three
components of RGB. each one representing a channel's value. This class has two
methods:

1. `int getPixel()`, this method takes the channel as an input and is used to get the channel
   value of the object of RGB class
2. `void setPixel()`, this method takes the channel and its value as an input and is used to set the
   channel
   value for the object.

Both the methods will throw an illegal argument exception if an invalid channel number ( >2) is
provided.

##### ImageProcessingModelImpl:

The `ImageProcessingModelImpl` class provides implementations for various image processing
operations. Below is an overview of each function within the class:

1. `public ImageProcessingModelImpl()`: The constructor initializes an instance
   of `RGBImageStorage`, which is used to store and manage the images.

2. `public void imageLoader(RGB[][] image, String storeFileName) throws FileNotFoundException`: This
   function loads an image represented by a 2D array of RGB pixels and stores it in the RGB image
   storage with the specified file name.

3. `private int getRGBPixelValue(RGB pixel)`: This is a utility function that calculates the maximum
   value among the red, green, and blue components of an RGB pixel.

4. `private RGB[][] buildRedPixelImage(RGB[][] orgImage)`: Constructs a new image with only the red
   component while setting green and blue components to zero.

5. `private RGB[][] buildGreenPixelImage(RGB[][] orgImage)`: Constructs a new image with only the
   green component while setting red and blue components to zero.

6. `private RGB[][] buildBluePixelImage(RGB[][] orgImage)`: Constructs a new image with only the
   blue component while setting red and green components to zero.

7. `public void constructRedGreyScale(String imageFind, String storeFind)`: Constructs a grayscale
   image using the red channel of the input image and stores it with the specified file name.

8. `private RGB[][] constructRedGreyScaleImage(RGB[][] orgImage)`: Constructs a grayscale image
   using only the red component of the input image.

9. `public void constructGreenGreyScale(String imageFind, String storeFind)`: Constructs a grayscale
   image using the green channel of the input image and stores it.

10. `private RGB[][] constructGreenGreyScaleImage(RGB[][] orgImage)`: Constructs a grayscale image
    using only the green component of the input image.

11. `public void constructBlueGreyScale(String imageFind, String storeFind)`: Constructs a grayscale
    image using the blue channel of the input image and stores it.

12. `private RGB[][] constructBlueGreyScaleImage(RGB[][] orgImage)`: Constructs a grayscale image
    using only the blue component of the input image.

13. `public void greyScaleValue(String imageFind, String storeFind)`: Constructs a grayscale image
    using the average intensity of the red, green, and blue components and stores it.

14. `private int findWeightedPixelSum(RGB rgbPixels)`: Calculates the weighted sum of RGB components
    for luminance in grayscale.

15. `public void greyScaleLuma(String imageFind, String storeFind)`: Constructs a grayscale image
    using the luminance (Y) component and stores it.

16. `private int getPixelAverage(RGB pixel)`: Calculates the average of the RGB components to create
    an intensity-based grayscale image.

17. `public void greyScaleIntensity(String imageFind, String storeFind)`: Constructs a grayscale
    image using the average intensity of RGB components and stores it.

18. `public void brightenOrDarkenImage(String imageFind, String storeFind, int value)`: Adjusts the
    brightness of the image by adding or subtracting a specified value to/from the RGB components.

19. `public void verticalImageFlip(String imageFind, String storeFind)`: Flips the image vertically.

20. `public void horizontalImageFlip(String imageFind, String storeFind)`: Flips the image
    horizontally.

21. `private RGB generateSepiaTone(RGB inputPixel)`: Generates a sepia tone effect on an RGB pixel.

22. `public void constructSepia(String imageFind, String storeFind)`: Applies a sepia tone effect to
    the image and stores the result.

23. `public void blurImage(String imageFind, String storeFind)`: Applies a Gaussian blur effect to
    the image.

24. `public void sharpenImage(String imageFind, String storeFind)`: Applies a sharpening filter to
    the image.

25. `public void imageSplitter(String imageFind, String[] storeFind)`: Splits the image into its
    red, green, and blue channels and stores them separately.

26. `public void imageMerger(String[] imageFind, String storeFind)`: Merges three grayscale images (
    red, green, and blue channels) into a single color image.

27. `public RGB[][] saveFile(String path, String imageFind)`: Saves the image with the specified
    name to the provided path.

28. `public RGB[][] retrieveImage(String searchImageKey)`: Retrieves the image with the specified
    name.

29. `private void findImgFromRGBStorage(String findOp)`: Checks if the image with the given name
    exists in the RGB image storage and raises an exception if not found.

##### RGBImageStorage

Here's an overview of each function in the `RGBImageStorage` class:

1. Constructor `RGBImageStorage()`:
    - Initializes an `RGBImageStorage` object.
    - Creates an internal `imageStorageMap`, which is a `HashMap` used for storing image data.

2. `storeImage(String imageName, RGB[][] inputImage)`:
    - Stores a pixel array (`inputImage`) under a specified image name in the `imageStorageMap`.
    - Parameters:
        - `imageName`: The name under which the image is stored.
        - `inputImage`: The pixel array representing the image.

3. `retrieveImage(String imageName)`:
    - Retrieves the pixel array associated with a given image name from the `imageStorageMap`.
    - Parameters:
        - `imageName`: The name of the image to retrieve.
    - Returns:
        - The pixel array (`RGB[][]`) representing the image.
        - Returns `null` if the image with the specified name is not found in the storage.

4. `findImage(String imageName)`:
    - Checks if an image with the specified name exists in the storage (`imageStorageMap`).
    - Parameters:
        - `imageName`: The name of the image to check for existence.
    - Returns:
        - `true` if the image with the specified name exists in the storage.
        - `false` if the image is not found.

These functions together provide a simple and efficient way to store and retrieve pixel arrays
associated with image names using a key-value map. The class allows you to manage image data within
your application, making it easy to store, retrieve, and check for the existence of images by their
names.

##### Image Mappers Enums:

#### ImageOperationMapper

The `ImageOperationMapper` enum provides a mapping between command strings and their
corresponding `ImageOperationController` implementations for performing various image operations.
This enum simplifies the process of creating controller instances for different image processing
commands. Here's an overview of this enum:

1. **Enum Values**: The enum includes several values, each representing a specific image processing
   command (e.g., "LOAD," "BRIGHTEN," "HORIZONTAL_FLIP"). Each enum value is associated with a
   command string and a function that can create an `ImageOperationController` instance for that
   command.

2. **Command String**: Each enum value has a command string, which corresponds to a specific image
   processing operation. For example, "LOAD" corresponds to the "load" operation, "BRIGHTEN" to
   the "brighten" operation, and so on.

3. **Controller Supplier Function**: The enum values also include a function that takes an argument
   and creates an instance of `ImageOperationController` for the associated command. This allows
   dynamic creation of controller instances based on the command string and its arguments.

4. **`getCommand()` Method**: The `getCommand()` method provides a way to retrieve the command
   string associated with each enum value.

5. **`createController(String argument)` Method**: The `createController(String argument)` method
   uses the associated controller supplier function to create an instance
   of `ImageOperationController` for a specific command based on the provided argument.

In summary, the `ImageOperationMapper` enum simplifies the process of
creating `ImageOperationController` instances for different image processing commands, making it a
flexible and extensible way to handle various image processing operations in an image processing
system.

#### ImageFormatMapper

The `ImageFormatMapper` enum provides a mapping between file extensions and the
corresponding `ImageFormatController` implementations. This enum is designed to help create
instances of `ImageFormatController` based on the image format name (file extension). Here's an
overview of this enum:

1. **Enum Values**: The enum consists of several values, each representing a specific image format (
   e.g., PPM, PNG, BMP, JPEG, JPG). Each enum value is associated with a format name (file
   extension) and a function that can create an `ImageFormatController` instance for that format.

2. **Format Name**: Each enum value has a format name, which corresponds to a specific image file
   format. For example, "PPM" corresponds to PPM format, "PNG" to PNG format, and so on.

3. **Controller Supplier Function**: The enum values also include a function that takes an argument
   and creates an instance of `ImageFormatController` for the associated format. This allows dynamic
   creation of controller instances based on the format name.

4. **`getFormatName()` Method**: The `getFormatName()` method provides a way to retrieve the format
   name associated with each enum value.

5. **`createController(String argument)` Method**: The `createController(String argument)` method
   uses the associated controller supplier function to create an instance of `ImageFormatController`
   for a specific format based on the provided argument.

In summary, the `ImageFormatMapper` enum simplifies the process of creating `ImageFormatController`
instances for different image formats, making it a flexible and extensible way to handle various
image file types in an image processing system.

##### Image Format Classes:

#### BPMFormat

The `BPMFormat` class implements the `ImageFormatController` interface to handle saving and loading
images in BMP format. It includes methods for saving RGB pixel data as a BMP image and for loading
BMP images into RGB pixel arrays. The class ensures proper format validation and exception handling,
providing a reliable interface for BMP format image operations.

#### JPEGFormat

The `JPEGFormat` class implements the `ImageFormatController` interface for saving and loading
images in JPEG format, supporting both .jpg and .jpeg extensions. It provides methods to save RGB
pixel data as a JPEG image and load JPEG images into RGB pixel arrays. The class handles proper
format validation and exception handling, ensuring reliable JPEG format image operations.

#### PNGFormat

The `PNGFormat` class implements the `ImageFormatController` interface for saving and loading images
in PNG format. It offers methods to save RGB pixel data as a PNG image and load PNG images into RGB
pixel arrays. The class ensures proper format validation and handles exceptions, making it reliable
for PNG image operations.

#### PPMFormat

The `PPMFormat` class implements the `ImageFormatController` interface for saving and loading images
in PPM format. It provides methods to save RGB pixel data as a PPM image and load PPM images into
RGB pixel arrays. The class ensures proper format validation and handles exceptions during file
operations. Additionally, it includes a method for reading PPM files and converting them into a 2D
array of RGB pixels.

##### Image Operation Classes:

#### BlurSharpen

The `BlurSharpen` class implements the `ImageOperationController` interface and is responsible for
performing blur and sharpen operations on images based on provided instructions, using
the `ImageProcessingModel`, with the input instructions parsed from a string.

#### BrightenDarken

The `BrightenDarken` class implements the `ImageOperationController` interface and is responsible
for adjusting the brightness of an image, allowing both brightening and darkening operations based
on provided instructions. It parses input commands, applies the brightness adjustment using
the `ImageProcessingModel`, and provides a message indicating the change in brightness level. The
class ensures error handling for valid command input.

#### ExtractRGB

The `ExtractRGB` class implements the `ImageOperationController` interface and is responsible for
extracting the individual red, green, and blue grayscale components of an image based on
user-provided instructions. It parses input commands, performs the extraction using
the `ImageProcessingModel`, and displays a message indicating the successful separation of these
components. The class includes error handling for ensuring valid commands.

#### GreyScale

The `GreyScale` class implements the `ImageOperationController` interface and provides operations to
generate various grayscale components of an image, such as red, green, blue, value, luma, and
intensity, based on user-provided instructions. It handles different types of grayscale generation
using the `ImageProcessingModel` and includes error handling for valid commands. The class also
prints informative messages upon successful grayscale component generation.

#### HorizontalFlip

The `HorizontalFlip` class implements the `ImageOperationController` interface and is designed for
performing a horizontal flip operation on an image. It parses user-provided instructions, including
the image name, and applies the horizontal flip using the `ImageProcessingModel`. The class also
handles exceptions and provides informative error messages for valid command input.

#### VerticalFlip

The `VerticalFlip` class implements the `ImageOperationController` interface and is responsible for
performing a vertical flip operation on images. It parses input instructions, including the image
name, and applies the vertical flip using the provided `ImageProcessingModel`. The class also
handles exceptions and provides informative messages upon successful vertical flipping, encouraging
further actions like saving or quitting.

#### ImageLoader

The `ImageLoader` class implements the `ImageOperationController` interface and is responsible for
loading and processing images based on user-provided instructions. It parses input commands,
including image file paths, and dynamically selects the appropriate image format controller for
loading. The class offers flexibility through a map of supported image formats and handles
exceptions, ensuring that the chosen file format is supported for loading and processing.

#### ImageSaver

The `ImageSaver` class implements the `ImageOperationController` interface and is responsible for
saving processed images based on user-provided instructions. It parses input commands, including
image file paths, and dynamically selects the appropriate image format controller for saving. The
class offers flexibility through a map of supported image formats, handles exceptions, and provides
feedback indicating the successful saving of the image along with the file path.

#### MergeRGB

The `MergeRGB` class implements the `ImageOperationController` interface and is responsible for
merging RGB components of an image based on user-provided instructions. It parses input commands,
combining the specified RGB components using the `ImageProcessingModel`. The class also handles
exceptions and provides informative messages upon successful component merging.

#### ScriptRunnable

The `ScriptRunnable` class implements the `ImageOperationController` interface and is designed to
execute a script file containing a sequence of image processing instructions. It parses the script
file, validates its format, and processes each instruction using the
provided `ImageProcessingModel`. The class handles exceptions, including file not found and
incorrect format, while executing the script.

#### TransformColor

The `ScriptRunnable` class is an implementation of the `ImageOperationController` interface and
serves the purpose of running a script file that contains a sequence of image processing
instructions. It reads and parses the script, validates its format, and executes each instruction
using the provided `ImageProcessingModel`. The class handles exceptions such as file not found and
incorrect script format, ensuring reliable script execution.

#### Interfaces

##### ImageProcessingModel:

The `ImageProcessingModel` interface defines a contract for performing various image processing
operations. Implementations of this interface are responsible for loading, transforming, and saving
images. It offers methods for tasks such as loading images, performing grayscale conversions, image
flipping, brightness adjustments, sharpening, applying filters, saving images, and more. This
interface serves as a foundation for building image processing functionality and provides a
structured approach to working with images in an application.

##### ImageFormatController

The `ImageFormatController` interface defines a contract for handling various image formats,
providing methods to save and load images in different formats. Implementations of this interface
are responsible for handling the specifics of image file formats, allowing the application to
interact with images in a format-agnostic manner. This interface is essential for supporting a
variety of image formats and enabling the storage and retrieval of images in a flexible and
extensible way.

##### ImageOperationController

The `ImageOperationController` interface defines a contract for performing image operations on
an `ImageProcessingModel`. Implementations of this interface are responsible for executing specific
image processing tasks, and they must adhere to this contract by providing a `performOperation`
method that accepts an `ImageProcessingModel`. This interface serves as a blueprint for creating
various image processing controllers that can be used to perform actions such as loading,
transforming, and saving images in an organized and consistent manner.

##### ImageProcessingConroller

The `ImageProcessingController` interface defines the operations that a controller class should
implement when managing and processing image operations. It serves as a contract for controllers in
an image processing system and includes the following main methods:

1. `imageOperationSelector()`: This method is responsible for initiating the selection of image
   processing operations. It allows the controller to choose a specific image processing operation
   to perform. It may throw an exception if any errors occur during the operation selection.

2. `operationProcessor(Scanner operation)`: This method processes the selected image operation based
   on the provided `Scanner` input. It takes the details of the operation as input and handles the
   execution of the chosen operation. It can throw exceptions if any errors occur during the
   operation processing.

In summary, the `ImageProcessingController` interface provides a structured framework for handling
the selection and execution of image processing operations within an image processing system, making
it a fundamental component for controlling image processing workflows.

### *How to Run:*

To run the application, follow these steps:

1. Execute the `RunImageProcessor` program located in the src folder.
2. The program will enter a waiting state, ready to receive user commands.
3. You can interact with the program by entering input commands directly into the IntelliJ terminal,
   or you have the option to load a text file containing the commands using the "load" command in
   the terminal.

#### *To run from CLI*

To execute a command script from the command-line interface (CLI), follow these steps:

1. Navigate to the "res" folder in your terminal.
2. Run the specified command to execute the program.

### *Operations:*

Our application offers a versatile set of image processing operations, including:

1. **Load:** One can import JPEG,BPM,PPM,JPG,PNG image files.
2. **Save:** One can export images in the JPEG,BPM,PPM,JPG,PNG image file format.
3. **Vertical Flip:** Flip images vertically.
4. **Horizontal Flip:** Flip images horizontally.
5. **Grayscale Conversion:** Transform images into grayscale using value, intensity, or luma
   components.
6. **Channel Grayscale:** Create grayscale images with a specific color channel.
7. **Brightness Adjustment:** Adjust the brightness by either brightening or darkening images.
8. **Sepia Toning:** Apply a sepia tone effect to your images.
9. **Blur Effect:** Add a blurring filter to the images.
10. **Sharpening:** Enhance image sharpness with a sharpening filter.


### Image Citation from New York time
Image that was used for Manipulation was from (https://static01.nyt.com/images/2021/02/27/arts/tomjerry1/tomjerry1-jumbo.jpg?quality=75&auto=webp).
The image from this link was saved as manhattan-small.png.
All the output images are placed in output/ folder


### *Simple Image Manipulation Features:*


| Operations                    | Command                                                                                                           | Output Image Name                                                                                                         |
|:------------------------------|:------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|
| load                          | load  manhattan-small manhattan-small                                                                             |                                                                                                                           |
| Brighten                      | brighten 100 manhattan-small manhattan-small-brighten                                                             | output/manhattan-small-brighten.png                                                                                       | 
| Darken                        | brighten -100 manhattan-small manhattan-small-darken                                                              | output/manhattan-small-darken.png                                                                                         | 
| Red Component                 | red-component manhattan-small manhattan-small-red-comp                                                            | output/manhattan-small-red-comp.png                                                                                       | 
| Blue Component                | blue-component manhattan-small manhattan-small-blue-comp                                                          | output/manhattan-small-blue-comp.png                                                                                      | 
| Green Component               | green-component manhattan-small manhattan-small-green-comp                                                        | output/manhattan-small-green-comp.png                                                                                     | 
| Sepia                         | sepia manhattan-small manhattan-small-sepia                                                                       | output/manhattan-small-sepia.png                                                                                          | 
| Blur                          | blur manhattan-small manhattan-small-blur                                                                         | output/manhattan-small-blur.png                                                                                           | 
| Sharpen                       | sharpen manhattan-small manhattan-small-sharpen                                                                   | output/manhattan-small-sharpen.png                                                                                        | 
| RGB Split                     | rgb-split manhattan-small manhattan-small-red-grey manhattan-small-green-grey manhattan-small-blue-grey           | output/manhattan-small-red-grey.png,<br/> output/manhattan-small-green-grey.png,<br/>output/manhattan-small-blue-grey.png |
| RGB Combine                   | rgb-combine manhattan-small-red comp manhattan-small-blue-comp manhattan-small-green-comp manhattan-small-combine | output/manhattan-small-combine.png                                                                                        |
| Greyscale Red Component       | greyscale red-component manhattan-small manhattan-small-red-component                                             | output/manhattan-small-red-component.png                                                                                  |
| Greyscale Green Component     | greyscale green-component manhattan-small manhattan-small-green-component                                         | output/manhattan-small-green-component.png                                                                                |
| Greyscale Blue Component      | greyscale blue-component manhattan-small manhattan-small-blue-component                                           | output/manhattan-small-blue-component.png                                                                                 |
| Greyscale Value Component     | greyscale value-component manhattan-small manhattan-small-value-component                                         | output/manhattan-small-value-component.png                                                                                |
| Greyscale Luma Component      | greyscale luma-component manhattan-small manhattan-small-luma-component                                           | output/manhattan-small-luma-component.png                                                                                 |
| Greyscale Intensity Component | greyscale intensity-component manhattan-small manhattan-small-intensity-component                                 | output/manhattan-small-intensity-component.png                                                                            |


### *Design Changes*
The `EnhancedImageProcessingModel` interface extends the `ImageProcessingModel` interface and defines additional methods for enhanced image processing. Here's a summary of the methods in this interface:

1. **`fetchHistogram(String imageName, String destImageName)`**:
    - **Input Parameters**: `imageName` (source image name), `destImageName` (destination image name)
    - **Return Type**: `int[][]`
    - **Description**: Retrieves the histogram of the image specified by `imageName` and saves it to the destination image specified by `destImageName`. The histogram is represented as a two-dimensional array of integers.

2. **`compressImage(String fileName, String destName, double threshold)`**:
    - **Input Parameters**: `fileName` (name of the image file to compress), `destName` (name of the destination image file), `threshold` (compression threshold)
    - **Return Type**: `void`
    - **Description**: Compresses the image specified by `fileName` and saves the compressed version to the destination image specified by `destName`. The compression is controlled by the `threshold` parameter.

3. **`imageColorCorrection(String imageName, String destImageName, double splitPercentage)`**:
    - **Input Parameters**: `imageName` (source image name), `destImageName` (destination image name), `splitPercentage` (percentage for color correction)
    - **Return Type**: `void`
    - **Description**: Performs color correction on the image specified by `imageName` and saves the corrected image to the destination specified by `destImageName`. The `splitPercentage` parameter determines the extent of color correction.

4. **`levelAdjust(String imageName, String destImageName, int b, int m, int w, double splitPercentage)`**:
    - **Input Parameters**: `imageName` (source image name), `destImageName` (destination image name), `b` (brightness adjustment), `m` (midpoint adjustment), `w` (width adjustment), `splitPercentage` (percentage for level adjustment)
    - **Return Type**: `void`
    - **Description**: Adjusts the levels of the image specified by `imageName` and saves the adjusted image to the destination specified by `destImageName`. The adjustments are controlled by the parameters `b` (brightness), `m` (midpoint), `w` (width), and `splitPercentage` (extent of adjustment).

This interface serves as a contract for classes that implement enhanced image processing functionality, building upon the basic image processing capabilities provided by the `ImageProcessingModel` interface. 
This class extends the Interface `ImageProcessingModel` and these functions are implemented in`ImageProcessingModelImpl`

Separate Classes called `ImageCompression`, `ImageColorCorrection`, `LevelAdjustment` ,`Histogram` were created in `imageoperations` package
which is the part of the controller. In the `ImageOperationMapper` enum, all four operations were matched to its controller object like below.
- `HISTOGRAM("histogram", Histogram::new)`
- `IMAGE_COMPRESSION("compress", ImageCompression::new)`
- `COLOR_CORRECTION("color-correct", ImageColorCorrection::new)`
- `LEVEL_ADJUST("levels-adjust", LevelAdjustment::new)`

For split view, the classes `Blur`, `Sharpen`, `GreScale`in `imageperation` packages in Controller Classes were dealt with changes, 
to accept split percentage as an optional input parameter in the functions implemented by the models.
If user does not input any parameter, this project will consider it to be 0 and does the operation for the entire image,
else if split percentage is provided, the manipulations are done only to the left of the image from the percentage provided by the user.

### *Enhanced Image Manipulation Features:*

| Operations                       | Command                                                                                | Output Image Name                                |
|:---------------------------------|:---------------------------------------------------------------------------------------|:-------------------------------------------------|
| Color Correction                 | color-correct  manhattan-small manhattan-small-color-corrected                         | output/manhattan-small-color-corrected.png       |
| Color Correction + Split         | color-correct manhattan-small manhattan-small-color-corrected-split split 40           | output/manhattan-small-color-corrected-split.png | 
| Histogram                        | histogram manhattan-small manhattan-small-histogram                                    | output/manhattan-small-histogram.png             |
| Level Adjustment                 | levels-adjust 20 100 255 manhattan-small manhattan-small-level-adjust                  | output/manhattan-small-level-adjust.png          | 
| Level Adjustment + Split         | levels-adjust 20 100 255 manhattan-small manhattan-small-level-adjust-split split 60   | output/manhattan-small-level-adjust-split.png    | 
| Blur + Split                     | blur manhattan-small manhattan-small-blur-split split 50                               | output/manhattan-small-blur-split.png            | 
| Sharpen + Split                  | sharpen manhattan-small manhattan-small-sharpen-split split 50                         | output/manhattan-small-sharpen-split.png         | 
| Sepia + Split                    | sepia manhattan-small manhattan-small-sepia-split split 70                             | output/manhattan-small-sepia-split.png           | 
| Greyscale Luma Component + Split | greyscale luma-component manhattan-small manhattan-small-luma-split-component split 35 | output/manhattan-small-luma-split-component.png  | 
| Compression                      | compress manhattan-small manhattan-small-compress-20                                   | output/manhattan-small-compress-20.png           |

All the commands in the script file - output/command.txt are executed via command line argument and also in a test case `testRunScript()` in `TestEnhancedImageManipulations` Class file. 
The output images of the commands are placed in output/script-output/.
All the commands were tested in the script, with many operations being manipulated above the previous results.
