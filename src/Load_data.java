import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load_data {

    public static void Load_data() {

        String file = "joueurs_4.bin";  // Path to the binary file

        Players[] players = null; // Init players null
        char[][] grid = null; // Init grid null

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream dataIn = new ObjectInputStream(fileIn)) {

            // Deserialize players and grid from the file
            players = (Players[]) dataIn.readObject();
            grid = (char[][]) dataIn.readObject();

            // Print players' data
            System.out.println("Les joueurs chargés du fichier :");
            for (Players player : players) {
                System.out.println(player.getName() + ", X: " + player.getX() + ", Y: " + player.getY());
            }

            // Print grid data
            System.out.println("La grille chargée du fichier :");
            for (char[] row : grid) {
                for (char cell : row) {
                    System.out.print(cell);
                }
                System.out.println();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur veuillez recommencer ! " + e.getMessage());
        }
    }
}