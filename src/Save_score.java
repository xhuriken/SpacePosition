import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Save_score {

    private static final String SCORE_FILE = "scores.bin"; // File name for storing scores
    private Map<String, Integer> scores = loadScores(); // Initialize and load scores

    // Load scores from a file or create an empty map if file doesn't exist
    private Map<String, Integer> loadScores() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SCORE_FILE))) {
            return (Map<String, Integer>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Log the error and return an empty map if there's an issue with the file
            System.err.println("Error loading scores: " + e.getMessage());
            return new HashMap<>();
        }
    }

    // Save scores to a file
    private void saveScores() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SCORE_FILE))) {
            out.writeObject(scores);
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }

    // Update a player's score and save
    public void updateScore(String playerName, int newScore) {
        scores.put(playerName, scores.getOrDefault(playerName, 0) + newScore);
        saveScores();
    }

    // Get a player's score
    public int getScore(String playerName) {
        return scores.getOrDefault(playerName, 0);
    }

    // Convert scores to a simple array of strings
    public String[][] ScorestoSimpleArray() {
        // Array 2d 
        String[][] scoresArray = new String[scores.size()][2];  
        int index = 0;
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            scoresArray[index][0] = entry.getKey();  // Name
            scoresArray[index][1] = String.valueOf(entry.getValue());  // Score
            index++;
        }
        return scoresArray;
    }

}
