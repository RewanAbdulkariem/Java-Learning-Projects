package Java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCounterGUI {
    public WordCounterGUI(){
        JFrame frame = new JFrame("WordsCounter");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel paragraphLabel = new JLabel("Paragraph");
        paragraphLabel.setBounds(10, 10, 70, 50);
        frame.add(paragraphLabel);

        JTextArea paragraphContainer = new JTextArea();
        paragraphContainer.setBounds(10, 60, 450, 200);
        paragraphContainer.setLineWrap(true);
        paragraphContainer.setWrapStyleWord(true);
        frame.add(paragraphContainer);

        JButton counterButton = new JButton("Count");
        counterButton.setBounds(200,270,100,50);
        frame.add(counterButton);

        JLabel countLabel = new JLabel();
        countLabel.setBounds(10, 350, 500, 100);
        frame.add(countLabel);

        counterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paragraph = paragraphContainer.getText();
                int wordCount = TextAnalyzer.countWords(paragraph);
                int letterCount = TextAnalyzer.countLetters(paragraph);
                int paragraphCount = TextAnalyzer.countParagraphs(paragraph);

                String text = "<html>Words count: " + wordCount + "<br>" +
                        "Letter count: " + letterCount + "<br>" +
                        "Paragraph count: " + paragraphCount + "</html>";

                countLabel.setText(text);
            }
        }
        );

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new WordCounterGUI();
    }
}
