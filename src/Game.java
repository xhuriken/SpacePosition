import java.util.Scanner;

public class Game {

    private Grid grid;
    private Players[] players;
    private Bot bot;
    private byte nbPlayer;
    private char[][] gridload;

    public Game(byte nbPlayer, boolean isGameLoaded) {
        // if game is loaded, load the grid and players
        if (isGameLoaded) {
            this.gridload = Load_data.Getgridload();
            this.grid = new Grid(gridload);
            InitializePlayer(true);
            // Create a new game with a new grid and players
        } else {
            this.grid = new Grid();
            this.nbPlayer = nbPlayer;
            this.players = new Players[nbPlayer];
            this.bot = new Bot();
            InitializePlayer(false);
        }
    }

    /**
     * Function to initialize the players according to the number of players given in 'Game' parameter
     */
    public void InitializePlayer(boolean isGameLoaded) {

        if (isGameLoaded) {
            // Load players from the file
            Players[] loadedPlayers = Load_data.Getplayersload();
            if (loadedPlayers == null) {
                System.out.println("No players loaded.");
                return;
            }
            else{
                players = loadedPlayers;

            }
            for (Players player : players) {
                System.out.println(player.getName() + ", X: " + player.getX() + ", Y: " + player.getY());
            }

            for (Players player : players) {
                grid.place(player);
                short xPosition = player.getX();
                short yPosition = player.getY();
            }
        }
        else{
            // Get the player names dynamically based on the number of players
            String[] playerNames = playerNames(nbPlayer);

            // Define colors for each player
            String[] playerColors = {"\u001B[31m", "\u001B[34m", "\u001B[32m", "\u001B[33m"}; // Red, Blue, Green, Yellow

            // Initialize players based on the number of players, their names, and colors
            for (int i = 0; i < nbPlayer; i++) {
                short xPosition = (short) (5 + i);  // Example: Adjust player starting positions
                short yPosition = 6;  // Same for all players (can be adjusted)
                players[i] = new Players(playerNames[i], xPosition, yPosition, playerColors[i]);
            }

            // Place each player on the grid
            for (Players player : players) {
                grid.place(player);
            }
        }
    }

    /**
     * Function for get the List of players
      * @return List Player
     */
    public Players[] getPlayers() {
        return players;
    }

    public Bot getBot() {
        return bot;
    }
    /**
     * Fonction for get the actual grid
     * @return grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Function for check if the player can declare endGame (if he stuck)
     * @param currentPlayers
     * @param grid
     * @return true
     */
    public boolean checkEndGame(Players currentPlayers , char[][] grid) {

        int cordx = currentPlayers.getX();
        int cordy = currentPlayers.getY();
                //en bas                        en haut,                      a droite                          a gauche
        return grid[cordx + 1][cordy] != '.' && grid[cordx - 1][cordy] != '.' && grid[cordx][cordy + 1] != '.' && grid[cordx][cordy - 1] != '.';
    }

    public void endGameLogic(Players currentPlayer){
        // Check if the current player is stuck
        if (checkEndGame(currentPlayer, getGrid().grid)) {
            System.out.println(                 currentPlayer.getName() + " is stuck and out of the game!");
            Save_score saveScore = new Save_score();
            saveScore.updateScore(currentPlayer.getName(), -2); // Decrease the score by 2
            removePlayer(currentPlayer); // Remove the player from the game
            Main.nbPlayers--;

            // Check if only one player remains
            if (Main.nbPlayers == 1) {
                Main.gameRunning = false;
                saveScore.updateScore(getPlayers()[0].getName(), 5); // Increase the score by 5
                System.out.println("\n                 Game Over! The winner is " + getPlayers()[0].getName() + "!");

                //NEED BREACK

            }
        }
    }
    /**
     * Function for remove player object from the Game object.
     * @param playerToRemove
     */
    public void removePlayer(Players playerToRemove) {
        Players[] newPlayers = new Players[players.length - 1];
        int index = 0;

        for (Players player : players) {
            if (!player.equals(playerToRemove)) {
                newPlayers[index++] = player;
            }
        }
        players = newPlayers;
    }

    /**
     * Function for ask Players name and use it into game
     * @param numberOfPlayers
     * @return String[]
     */
    public static String[] playerNames(int numberOfPlayers) {
        Scanner sc = new Scanner(System.in);
        String[] playerNames = new String[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.printf("                 Enter the name for player %d: ", i + 1);
            String name = sc.nextLine().trim(); // Read and trim whitespace

            // Validate the name length and uniqueness
            while (name.length() < 3 || name.length() > 10 || nameTaken(playerNames, name)) {
                if (name.isEmpty()) {
                    System.out.println("                 Name cannot be empty.");
                } else if (name.length() < 3) {
                    System.out.println("                 Name is too short. It must be at least 3 characters.");
                } else if (name.length() > 10) {
                    System.out.println("                 Name is too long. It must be no more than 10 characters.");
                } else if (nameTaken(playerNames, name)) {
                    System.out.println("                 This name is already taken. Please choose a different name.");
                }
                System.out.printf("                 Enter the name for player %d: ", i + 1);
                name = sc.nextLine().trim();
            }
            // Store the valid and unique name
            playerNames[i] = name;
        }
        return playerNames;
    }

    /**
     * Verify if name's already exist
     * @param playerNames
     * @param name
     * @return
     */
    public static boolean nameTaken(String[] playerNames, String name) {
        for (String existingName : playerNames) {
            if (existingName != null && existingName.equalsIgnoreCase(name)) {
                return true; // Name is already taken
            }
        }
        return false; // Name is available
    }
}
