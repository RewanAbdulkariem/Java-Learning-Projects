package Java;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCounterGUI {

    // Constructor to create the GUI
    public WordCounterGUI() {
        // Create the main frame for the GUI
        JFrame frame = new JFrame("WordsCounter");
        frame.setSize(500, 500); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit
        frame.setLayout(null); // Use absolute positioning

        // Create and add a label for the paragraph input
        JLabel paragraphLabel = new JLabel("Paragraph");
        paragraphLabel.setBounds(10, 10, 70, 50); // Position and size
        frame.add(paragraphLabel);

        // Create a text area for user to enter the paragraph
        JTextArea paragraphContainer = new JTextArea();
        paragraphContainer.setBounds(10, 60, 450, 200); // Position and size
        paragraphContainer.setLineWrap(true); // Wrap lines automatically
        paragraphContainer.setWrapStyleWord(true); // Wrap by words, not characters

        // Add a scroll pane to the text area in case the text is long
        JScrollPane scrollPane = new JScrollPane(paragraphContainer);
        scrollPane.setBounds(10, 60, 450, 200); // Position and size
        frame.add(scrollPane);

        // Create a button to trigger the word count
        JButton counterButton = new JButton("Count");
        counterButton.setBounds(200, 270, 100, 50); // Position and size
        frame.add(counterButton);

        // Label to display the results (words, letters, paragraphs)
        JLabel countLabel = new JLabel();
        countLabel.setBounds(10, 350, 500, 100); // Position and size
        frame.add(countLabel);

        // Add action listener to the button to perform counting when clicked
        counterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from the text area
                String paragraph = paragraphContainer.getText();

                // Call methods from TextAnalyzer class to count words, letters, and paragraphs
                int wordCount = TextAnalyzer.countWords(paragraph);
                int letterCount = TextAnalyzer.countLetters(paragraph);
                int paragraphCount = TextAnalyzer.countParagraphs(paragraph);

                // Prepare HTML formatted text to display counts in multiple lines
                String text = "<html>Words count: " + wordCount + "<br>" +
                        "Letter count: " + letterCount + "<br>" +
                        "Paragraph count: " + paragraphCount + "</html>";

                // Set the result text in the countLabel
                countLabel.setText(text);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        new WordCounterGUI(); // Create an instance of the GUI
    }
}
