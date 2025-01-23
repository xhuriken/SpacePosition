import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Save_data - A class to save players and grid data to binary files.
 * 
 * Important Class:
 * This class is responsible for saving serialized player and grid data to unique binary files.
 * It provides methods to save the data and generate unique file names.
 */
public class Save_data {

    private Players[] players;
    private char[][] grid;

    /**
     * Constructor to initialize players and grid.
     * 
     * @param players Array of Player objects to be saved.
     * @param grid 2D char array representing the grid to be saved.
     */
    public Save_data(Players[] players, char[][] grid) {
        this.players = players;
        this.grid = grid;
    }

    /**
     * Save the players and grid data to a binary file with a unique name.
     */
    public void save() {
        String cheminFichier = UniqueFileName("joueurs", "bin"); // File name unique generator
        SavePlayersAndGrid(players, grid, cheminFichier); // Save data to a file
    }

    /**
     * Save players and grid to a specified file.
     * 
     * @param players Array of Player objects to be saved.
     * @param grid 2D char array representing the grid to be saved.
     * @param cheminFichier The path of the file where data will be saved.
     */
    public static void SavePlayersAndGrid(Players[] players, char[][] grid, String cheminFichier) {
        try (FileOutputStream fileOut = new FileOutputStream(cheminFichier);
             ObjectOutputStream data = new ObjectOutputStream(fileOut)) {
            // Save players
            data.writeObject(players);
            // Save grid
            data.writeObject(grid);

            System.out.println("Données sauvegardées dans le fichier : " + cheminFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
    
    /**
     * Generate a unique file name based on the base name and extension.
     * 
     * @param baseName The base name for the file.
     * @param extension The file extension.
     * @return A unique file name with the given base name and extension.
     */
    public static String UniqueFileName(String baseName, String extension) {
        int compteur = 1;
        File fichier;
        do {
            fichier = new File(baseName + "_" + compteur + "." + extension);
            compteur++;
        } while (fichier.exists()); // if file exists, increment counter and try again
        return fichier.getName(); // return the unique file name
    }
}