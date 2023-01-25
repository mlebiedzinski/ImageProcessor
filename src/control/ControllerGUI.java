package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Component;
import java.io.File;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.commands.Blur;
import control.commands.BrightenOrDarken;
import control.commands.ConvertGreyscale;
import control.commands.HorizontalFlip;
import control.commands.Save;
import control.commands.Sharpen;
import control.commands.TransformGreyscale;
import control.commands.TransformSepia;
import control.commands.VerticalFlip;
import model.Histogram;
import model.Image;
import model.ImageCollection;
import view.IView;

/**
 * This is the class for the controller that handles the image processing program. It is
 * an asynchronous controller that refers to the view to know the user's interactions
 * and then passes them to the model to make those adjustments to the image or whatever
 * the user wants to do.
 */
public class ControllerGUI implements ActionListener {

  private final ImageCollection imageCollection;
  private final IView view;

  /**
   * This is the constructor for the controller. It takes in the image map and view.
   *
   * @param imageCollection the given image.
   * @param view            given input readable.
   * @throws IllegalArgumentException if null values are present.
   */
  public ControllerGUI(ImageCollection imageCollection, IView view)
          throws IllegalArgumentException {
    if (imageCollection == null || view == null) {
      throw new IllegalArgumentException("Image or readable is null.");
    } else {
      this.imageCollection = imageCollection;
      this.view = view;
    }
  }

  /**
   * This method sets the button listener as the controller and makes the view visible.
   */
  public void reSetup() {
    this.view.setButtonListener(this);
    this.view.makeVisible();
  }

  /**
   * This method processes the operation that was selected by the user and performs the operation
   * on the image, then updates the image in the view.
   *
   * @param commandParameters command map (key is "command", value is the actual command)
   */
  public void processCommand(HashMap<String, String> commandParameters) {

    String imgName = "Loaded image";
    String cmd = commandParameters.get("command");

    switch (cmd) {
      case "red-component":
        new ConvertGreyscale(imgName, imgName, "red", this.imageCollection);
        break;
      case "green-component":
        new ConvertGreyscale(imgName, imgName, "green", this.imageCollection);
        break;
      case "blue-component":
        new ConvertGreyscale(imgName, imgName, "blue", this.imageCollection);
        break;
      case "intensity-component":
        new ConvertGreyscale(imgName, imgName, "intensity", this.imageCollection);
        break;
      case "luma-component":
        new ConvertGreyscale(imgName, imgName, "luma", this.imageCollection);
        break;
      case "value-component":
        new ConvertGreyscale(imgName, imgName, "value", this.imageCollection);
        break;
      case "vertical-flip":
        new VerticalFlip(imgName, imgName, this.imageCollection);
        break;
      case "horizontal-flip":
        new HorizontalFlip(imgName, imgName, this.imageCollection);
        break;
      case "brighten":
        try {
          int increment = Integer.parseInt(commandParameters.get("increment"));
          new BrightenOrDarken(imgName, imgName, increment, this.imageCollection);
        } catch (IllegalArgumentException a) {
          this.view.showErrorMessage("For the brighten feature, please enter a positive or "
                  + "negative number.");
        }
        break;
      case "blur":
        new Blur(imgName, imgName, this.imageCollection);
        break;
      case "sharpen":
        new Sharpen(imgName, imgName, this.imageCollection);
        break;
      case "greyscale":
        new TransformGreyscale(imgName, imgName, this.imageCollection);
        break;
      case "sepia":
        new TransformSepia(imgName, imgName, this.imageCollection);
        break;
      default:
        break;
    }
    BufferedImage buffImage = this.imageCollection.getHashMap().get(imgName).toBufferedImage();

    this.view.getImagePanel().updateImage(buffImage);

  }

  /**
   * This method checks what the action event is and reacts accordingly; it handles opening
   * a file, saving a file, and when the user clicks execute to modify an image.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    if (action.equals("Open file")) {

      final JFileChooser fchooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG, PNG, PPM, BMP Images", "jpg", "png", "ppm", "bmp");
      fchooser.setFileFilter(filter);
      int retvalue = fchooser.showOpenDialog((Component) view);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        this.view.getFileOpenDisplayFromCommandPanel().setText(f.getAbsolutePath());

        Image imageLoaded = new Image(f.getAbsolutePath());

        this.imageCollection.getHashMap().put("Loaded image", imageLoaded);
        this.view.getHistogramPanelFrame().updateHistogram(new Histogram(this.imageCollection.
                getHashMap().get("Loaded image")));
        this.view.getImagePanel().updateImage(imageLoaded.toBufferedImage());
      }
    }

    if (action.equals("Save File")) {
      if (!(this.imageCollection.getHashMap().containsKey("Loaded image"))) {
        this.view.showErrorMessage("Please load in an image first.");
      } else {
        final JFileChooser fileChooser = new JFileChooser(".");
        int retvalue = fileChooser.showSaveDialog((Component) view);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, PNG, PPM, BMP Images", "jpg", "png", "ppm", "bmp");
        fileChooser.setFileFilter(filter);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          try {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            Save save = new Save(fileName, "First loaded image", this.imageCollection);
            this.view.showSaveMessage("Your image was successfully saved!");
          } catch (NullPointerException nullE) {
            this.view.showErrorMessage("Image must be saved as a JPG, PNG, PPM, or BMP.");
          }
        }
      }
    }

    if (action.equals("execute")) {
      if (!(this.imageCollection.getHashMap().containsKey("Loaded image"))) {
        this.view.showErrorMessage("Please load in an image first.");
      } else {

        HashMap<String, String> commandParameterMap = new HashMap<>();
        commandParameterMap.put("command", view.getCommandOptionsBoxFromCommandPanel().
                getSelectedItem().toString());
        commandParameterMap.put("increment", view.getBrightenValueFromCommandPanel());

        try {
          this.processCommand(commandParameterMap);
          this.view.getHistogramPanelFrame().updateHistogram(new Histogram(this.imageCollection.
                  getHashMap().get("Loaded image")));
        } catch (Exception ex) {
          view.showErrorMessage(ex.getMessage());
        }
      }
    }

    this.view.refresh();
  }

}

