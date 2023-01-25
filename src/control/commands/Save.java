package control.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.ICommand;
import model.Image;
import model.ImageCollection;

/**
 * This is a class for the save command. It implements ICommand.
 * It saves the image with the given name to the specified path which should include the
 * name of the file.
 */
public class Save implements ICommand {

  /**
   * This is the constructor for save. It creates a new file in the user's computer, depending on
   * what the image file format is.
   *
   * @param imgPath  name of new file where the image will go
   * @param imgName  name of the image in the map
   * @param imageMap map of images
   */
  public Save(String imgPath, String imgName, ImageCollection imageMap) {
    String last3 = "";

    if (imgPath.length() >= 3) {
      last3 = imgPath.substring(imgPath.length() - 3);
      if (last3.equals("peg")) {
        last3 = "jpeg";
      }
    }

    if (last3.equals("ppm")) {
      try {
        File newFile = new File(imgPath);
        FileWriter myWriter = new FileWriter(newFile);
        myWriter.write(imageMap.getHashMap().get(imgName).imageToString(imgPath));
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
      }
    } else {

      try {
        Image image = imageMap.getHashMap().get(imgName);

        if (image == null) {
          System.out.println("Specified image doesn't exist.");
          return;
        }
        BufferedImage buff = image.toBufferedImage();

        File outFile = new File(imgPath);
        ImageIO.write(buff, last3, outFile);
        System.out.println("Successfully wrote to the file.");

      } catch (IOException e) {
        System.out.println("An error occurred.");

      }
    }
    System.out.println("File saved!");
  }
}
