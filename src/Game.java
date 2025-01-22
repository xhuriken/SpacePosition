
public class Game {

    private Grid grid;
    private Players[] players;
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
}

    public Players[] getPlayers() {
        return players;
    }

    public Grid getGrid() {
        return grid;
    }

    // Method to load player data
    private void Load_data(byte nbPlayer) {
        // Implementation for loading player data
        // This is a placeholder, you need to implement the actual logic
        System.out.println("Loading data for " + nbPlayer + " players.");
    }

    //function endgame
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
