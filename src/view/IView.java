package view;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JComboBox;

/**
 * The view interface represents the view of the program and what the user is seeing while
 * interacting with the program.
 */
public interface IView {

  /**
   * This makes the view visible. This is called after the view is constructed.
   */
  void makeVisible();

  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly.
   *
   * @param error message to be displayed
   */
  void showErrorMessage(String error);

  /**
   * This method makes a message pop up saying that the image was successfully saved.
   *
   * @param message message that the image was saved
   */
  void showSaveMessage(String message);

  /**
   * Signal the view to draw itself.
   */
  void refresh();

  /**
   * This method sets an action listener for the load, execute, and save buttons so that the
   * controller knows what interactions the user is doing to process the action event.
   *
   * @param actionEvent action listener
   */
  void setButtonListener(ActionListener actionEvent);

  /**
   * This getter method returns the image panel from the view.
   *
   * @return image panel
   */
  ImagePanel getImagePanel();

  /**
   * This getter method returns the image panel from the view. It exists so in the controller,
   * we can check which item was selected in the dropdown.
   *
   * @return command options dropdown box
   */
  JComboBox getCommandOptionsBoxFromCommandPanel();

  /**
   * This getter method returns the histogram panel from the view. It's here so the controller
   * can update the histogram in the right JPanel.
   *
   * @return histogram panel
   */
  HistogramPanel getHistogramPanelFrame();

  /**
   * This getter method returns the value the user wants to brighten (or darken) the image by.
   * It's here so the controller knows what value to pass to the Brighten class.
   *
   * @return brighten value
   */
  String getBrightenValueFromCommandPanel();

  /**
   * This getter method returns the path of the image file that was loaded in.. It's here
   * so it can be passed to the controller.
   *
   * @return opened file label
   */
  JLabel getFileOpenDisplayFromCommandPanel();

  /**
   * This getter method returns the command and instructions panel in view.
   *
   * @return command and instructions panel
   */
  CommandandInstructionPanel getCommandAndInstructionPanel();

}
