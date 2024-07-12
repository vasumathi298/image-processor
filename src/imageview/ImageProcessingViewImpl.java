package imageview;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;

import javax.swing.filechooser.FileNameExtensionFilter;


import imagecontroller.Features;
import imagemodel.EnhancedImageProcessingModel;
import imagemodel.ImageProcessingModel;
import imagemodel.RGB;

/**
 * This class holds all the GUI implementation of view.
 */
public class ImageProcessingViewImpl extends JFrame implements ImageProcessingView {


  private final String objectName = "image";
  private final JPanel imagePanel;
  private final JPanel histogramPanel;
  private final JButton loadButton;
  private final JButton verticalFlipButton;
  private final JButton horizontalFlip;
  private final JButton greyScaleButton;
  private final JButton blur;

  private JButton split;
  private final JButton compression;

  private final JButton redComp;

  private final JButton colorCorrection;

  private final JButton levelAdjust;

  private final JButton greenComp;

  private final JButton blueComp;


  private final JButton sharpen;
  private final JButton sepia;
  private final JButton brightness;
  private final JButton loadAndCombine;
  private final JButton saveButton;
  private final ImageProcessingModel model;
  int flag = 0;
  private JPanel editButtonPanel;
  private JButton editButton;
  private JLabel fileSaveDisplay;
  private JTextField input;
  private JTextField commandInput;
  private String command;

  private JToggleButton splitToggle;

  /**
   * This is the construction of view implementation class.
   *
   * @param caption is the caption of the loaded image.
   * @param model   is the model of the enhanced image processing model.
   */
  public ImageProcessingViewImpl(String caption, ImageProcessingModel model) {
    super(caption);
    this.model = model;

    setSize(1000, 1000);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    // Set the background color of the main panel
    mainPanel.setBackground(Color.LIGHT_GRAY);

    // Set the background color of the histogram panel
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //show an image with a scrollbar
    this.imagePanel = new JPanel();
    //a border around the panel with a caption
    this.imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    this.imagePanel.setPreferredSize(new Dimension(400, 300));
    this.imagePanel.setVisible(true);
    imagePanel.setBackground(Color.LIGHT_GRAY);
    mainPanel.add(imagePanel);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.setBackground(Color.LIGHT_GRAY);
    mainPanel.add(dialogBoxesPanel);


    //file save
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout());
    fileSavePanel.setBackground(Color.LIGHT_GRAY);
    dialogBoxesPanel.add(fileSavePanel);
    this.split = new JButton("Revert");
    this.split.setEnabled(false);
    this.saveButton = new JButton("Save a file");
    saveButton.setBackground(Color.WHITE);
    this.saveButton.setActionCommand("Save file");
    fileSavePanel.add(this.saveButton);
    this.loadButton = new JButton("Open a file");
    this.loadButton.setActionCommand("open file");
    loadButton.setBackground(Color.WHITE);
    fileSavePanel.add(this.loadButton);
    fileSavePanel.add(this.split);

    //commands panel
    JPanel commandsPanel = new JPanel();
    setPreferredSize(new Dimension(600, 400));
    setLocation(400, 400);
    //a border around the panel with a caption
    commandsPanel.setBorder(BorderFactory.createTitledBorder("Commands Panel"));
    commandsPanel.setLayout(new GridLayout(2, 0, 10, 10));
    commandsPanel.setBackground(Color.LIGHT_GRAY);
    mainPanel.add(commandsPanel);

    this.verticalFlipButton = new JButton("Vertical Flip");
    this.verticalFlipButton.setActionCommand("vertical flip");
    verticalFlipButton.setBackground(Color.WHITE);
    commandsPanel.add(this.verticalFlipButton);

    this.horizontalFlip = new JButton("Horizontal flip");
    this.horizontalFlip.setActionCommand("horizontal flip");
    this.horizontalFlip.setBackground(Color.WHITE);
    commandsPanel.add(this.horizontalFlip);

    this.redComp = new JButton("Red Comp");
    this.redComp.setActionCommand("red comp");
    this.redComp.setBackground(Color.WHITE);
    commandsPanel.add(this.redComp);

    this.greenComp = new JButton("Green Comp");
    this.greenComp.setActionCommand("green comp");
    this.greenComp.setBackground(Color.WHITE);
    commandsPanel.add(this.greenComp);

    this.blueComp = new JButton("Blue Comp");
    this.blueComp.setActionCommand("Blue comp");
    this.blueComp.setBackground(Color.WHITE);
    commandsPanel.add(this.blueComp);

    this.colorCorrection = new JButton("Color Correction");
    this.colorCorrection.setActionCommand("color-correct");
    this.colorCorrection.setBackground(Color.WHITE);
    commandsPanel.add(this.colorCorrection);

    this.levelAdjust = new JButton("Levels Adjust");
    this.levelAdjust.setActionCommand("levels-adjust");
    this.levelAdjust.setBackground(Color.WHITE);
    commandsPanel.add(this.levelAdjust);

    this.greyScaleButton = new JButton("Greyscale options");
    this.greyScaleButton.setActionCommand("grayscale option");
    this.greyScaleButton.setBackground(Color.WHITE);
    commandsPanel.add(this.greyScaleButton);

    this.blur = new JButton("Blur");
    this.blur.setActionCommand("blur");
    this.blur.setBackground(Color.WHITE);
    commandsPanel.add(this.blur);

    this.compression = new JButton("Compression");
    this.compression.setActionCommand("compression");
    this.compression.setBackground(Color.WHITE);
    commandsPanel.add(this.compression);

    this.sharpen = new JButton("Sharpen");
    this.sharpen.setActionCommand("sharpen");
    this.sharpen.setBackground(Color.WHITE);
    commandsPanel.add(this.sharpen);

    this.sepia = new JButton("Sepia Tone");
    this.sepia.setActionCommand("sepia");
    this.sepia.setBackground(Color.WHITE);
    commandsPanel.add(this.sepia);

    this.brightness = new JButton("Brightness");
    this.brightness.setActionCommand("brightness");
    this.brightness.setBackground(Color.WHITE);
    commandsPanel.add(this.brightness);


    this.loadAndCombine = new JButton("Load and combine");
    this.loadAndCombine.setActionCommand("load combine");
    this.loadAndCombine.setBackground(Color.WHITE);
    commandsPanel.add(this.loadAndCombine);



    //show an image histogram with a scrollbar
    this.histogramPanel = new JPanel();
    histogramPanel.setBackground(Color.LIGHT_GRAY);
    setPreferredSize(new Dimension(600, 400));
    setLocation(400, 400);
    //a border around the panel with a caption
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    this.histogramPanel.setLayout(new GridLayout(1, 0, 10, 10));
    this.histogramPanel.setVisible(true);

    //imagePanel.setMaximumSize(null);
    JScrollPane histogramScrollPane = new JScrollPane(histogramPanel);
    histogramScrollPane.setPreferredSize(new Dimension(1200, 800));
    mainPanel.add(histogramScrollPane);
    pack();
    setVisible(true);
  }


  @Override
  public void addFeatures(Features features) {
    this.loadButton.addActionListener(evt -> {
      features.loadImage(objectName);
    });
    this.saveButton.addActionListener(evt -> {
      features.saveImage();
      JFrame savePopUp = new JFrame("Pop-Up Window");
      JOptionPane.showMessageDialog(savePopUp,
              "Image has been saved!");

    });
    this.verticalFlipButton.addActionListener(evt -> features.verticalFlip());
    this.horizontalFlip.addActionListener(evt -> features.horizontalFlip());
    this.greyScaleButton.addActionListener(evt -> {
      JFrame greyScaleOptions = new JFrame();
      String[] options = {"Intensity", "Value", "Luma", "Red", "Green", "Blue"};
      int input = JOptionPane.showOptionDialog(greyScaleOptions,
              "Choose a greyscale option:", "Greyscale options",
              JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
              null, options, options[0]);
      try {
        selectGreyScale(options[input], features);
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });

    this.blur.addActionListener(evt -> {
      this.split.setEnabled(true);
      JFrame splitPopUp = new JFrame("Pop-Up Window");
      String input = JOptionPane.showInputDialog(splitPopUp,
              "Enter Split value for preview (Optional):");
      double splitPercentage = input.isEmpty() ? 0 : Double.parseDouble(input);
      features.blur(splitPercentage);
      if (splitPercentage == 0) {
        this.saveButton.setEnabled(true);
      } else {
        this.saveButton.setEnabled(false);
      }
    });
    this.redComp.addActionListener(evt -> {
      try {
        features.getRedComponent();
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });


    this.greenComp.addActionListener(evt -> {
      try {
        features.getGreenComponent();
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });

    this.blueComp.addActionListener(evt -> {
      try {
        features.getBlueComponent();
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });

    this.colorCorrection.addActionListener(evt -> {
      try {
        this.split.setEnabled(true);
        JFrame splitPopUp = new JFrame("Pop-Up Window");
        String input = JOptionPane.showInputDialog(splitPopUp,
                "Enter Split value for preview (Optional):");
        double splitPercentage = input.isEmpty() ? 100 : Double.parseDouble(input);
        features.getColorCorrectedImage(splitPercentage);
        if (splitPercentage == 100) {
          this.saveButton.setEnabled(true);
        } else {
          this.saveButton.setEnabled(false);
        }
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });

    this.sharpen.addActionListener(evt -> {
      this.split.setEnabled(true);
      JFrame splitPopUp = new JFrame("Pop-Up Window");
      String input = JOptionPane.showInputDialog(splitPopUp,
              "Enter Split value for preview (Optional):");
      double splitPercentage = input.isEmpty() ? 100 : Double.parseDouble(input);
      features.sharpen(splitPercentage);
      if (splitPercentage == 100) {
        this.saveButton.setEnabled(true);
      } else {
        this.saveButton.setEnabled(false);
      }
    });
    this.sepia.addActionListener(evt -> {
      this.split.setEnabled(true);
      JFrame splitPopUp = new JFrame("Pop-Up Window");
      String input = JOptionPane.showInputDialog(splitPopUp,
              "Enter Split value for preview (Optional):");
      double splitPercentage = input.isEmpty() ? 100 : Double.parseDouble(input);
      features.sepiaTone(splitPercentage);
      if (splitPercentage == 100) {
        this.saveButton.setEnabled(true);
      } else {
        this.saveButton.setEnabled(false);
      }
    });

    this.split.addActionListener(evt -> {
      features.revert();
      this.saveButton.setEnabled(true);
      this.split.setEnabled(false);
    });


    this.brightness.addActionListener(evt -> {
      JFrame brightnessPopUp = new JFrame("Pop-Up Window");
      String input = JOptionPane.showInputDialog(brightnessPopUp,
              "Enter brightness value:");
      int brightnessValue = input.isEmpty() ? 0 : Integer.parseInt(input);
      features.brightness(brightnessValue);
    });

    this.compression.addActionListener(evt -> {
      JFrame brightnessPopUp = new JFrame("Pop-Up Window");
      String input = JOptionPane.showInputDialog(brightnessPopUp,
              "Enter the threshold value:");
      double threshold = input.isEmpty() ? 0 : Double.parseDouble(input);
      features.compression(threshold);

    });

    this.levelAdjust.addActionListener(evt -> {
      this.split.setEnabled(true);
      JFrame levelAdjustPopUp = new JFrame("Pop-Up window B/M/W");
      String inputB = JOptionPane.showInputDialog(levelAdjustPopUp,
              "Enter the B");
      String inputM = JOptionPane.showInputDialog(levelAdjustPopUp,
              "Enter the M");
      String inputW = JOptionPane.showInputDialog(levelAdjustPopUp,
              "Enter the W");
      String splitPercentage = JOptionPane.showInputDialog(levelAdjustPopUp,
              "Enter the split value (is optional)");

      int bValue = inputB.isEmpty() ? 0 : Integer.parseInt(inputB);
      int mValue = inputM.isEmpty() ? 0 : Integer.parseInt(inputM);
      int wValue = inputW.isEmpty() ? 0 : Integer.parseInt(inputW);
      double split = splitPercentage.isEmpty() ? 100 : Double.parseDouble(splitPercentage);
      features.levelsAdjust(bValue, mValue, wValue, split);

      if (split == 100) {
        this.saveButton.setEnabled(true);
      } else {
        this.saveButton.setEnabled(false);
      }
    });

    this.loadAndCombine.addActionListener(evt -> {
      JFrame greyScaleOptions = new JFrame();
      String[] options = {"Red greyscale", "Green greyscale", "Blue greyscale"};
      for (int i = 0; i < options.length; i++) {
        int input = JOptionPane.showOptionDialog(greyScaleOptions,
                "Choose a greyscale option to save:", "Greyscale options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[i]);
        try {
          loadAndCombineImages(options[input], features);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
      }
      features.combineImage();
    });

  }

  private void selectGreyScale(String greyScaleOption,
                               Features features) throws FileNotFoundException {
    switch (greyScaleOption) {
      case "Intensity":
        features.intensityGrayscale();
        break;
      case "Value":
        features.valueGrayscale();
        break;
      case "Luma":
        this.split.setEnabled(true);
        JFrame splitPopUp = new JFrame("Pop-Up Window");
        String input = JOptionPane.showInputDialog(splitPopUp,
                "Enter Split value for preview (Optional):");
        double splitPercentage = input.isEmpty() ? 100 : Double.parseDouble(input);
        features.lumaGrayscale(splitPercentage);
        if (splitPercentage == 100) {
          this.saveButton.setEnabled(true);
        } else {
          this.saveButton.setEnabled(false);
        }
        break;
      case "Red":
        features.redGrayscale();
        break;
      case "Green":
        features.greenGrayscale();
        break;
      case "Blue":
        features.blueGrayscale();
        break;
      default:
        System.out.println("ERROR");
    }

  }

  private void splitAndSaveImages(String option, Features features) throws FileNotFoundException {
    switch (option) {
      case "Red greyscale":
        features.redGrayscale();
        features.saveImage();
        break;
      case "Green greyscale":
        features.greenGrayscale();
        features.saveImage();
        break;
      case "Blue greyscale":
        features.blueGrayscale();
        features.saveImage();
        break;
      default:
        System.out.println("Incorrect Command");
    }
  }

  private void loadAndCombineImages(String option, Features features) throws FileNotFoundException {
    switch (option) {
      case "Red greyscale":
        features.loadImage(objectName);
        features.redGrayscale();
        break;
      case "Green greyscale":
        features.loadImage(objectName);
        features.greenGrayscale();
        break;
      case "Blue greyscale":
        features.loadImage(objectName);
        features.blueGrayscale();
        break;
      default:
        System.out.println("Incorrect Command");
    }
  }

  @Override
  public String load() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "PNG,JPEG,BMP & PPM Images", "jpeg", "png", "ppm", "bmp", "jpg");
    fchooser.setFileFilter(filter);
    int retValue = fchooser.showOpenDialog(ImageProcessingViewImpl.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
  }

  @Override
  public String save() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "PNG,JPEG,BMP & PPM Images", "jpeg", "png", "ppm", "bmp", "jpg");
    fchooser.setFileFilter(filter);
    int retValue = fchooser.showSaveDialog(ImageProcessingViewImpl.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
  }

  @Override
  public String executeCommand() {
    return this.command + " " + this.commandInput.getText()
            + " " + this.input.getText();
  }

  @Override
  public void displayImage(String imageName) {
    try {
      this.imagePanel.removeAll(); // to remove any previous displaying image
      RGB[][] image = model.retrieveImage(imageName);
      BufferedImage newImage = new BufferedImage(image[0].length, image.length,
              BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          int red = image[i][j].getPixel(0);
          int green = image[i][j].getPixel(1);
          int blue = image[i][j].getPixel(2);
          int rgb = (red << 16) | (green << 8) | blue;
          newImage.setRGB(j, i, rgb);
        }
      }
      JLabel picLabel = new JLabel(new ImageIcon(newImage));
      this.imagePanel.add(picLabel);
      this.imagePanel.revalidate();
      this.imagePanel.repaint();
      displayHistogram(imageName);
    } catch (Exception e) {
      //System.out.println(e);
    }
  }

  private void displayHistogram(String imageName) {
    try {
      EnhancedImageProcessingModel model = (EnhancedImageProcessingModel) this.model;
      int[][] histogram = model.fetchHistogram(imageName, "histogram");
      this.histogramPanel.removeAll();
      HistogramPanel histPanel = new HistogramPanel(histogram);
      this.histogramPanel.add(histPanel);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  @Override
  public void displayError(String error) {
    JOptionPane.showMessageDialog(ImageProcessingViewImpl.this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }
}
