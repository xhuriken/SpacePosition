import java.util.Scanner;


public class Game {

    private Grid grid;
    private Players[] players;
    private byte nbPlayer;

    public Game(byte nbPlayer) {
        this.nbPlayer = nbPlayer;
        this.grid = new Grid();
        this.players = new Players[nbPlayer];
        InitializePlayer();
    }

    /**
     * Function to initialize the players according to the number of players given in 'Game' parameter
     */
    public void InitializePlayer() {
        // Get the player names dynamically based on the number of players
        String[] playerNames = MainMenu.PlayerNames(nbPlayer);

        // Initialize players based on the number of players and their names
        for (int i = 0; i < nbPlayer; i++) {
            short xPosition = (short) (5 + i);  // Example: Adjust player starting positions
            short yPosition = 6;  // Same for all players (can be adjusted)
            players[i] = new Players(playerNames[i], xPosition, yPosition);
        }

        // Place each player on the grid (assuming you have a grid object)
        for (Players player : players) {
            grid.place(player); // Assume grid is an object that has a place method
        }
    }

    /**
     * Function for get the List of players
      * @return List Player
     */
    public Players[] getPlayers() {
        return players;
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
    public boolean endgame(Players currentPlayers , char[][] grid) {

        int cordx = currentPlayers.getX();
        int cordy = currentPlayers.getY();


        if (grid[cordx + 1][cordy] != '.' & grid[cordx - 1][cordy] != '.' & grid[cordx][cordy + 1] != '.' & grid[cordx][cordy - 1] != '.') {
            System.out.println("Vous ne pouvez plus bouger");
            return true;
        }
        return false;
    }
}
