import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ScoreManager {

    private static final String SCORE_FILE = "scores.bin"; // File name for storing scores
    private Map<String, Integer> scores = loadScores(); // Initialize and load scores
    // Load scores from a file or create an empty map if file doesn't exist

    // Integer is object type, so it can be null, so we use getOrDefault to avoid null pointer exception
    private Map<String, Integer> loadScores() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SCORE_FILE))) {
            // Map<String, Integer> is the type of object we are reading from the file
            return (Map<String, Integer>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file doesn't exist or there is an error reading the file, return an empty map
            return new HashMap<>();
        }
    }

    // Save scores to a file
    private void saveScores() {
        // Save scores to a file using try-with-resources to ensure the file is closed
        // ObjectOutputStream is used to write objects to a file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SCORE_FILE))) {
            out.writeObject(scores);
        } catch (IOException e) {
            System.out.println("Error saving scores: " + e.getMessage());
        }
    }

    // Update a player's score and save
    public void updateScore(String playerName, int newScore) {
        // Add new score to the existing score or create a new entry to default value of 0
        scores.put(playerName, scores.getOrDefault(playerName, 0) + newScore);
        saveScores();
    }

    // Get a player's score
    public int getScore(String playerName) {
        return scores.getOrDefault(playerName, 0);
    }

    // Display all scores
    public void displayAllScores() {
        scores.forEach((name, score) -> System.out.println(name + ": " + score));
    }
}
