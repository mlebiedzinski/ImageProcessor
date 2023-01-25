package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * This is the class for the program's graphical user interface. Through this, the user can
 * interactively load, process and save images.
 */
public class ImageGUI extends JFrame implements IView {

  private final ImagePanel imagePanel;
  private final HistogramPanel histogramPanelFrame;

  private final CommandandInstructionPanel commandandInstructionPanel;

  /**
   * This is the constructor for ImageGUI. It initializes all of the panels and creates each
   * component of the interface.
   */
  public ImageGUI() {
    super();
    this.setTitle("Image Processor!");
    this.setPreferredSize(new Dimension(1800, 780));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // the panel where the image will appear
    this.imagePanel = new ImagePanel();
    JScrollPane imageScrollFrame = new JScrollPane(this.imagePanel);
    imageScrollFrame.setPreferredSize(new Dimension(1800, 780));
    imageScrollFrame.createHorizontalScrollBar();
    imageScrollFrame.createVerticalScrollBar();
    this.add(imageScrollFrame, BorderLayout.CENTER);

    // -------------------

    // the section where the histogram and key appear

    JPanel histogramPanelSection = new JPanel();
    histogramPanelSection.setPreferredSize(new Dimension(500, 780));
    histogramPanelSection.setLayout(new BoxLayout(histogramPanelSection,
            BoxLayout.Y_AXIS));

    this.histogramPanelFrame = new HistogramPanel();

    JPanel histogramKeyPanel = new JPanel();
    histogramKeyPanel.setBorder(BorderFactory.createTitledBorder("Histogram Key"));
    histogramKeyPanel.setBackground(new Color(255, 251, 209));
    histogramKeyPanel.setPreferredSize(new Dimension(500, 140));

    HistogramKey histogramKeyFrame = new HistogramKey();
    histogramKeyPanel.add(histogramKeyFrame);
    histogramPanelSection.add(this.histogramPanelFrame);
    histogramPanelSection.add(histogramKeyPanel);
    this.add(histogramPanelSection, BorderLayout.EAST);

    //-------

    this.commandandInstructionPanel = new CommandandInstructionPanel();
    this.add(commandandInstructionPanel, BorderLayout.SOUTH);

    this.pack();
  }

  /**
   * This method sets an action listener for the load, execute, and save buttons so that the
   * controller knows what interactions the user is doing to process the action event.
   *
   * @param actionEvent action listener
   */
  public void setButtonListener(ActionListener actionEvent) {
    this.commandandInstructionPanel.getLoadButton().addActionListener(actionEvent);
    this.commandandInstructionPanel.getExecuteButton().addActionListener(actionEvent);
    this.commandandInstructionPanel.getSaveButton().addActionListener(actionEvent);
  }

  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly.
   *
   * @param error message to be displayed
   */
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * This method makes a message pop up saying that the image was successfully saved.
   *
   * @param message message that the image was saved
   */
  public void showSaveMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Message",
            JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Signal the view to draw itself.
   */
  public void refresh() {
    this.repaint();
  }

  /**
   * This getter method returns the histogram panel from the view. It's here so the controller
   * can update the histogram in the right JPanel.
   *
   * @return histogram panel
   */
  public HistogramPanel getHistogramPanelFrame() {
    return this.histogramPanelFrame;
  }

  /**
   * This getter method returns the image panel from the view.
   *
   * @return image panel
   */
  public ImagePanel getImagePanel() {
    return this.imagePanel;
  }

  /**
   * This getter method returns the command panel dropdown from the view. It exists so in the
   * controller, we can check which item was selected in the dropdown.
   *
   * @return command options dropdown box panel
   */
  public JComboBox getCommandOptionsBoxFromCommandPanel() {
    return this.commandandInstructionPanel.getCommandOptionsBox();
  }

  /**
   * This getter method returns the value the user wants to brighten (or darken) the image by.
   * It's here so the controller knows what value to pass to the Brighten class.
   *
   * @return brighten value
   */
  public String getBrightenValueFromCommandPanel() {
    return this.commandandInstructionPanel.getBrightenValue();
  }


  /**
   * This getter method returns the path of the image file that was loaded in. It's here
   * so it can be passed to the controller.
   *
   * @return opened file label
   */
  public JLabel getFileOpenDisplayFromCommandPanel() {
    return this.commandandInstructionPanel.getFileOpenDisplay();
  }


  /**
   * This getter method returns the command and instructions panel in view.
   *
   * @return command and instructions panel
   */
  public CommandandInstructionPanel getCommandAndInstructionPanel() {
    return this.commandandInstructionPanel;
  }

}
