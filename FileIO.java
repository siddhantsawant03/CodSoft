package CodSoft;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
    

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FileIO {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Word Count Tool");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JButton fileButton = new JButton("Count Words in File");
            JButton textButton = new JButton("Count Words in Text");

            panel.add(fileButton);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(textButton);

            fileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String filePath = JOptionPane.showInputDialog("Enter file path:");
                    if (validateFilePath(filePath)) {
                        String fileContents = readFile(filePath);
                        processAndShowResults(fileContents);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid file path. Please try again.");
                    }
                }
            });

            textButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = JOptionPane.showInputDialog("Enter text:");
                    if (validateText(text)) {
                        processAndShowResults(text);
                    } else {
                        JOptionPane.showMessageDialog(null, "Text is empty. Please enter valid text.");
                    }
                }
            });

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    public static boolean validateFilePath(String filePath) {
        return new File(filePath).isFile();
    }

    public static boolean validateText(String text) {
        return !text.trim().isEmpty();
    }

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try {
            Scanner sc = new Scanner(new File(filePath));

            while (sc.hasNextLine()) {
                content.append(sc.nextLine()).append("\n");
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    public static void processAndShowResults(String inputString) {
        String[] wordsArray = splitAndCountWords(inputString);
        filterStopWords(wordsArray);
        calculateWordStatistics(wordsArray);
    }

    public static String[] splitAndCountWords(String inputString) {
        return inputString.split("[\\s\\p{Punct}]+");
    }

    public static void filterStopWords(String[] wordsArray) {
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "and", "is", "in", "to", "of", "a"));
        String[] filteredWords = Arrays.stream(wordsArray)
                .filter(word -> !stopWords.contains(word.toLowerCase()))
                .toArray(String[]::new);

        // Print the filtered array of words
        System.out.println("Filtered Words (without stop words):");
        for (String word : filteredWords) {
            System.out.println(word);
        }

        // Print the count of filtered words
        int filteredWordCount = filteredWords.length;
        System.out.println("Filtered Word Count: " + filteredWordCount);
    }

    public static void calculateWordStatistics(String[] wordsArray) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        for (String word : wordsArray) {
            wordFrequencyMap.put(word.toLowerCase(), wordFrequencyMap.getOrDefault(word.toLowerCase(), 0) + 1);
        }

        // Print the number of unique words
        int uniqueWordCount = wordFrequencyMap.size();
        System.out.println("Number of Unique Words: " + uniqueWordCount);

        // Print the word frequencies
        System.out.println("Word Frequencies:");
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Print the total count of words
        int totalCount = wordsArray.length;
        System.out.println("Total Word Count: " + totalCount);
    }
}
