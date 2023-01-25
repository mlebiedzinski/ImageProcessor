package view;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

/**
 * This class creates the bottom section below the image that includes command
 * panel buttons and instructions for how to use the program.
 */
public class CommandandInstructionPanel extends JPanel {

  private final JTextField brightenValue;

  private final JButton executeButton;
  private final JButton loadButton;
  private final JButton saveButton;
  private final JComboBox commandOptionsDropdown;
  private final JLabel fileOpenDisplay;


  /**
   * This is the constructor for CommandandInstructionPanel. It initializes the panels and
   * buttons in the panel as well as the instruction text box.
   */
  public CommandandInstructionPanel() {

    this.setBackground(Color.LIGHT_GRAY);
    this.setPreferredSize(new Dimension(800, 220));
    this.setLayout(new BoxLayout(this,
            BoxLayout.Y_AXIS));
    this.setBorder(BorderFactory.createTitledBorder("Command Selection Panel"));

    // the panel that contains the load, save, and quit buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.lightGray);
    buttonPanel.setPreferredSize(new Dimension(800, 40));

    // load button to trigger loading in an image
    loadButton = new JButton("Load Image");
    loadButton.setActionCommand("load");

    // for opening button
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    //    imagePanel.add(fileopenPanel);
    loadButton.setActionCommand("Open file");
    fileopenPanel.add(loadButton);

    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    buttonPanel.add(loadButton);
    buttonPanel.add(fileOpenDisplay);

    this.add(fileopenPanel);

    // save button
    saveButton = new JButton("Save Image");
    saveButton.setActionCommand("Save File");
    buttonPanel.add(saveButton);

    // quit button
    JButton quitButton = new JButton("Quit Program");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton, BorderLayout.EAST);

    this.add(buttonPanel, BorderLayout.SOUTH);

    // the panel that contains all the commands options and features
    JPanel commandSelectionPanel = new JPanel();
    commandSelectionPanel.setBackground(Color.LIGHT_GRAY);
    commandSelectionPanel.setPreferredSize(new Dimension(800, 70));
    commandSelectionPanel.setBorder(BorderFactory.createTitledBorder("Operation Selection"));
    this.add(commandSelectionPanel);

    // instruction text : "choose command:"
    JLabel chooseCommand = new JLabel();
    chooseCommand.setText("Choose Operation: ");
    commandSelectionPanel.add(chooseCommand);

    // combo box drop down for command options
    ArrayList<String> commandOptionList = new ArrayList<String>(
            Arrays.asList("red-component", "green-component", "blue-component",
                    "intensity-component", "luma-component", "value-component", "vertical-flip",
                    "horizontal-flip", "sharpen", "blur", "greyscale", "sepia", "brighten"));
    commandOptionsDropdown = new JComboBox<String>();
    for (int i = 0; i < commandOptionList.size(); i++) {
      commandOptionsDropdown.addItem(commandOptionList.get(i));
    }

    this.add(fileopenPanel);

    commandSelectionPanel.add(commandOptionsDropdown);

    // instruction text : "Brighten Increment Value:"
    JLabel brightenText = new JLabel();
    brightenText.setText("Brighten Value:");
    commandSelectionPanel.add(brightenText);

    // text box for the brighten increment value
    this.brightenValue = new JTextField(5);
    this.brightenValue.setText("n/a");
    this.brightenValue.setEditable(false);

    // calls adjustBrightenTextLock to lock/unlock brightenValue text box
    commandOptionsDropdown.addActionListener((ActionEvent e) -> {
      String chosenOption = (String) commandOptionsDropdown.getSelectedItem();
      assert chosenOption != null;
      adjustBrightenTextLock(chosenOption);
    });
    commandSelectionPanel.add(brightenValue);

    // execute button to trigger the execution of the command
    this.executeButton = new JButton("Execute");
    this.executeButton.setActionCommand("execute");
    commandSelectionPanel.add(this.executeButton);

    JPanel instructionsPanel = new JPanel();
    instructionsPanel.setBackground(Color.WHITE);
    instructionsPanel.setPreferredSize(new Dimension(1400, 210));

    instructionsPanel.setBorder(BorderFactory.createTitledBorder("Instructions"));

    JLabel instructionsLabel = new JLabel();
    instructionsLabel.setText("<html>Welcome to our image processing program!<br/>" +
            "First, load an image from your computer with the Load button.<br/>" +
            "Then, select an operation to perform on your image. You can perform " +
            "operations like converting or transforming to greyscale, sepia, flipping " +
            "horizontally and vertically,<br/>blurring and sharpening, brightening, etc. " +
            "Note: for brighten, you can either brighten or darken the image depending on " +
            "if the value you enter is negative or positive.<br/>" +
            "You can perform multiple operations on an image. For blur and sharpen, you can" +
            "apply those operations to make the image as blurry or sharpened as you want.<br/>" +
            "If you'd like to save the image you're working on, click the Save button.<br/>" +
            "On the right hand side, you can view the histogram of the distribution of RGB & " +
            "intensity values with their frequencies in the image you're currently working on." +
            "<br/>If you'd like to work on a different image, load a new one.<br/>" +
            "Have fun! :)<html>");

    instructionsPanel.add(instructionsLabel);

    JScrollPane instructionsScrollFrame = new JScrollPane(instructionsPanel);

    this.add(instructionsScrollFrame, BorderLayout.SOUTH);
  }

  /**
   * This private helper method checks whether the user selected brighten; if so, it unlocks the
   * text box that the user types the increment/decrement in. If not, the box stays locked.
   *
   * @param chosenOption option from the operation dropdown
   */
  private void adjustBrightenTextLock(String chosenOption) {
    if (chosenOption.equals("brighten")) {
      this.brightenValue.setText("");
      this.brightenValue.setEditable(true);
    } else {
      this.brightenValue.setText("n/a");
      this.brightenValue.setEditable(false);
    }
  }

  /**
   * This getter method returns the path of the image file that was loaded in. It's here
   * so it can be passed to the controller that then loads the image and displays the file path.
   *
   * @return opened file label
   */
  public JLabel getFileOpenDisplay() {
    return this.fileOpenDisplay;
  }

  /**
   * This getter method returns the command panel dropdown box , so it can be passed to the
   * ImageGUI class which then passes it to the controller to see which option was selected.
   *
   * @return command panel dropdown box
   */
  public JComboBox getCommandOptionsBox() {
    return this.commandOptionsDropdown;
  }

  /**
   * This getter method returns the brighten value in the panel, so it can be passed to the
   * ImageGUI class which then can pass it to the controller so it knows what value to
   * brighten the image by.
   *
   * @return brightening value
   */
  public String getBrightenValue() {
    return this.brightenValue.getText();
  }

  /**
   * This getter method returns the load button, so it can be passed to the ImageGUI class
   * which then adds an action listener to the button.
   *
   * @return load button
   */
  public JButton getLoadButton() {
    return this.loadButton;
  }

  /**
   * This getter method returns the execute button, so it can be passed to the ImageGUI class
   * which then adds an action listener to the button.
   *
   * @return execute button
   */
  public JButton getExecuteButton() {
    return this.executeButton;
  }

  /**
   * This getter method returns the save button, so it can be passed to the ImageGUI class
   * which then adds an action listener to the button.
   *
   * @return save button
   */
  public JButton getSaveButton() {
    return this.saveButton;
  }

}


