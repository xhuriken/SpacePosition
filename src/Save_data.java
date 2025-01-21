import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save_data{
    private Players[] players;
    private char[][] grid;

    public Save_data() {
        // Create a grid 10x11 fictif
        grid = new char[10][11];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '.'; 
            }
        }
        grid[0][0] = 'X';

        // Init 2 players fictifs
        players = new Players[2];
        players[0] = new Players("didi", (short) 5, (short) 6);
        players[1] = new Players("dede", (short) 6, (short) 6);

        System.out.println("Joueur 1 : " + players[0].GetName() + " X : " + players[0].GetX() + " Y : " + players[0].GetY());
        System.out.println("Joueur 2 : " + players[1].GetName() + " X : " + players[1].GetX() + " Y : " + players[1].GetY());
    }
    
    public void save(){
        String cheminFichier = UniqueFileName("joueurs", "txt"); // Generate a unique file name
        SavePlayersAndGrid(players, grid, cheminFichier); // Save the data to the file
    }


    public static void SavePlayersAndGrid(Players[] players, char[][] grid, String cheminFichier){
                try (FileWriter writer = new FileWriter(cheminFichier)) {
            StringBuilder sb = new StringBuilder();

            // Add the players to the StringBuilder
            for (Players player : players) {
                sb.append(player.GetName()).append(",").append(player.GetX()).append(",").append(player.GetY()).append("\n");
            }
            // Add the grid to the StringBuilder
            for (char[] row : grid) {
                for (char cell : row) {
                    sb.append(cell);
                }
                sb.append("\n");
            }

            // Write the StringBuilder to the file
            writer.write(sb.toString());

            System.out.println("Données sauvegardées dans le fichier : " + cheminFichier);

        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        } 
    }

    // Function to generate a unique file name by adding a number at the end
    public static String UniqueFileName(String baseName, String extension){
        int compteur = 1;
        File fichier;
        do {
            fichier = new File(baseName + "_" + compteur + "." + extension);
            compteur++;
        // Check if the file already exists and if so, increment the counter
        } while (fichier.exists());
        // Return the unique file name
        return fichier.getName();
    }
}