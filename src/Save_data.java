import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save_data {
    private Players[] players;
    private char[][] grid;

    public Save_data() {
        // Grid fictif
        grid = new char[10][11];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '.'; 
            }
        }
        grid[0][0] = 'X';
        grid[9][10] = 'X';

        // Players fictifs
        players = new Players[2];
        players[0] = new Players("didi", (short) 5, (short) 6);
        players[1] = new Players("dede", (short) 6, (short) 6);
    }

    // Save data to a file Bin
    public void save() {
        String cheminFichier = UniqueFileName("joueurs", "bin"); // File name unique generator
        SavePlayersAndGrid(players, grid, cheminFichier); // Save data to a file
    }

    // Save players and grid to a file
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
    
    // File name unique generator
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
