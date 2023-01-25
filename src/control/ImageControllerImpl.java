package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import control.commands.Blur;
import control.commands.BrightenOrDarken;
import control.commands.ConvertGreyscale;
import control.commands.HorizontalFlip;
import control.commands.Load;
import control.commands.Save;
import control.commands.Sharpen;
import control.commands.TransformGreyscale;
import control.commands.TransformSepia;
import control.commands.VerticalFlip;
import model.ImageCollection;

/**
 * Class for implementation of the controller.
 */
public class ImageControllerImpl implements ImageController {

  private final ImageCollection imageCollection;
  private final Readable readable;

  /**
   * Constructor representing the controller.
   *
   * @param imageCollection the given image.
   * @param readable        given input readable.
   * @throws IllegalArgumentException if null values are present.
   */
  public ImageControllerImpl(ImageCollection imageCollection, Readable readable)
          throws IllegalArgumentException {
    if (imageCollection == null || readable == null) {
      throw new IllegalArgumentException("Image or readable is null.");
    } else {
      this.imageCollection = imageCollection;
      this.readable = readable;
    }
  }


  /**
   * This is the primary method in the controller that
   * communicates with the model to execute actions in the program.
   *
   * @throws IOException if null values are present.
   */
  @Override
  public void execute() throws IOException {
    Scanner scanner = new Scanner(this.readable);

    Map<String, Function<Scanner, ICommand>> allCommands = new HashMap<>();

    allCommands.put("load", (Scanner s) -> new Load(s.next(), s.next(), imageCollection));
    allCommands.put("save", (Scanner s) -> new Save(s.next(), s.next(), imageCollection));
    allCommands.put("vertical-flip", (Scanner s) ->
            new VerticalFlip(s.next(), s.next(), imageCollection));
    allCommands.put("horizontal-flip", (Scanner s) ->
            new HorizontalFlip(s.next(), s.next(), imageCollection));
    allCommands.put("red-component", (Scanner s) ->
            new ConvertGreyscale(s.next(), s.next(), "red", imageCollection));
    allCommands.put("green-component", (Scanner s) ->
            new ConvertGreyscale(s.next(), s.next(), "green", imageCollection));
    allCommands.put("blue-component", (Scanner s) ->
            new ConvertGreyscale(s.next(), s.next(), "blue", imageCollection));
    allCommands.put("luma-component", (Scanner s) ->
            new ConvertGreyscale(s.next(), s.next(), "luma", imageCollection));
    allCommands.put("value-component", (Scanner s) ->
            new ConvertGreyscale(s.next(), s.next(), "value", imageCollection));
    allCommands.put("intensity-component", (Scanner s) ->
            new ConvertGreyscale(s.next(), s.next(), "intensity", imageCollection));
    allCommands.put("brighten", (Scanner s) ->
            new BrightenOrDarken(s.next(), s.next(), s.nextInt(), imageCollection));
    allCommands.put("blur", (Scanner s) -> new Blur(s.next(), s.next(), imageCollection));
    allCommands.put("sharpen", (Scanner s) -> new Sharpen(s.next(), s.next(), imageCollection));
    allCommands.put("greyscale", (Scanner s) ->
            new TransformGreyscale(s.next(), s.next(), imageCollection));
    allCommands.put("sepia", (Scanner s) ->
            new TransformSepia(s.next(), s.next(), imageCollection));

    while (scanner.hasNext()) {
      String in = scanner.next();

      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        break;
      }
      if (in.endsWith("txt")) {

        // check if text file exists
        try {
          scanner = new Scanner(new FileInputStream(in));
        } catch (FileNotFoundException e) {
          System.out.println("Text file " + in + " not found!");
        }
        in = scanner.next();
      }
      Function<Scanner, ICommand> command = allCommands.getOrDefault(in, null);
      if (command == null) {
        System.out.println("Command is nonexistent.");
      } else {
        command.apply(scanner);
      }
    }

    System.out.println("Done");
  }

}
