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
                Main.currentBot.setPosition(x, y);
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
    public void destroy() {
        boolean choice = true; // Continue looping until a box is destroyed
        short[] coordinates = new short[2]; // Array to hold X and Y coordinates

        while (choice) {
            // Validate and update coordinates using ValidatorCo
            validateCord(coordinates);

            // Extract X and Y coordinates from the array
            short CoordXg = coordinates[0];
            short CoordYg = coordinates[1];

            // Check if the coordinates are within grid boundaries
            if (CoordXg >= 0 && CoordXg < grid.length && CoordYg > 0 && CoordYg <= grid[0].length) {
                // Check if the target cell is empty ('.')
                if (grid[CoordXg][CoordYg - 1] == '.') {
                    grid[CoordXg][CoordYg - 1] = '#'; // Mark the cell as destroyed
                    System.out.println("                 Box destroyed!");
                    choice = false; // Exit the loop after success
                } else {
                    System.out.println("                 The case is occupied. Try again!");
                }
            } else {
                System.out.println("                 Coordinates out of bounds. Try again!");
            }
        }
    }

    /**
     * Function to validate coordinates entered by the user.
     * Updates the coordinates `CoordXg` and `CoordYg` via an array passed by reference.
     */
    public void validateCord(short[] coordinates) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        while (true) { // Loop until valid input is provided
            System.out.print("                 Enter coordinates to destroy boxes like 'a2': ");
            String coords = scanner.next();

            // Ensure input is at least 2 characters long
            if (coords.length() < 2) {
                System.err.println("                 Invalid input! Coordinates must be at least two characters (e.g., 'a2').");
                continue; // Ask again
            }

            if (coords.length() <= 3) {
                // Check if the first character is a letter
                if (Character.isLetter(coords.charAt(0))) {
                    char CoordXChar = coords.charAt(0);

                    // Check if the second character is a digit
                    if (Character.isDigit(coords.charAt(1))) {
                        int CoordXi = CoordXChar % 97; // Convert 'a' to index 0
                        int CoordYi;

                        if (coords.length() == 3 && Character.isDigit(coords.charAt(2))) {
                            // Coordinates with three characters
                            int CoordZi = coords.charAt(2) % 48;
                            CoordYi = (coords.charAt(1) % 48) * 10 + CoordZi;
                        } else {
                            // Coordinates with two characters
                            CoordYi = coords.charAt(1) % 48;
                        }

                        // Validate that coordinates are within bounds
                        if (CoordXi >= 0 && CoordXi < 12 && CoordYi >= 0 && CoordYi < 12) {
                            coordinates[0] = (short) CoordXi;
                            coordinates[1] = (short) CoordYi;

                            System.out.println("                 Valid coordinates: X=" + coordinates[0] + ", Y=" + coordinates[1]);
                            break; // Exit loop with valid input
                        } else {
                            System.err.println("                 Coordinates must be between 0 and 11 for both X and Y.");
                        }
                    } else {
                        System.err.println("                 The second character is not a digit.");
                    }
                } else {
                    System.err.println("                 The first character is not a letter.");
                }
            } else {
                System.err.println("                 The input is too long. Use a format like 'a2' or 'a11'.");
            }
        }
    }
}
