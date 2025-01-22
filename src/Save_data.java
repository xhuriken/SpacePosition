import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save_data {
     
 

    private Players[] players;
    private char[][] grid;

    public Save_data(Players[] players, char[][] grid) {
        this.players = players;

        this.grid = grid ;
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
