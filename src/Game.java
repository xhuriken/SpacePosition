import java.util.Random;

public class Game {

    private static Grid grid;
    private Players[] players;
    private static Bot bot; // Un bot unique
    private byte nbPlayer;

    public Game(byte nbPlayer) {
        this.nbPlayer = nbPlayer;
        this.grid = new Grid();
        this.players = new Players[nbPlayer];
        this.bot = new Bot(); // Initialiser le bot
        InitializePlayer();
    }

    /**
     * Fonction pour initialiser les joueurs et le bot
     */
    public void InitializePlayer() {
        // Obtenir les noms des joueurs dynamiquement en fonction du nombre de joueurs
        String[] playerNames = MainMenu.PlayerNames(nbPlayer);

        // Initialiser les joueurs
        for (int i = 0; i < nbPlayer; i++) {
            short xPosition = (short) (5 + i);  // Position initiale des joueurs
            short yPosition = 6;
            players[i] = new Players(playerNames[i], xPosition, yPosition);
        }

        // Placer chaque joueur sur la grille
        for (Players player : players) {
            grid.place(player); // Placer le joueur sur la grille
        }

        // Initialiser et placer le bot sur une case libre

    }

    /**
     * Méthode pour placer le bot sur une case aléatoire qui n'est pas occupée
     */
    public static void placeBotOnGrid() {

        Random random = new Random();
        int gridSize = grid.getSize();

        while (true) {
            int x = random.nextInt(gridSize);
            int y = random.nextInt(gridSize);

            // Vérifier si la case est vide avant de placer le bot
            if (grid.isCellEmpty(x, y)) {
                bot.setPosition(x, y);
                grid.placeBot(bot);
                break;
            }
        }
    }

    /**
     * Fonction pour obtenir la liste des joueurs
     * @return Liste des joueurs
     */
    public Players[] getPlayers() {
        return players;
    }

    public Bot getBot() {
        return bot;
    }
    /**
     * Fonction pour obtenir la grille actuelle
     * @return Grille
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Fonction pour vérifier si un joueur peut déclarer la fin de la partie (s'il est bloqué)
     * @param currentPlayers
     * @param grid
     * @return true si bloqué
     */
    public boolean endgame(Players currentPlayers, char[][] grid) {

        int cordx = currentPlayers.getX();
        int cordy = currentPlayers.getY();

        if (grid[cordx + 1][cordy] != '.' &&
                grid[cordx - 1][cordy] != '.' &&
                grid[cordx][cordy + 1] != '.' &&
                grid[cordx][cordy - 1] != '.') {
            System.out.println("Vous ne pouvez plus bouger");
            return true;
        }
        return false;
    }
}
