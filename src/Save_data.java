import java.io.File;

public class Save_data{

    public void initdata(){
        // Create a grid 10x11 fictif
        char[][] grid = new char[10][11];
        grid = new char[10][11];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '.'; 
            }
        }
        grid[0][0] = 'X';
        grid[1][0] = 'X';
        
        // Init 2 players fictifs
        Players[] players = new Players[2];

        players[0] = new Players("didi", (short) 5, (short) 6);
        players[1] = new Players("dede", (short) 6, (short) 6);

        System.out.println("Joueur 1 : " + players[0].GetName() + " X : " + players[0].GetX() + " Y : " + players[0].GetY());
        System.out.println("Joueur 2 : " + players[1].GetName() + " X : " + players[1].GetX() + " Y : " + players[1].GetY());
    }

    public void save(){
        initdata(); // Call the function to initialize the data
        String cheminFichier = UniqueFileName("joueurs", "txt"); // Generate a unique file name
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