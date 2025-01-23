
import java.util.Scanner;

public class Grid {

    public char[][] grid;

    public Grid(){
        grid = new char[10][11];
        initializeGrid();
    }
    public Grid(char[][] gridData) {
        this.grid = gridData;
    }

    /**
     * Function for initialize de grid (btw its useless, we can put this code inside 'public grid' nevermind)
     */
    private void initializeGrid() {

        //Moin d'oppération :
        grid = new char[][] {
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }
        };
        //Bonne vielle époque :
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                grid[i][j] = '.';
//            }
//        }
    }

    /**
     * Fonction pour obtenir la taille de la grille (nombre de lignes).
     * @return nombre de lignes dans la grille.
     */
    public int getSize() {
        return grid.length;
    }

    /**
     * Function for spawn player in the grid
     * @param player
     */
    public void place(Players player) {
        grid[player.getX()][player.getY()] = 'J';
    }

    /**
     * Function for spawn player in the grid
     * @param bot
     */
    public void placeBot(Bot bot) {
        grid[bot.getX()][bot.getY()] = 'B';
    }

    /**
     * function for display the grid in cli
     */
    public void displayGrid(Players[] players) {
        // Define colors for the indices
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";    // Color for numbers
        final String MAGENTA = "\u001B[35m"; // Color for letters

        // Define the margin (spaces before each row)
        final String MARGIN = "                               ";
//        for (short space=0; space<30; space++){
//            System.out.print("\n");
//        }
        System.out.print(MARGIN);
        System.out.print("   "); // Space for the first column
        for (int col = 1; col <= grid[0].length; col++) {
            System.out.printf(CYAN + "%-3d" + RESET, col); // Print numbers with 3-character width
        }
        System.out.println();

        // Print the grid with the left column of letters
        for (int i = 0; i < grid.length; i++) {
            // Print the row letter with margin
            System.out.print(MARGIN);
            char rowLabel = (char) ('a' + i); // 'a', 'b', 'c', ...
            System.out.printf(MAGENTA + "%-3c" + RESET, rowLabel); // Print letter with 3-character width

            // Print the grid row
            for (int j = 0; j < grid[i].length; j++) {
                boolean isPlayerHere = false;

                // Check if a player is on this cell
                for (Players player : players) {
                    if (player.getX() == i && player.getY() == j) {
                        // Print the player's symbol with their color
                        System.out.printf(player.getColor() + "J" + RESET + "  ");
                        isPlayerHere = true;
                        break;
                    }
                }

                if (!isPlayerHere) {
                    // Print the default grid cell
                    System.out.printf("%-3c", grid[i][j]);
                }
            }
            System.out.println(); // Move to the next row
        }
    }
    /*
      * Pseudo code de la fonction destroy
      * Il faut demander au joueur de rentrer des coordonnées (ex : a2)
      * Recup les coordonnées dans un string
      * boucle for pour split le string
      * quand i = 2, on recupere la valeur de x
      *
      */

    public boolean isCellEmpty(int x, int y) {
        // Vérifiez si la case est vide (par exemple, avec '.' pour les cases libres)
        return this.grid[x][y] == '.';
    }


    /**
     * Function for destroy case
     */
    public void destroy(){
    boolean Choice = true;
        //Request the player to enter a coordinates
        Scanner scanner = new Scanner(System.in);
        int CoordXi;
        int CoordYi;
        short CoordXg = 0;
        short CoordYg = 0;
        //As long as Choice is true, we continue
        while (Choice == true){
            
            System.out.print("                 Enter coordinates to destroy boxes like 'a2': ");
            String coords = scanner.next();

            //Split the coordinates
            if (coords.length() == 2){
                //Check if the first character is a letter
                if(Character.isLetter(coords.charAt(0))){
                    //Get the first character
                    char CoordXChar = coords.charAt(0);
                    if(Character.isDigit(coords.charAt(1))){
                        //Get the second character
                        char CoordYChar = coords.charAt(1);

                        //sends one character per variable
                        CoordXi = CoordXChar % 97 ;
                        CoordYi = CoordYChar % 48 ;

                        CoordXg = (short) CoordXi ;
                        CoordYg = (short) CoordYi ;

                    }else{
                        System.err.println("                 Vos coordonnées ne finissent pas par un chiffre");
                    }
                }else{
                    System.err.println("                 Vos coordonnées ne commence pas par une lettre");
                }
            }else{
                System.err.println("                 Chaine de caractère trop longue");
            }

            //Check whether the coordinates are outside the table 
            if (CoordXg >= 0 && CoordXg < grid.length && CoordYg >= 0 && CoordYg < grid[0].length) {
                //Check if the box is empty
                if (grid[CoordXg][CoordYg -1] == '.') {
                    grid[CoordXg][CoordYg -1] = '#'; 
                    System.out.println("                 Box destroyed!");
                    Choice = false; 
                } else {
                    System.out.println("                 The case is occupied ;) Try again!");
                }
            } else {
                System.out.println("                 Coordinates out of bounds :( Try again !");
            }
        }
    }
}
