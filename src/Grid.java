import java.util.Random;
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
     * Function to place a bot randomly on the grid.
     */
    public void placeBotOnGrid() {
        Random random = new Random();
        int rows = grid.length;
        int cols = grid[0].length;

        int x, y;
        boolean placed = false;

        while (!placed) {
            // Generate random coordinates
            x = random.nextInt(rows);
            y = random.nextInt(cols);

            // Check if the cell is empty
            if (isCellEmpty(x, y)) {
                grid[x][y] = 'B'; // Place the bot on the grid
                placed = true;
            }
        }
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

    public boolean isCellEmpty(int x, int y) {
        // Vérifiez si la case est vide (par exemple, avec '.' pour les cases libres)
        return this.grid[x][y] == '.';
    }

    /*
     * Pseudo code de la fonction destroy
     * Il faut demander au joueur de rentrer des coordonnées (ex : a2)
     * Recup les coordonnées dans un string
     * boucle for pour split le string
     * quand i = 2, on recupere la valeur de x
     *
     */
    /**
     * Function for destroy case
     */
    public void destroy(){
    boolean Choice = true;
        //Request the player to enter a coordinates
        Scanner scanner = new Scanner(System.in);
        int CoordXi;
        int CoordYi;
        int CoordZi;
        short CoordXg = 0;
        short CoordYg = 0;

        //As long as Choice is true, we continue
        while (Choice){
            
            System.out.print("                 Enter coordinates to destroy boxes like 'a2': ");
            String coords = scanner.next();

            if (coords.length() <= 3) {
                // Check if the first character is a letter
                if (Character.isLetter(coords.charAt(0))) {
                    // Get the first character
                    char CoordXChar = coords.charAt(0);
                    // Check if the second character is a digit
                    if (Character.isDigit(coords.charAt(1))) {
                        // If the string has a third character
                        if (coords.length() == 3) {
                            // Check if the third character is also a digit
                            if (Character.isDigit(coords.charAt(2))) {
                                char CoordZChar = coords.charAt(2);

                                // Process the characters as integers
                                CoordXi = CoordXChar % 97;
                                CoordYi = coords.charAt(1) % 48;
                                CoordZi = coords.charAt(2) % 48;

                                CoordXg = (short) CoordXi;
                                CoordYg = (short) (CoordYi + CoordZi);


                                System.err.println(CoordXg);
                                System.err.println(CoordYg);

                            } else {
                                System.err.println("The third character is not a digit");
                            }
                        } else {
                            // If the string contains only two characters
                            CoordXi = CoordXChar % 97;
                            CoordYi = coords.charAt(1) % 48;

                            CoordXg = (short) CoordXi;
                            CoordYg = (short) CoordYi;

                            System.err.println(CoordXg);
                            System.err.println(CoordYg);
                        }
                    } else {
                        System.err.println("The second character is not a digit");
                    }
                } else {
                    System.err.println("The first character is not a letter");
                }
            } else {
                System.err.println("The string is too long");
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
