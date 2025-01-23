/**
 * Load_data - A class to load players and grid data from binary files.
 */
import java.io.*;

public class Load_data {
    private static Players[] players = null; // Init players null
    private static char[][] grid = null; // Init grid null

    public static void Load_data(String choise) {
        players = null; // Reset players 

        String file = "joueurs_" + choise + ".bin"; // File name to load

        File fichier = new File(file);
        if (!fichier.exists()) {
            System.out.println("Erreur : le fichier " + file + " n'existe pas.");
            return;
        }

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream dataIn = new ObjectInputStream(fileIn)) {

            // Deserialize players and grid from the file
            players = (Players[]) dataIn.readObject();
            grid = (char[][])dataIn.readObject();
            

        } catch (IOException e) {
            System.out.println("File not find" + file);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("");
            e.printStackTrace();
        }
    }

    public static Players[] Getplayersload() {
        if (players == null) {
            System.out.println("Aucun joueur n'a été chargé.");
        }
        return players;
    }

    public static char[][] Getgridload() {
        if (grid == null) {
            System.out.println("Aucune grille n'a été chargée.");
        }
        return grid;
    }
}