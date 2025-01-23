public class Bot {
    private int x; // Coordonnée x du bot
    private int y; // Coordonnée y du bot

    /**
     * Constructeur par défaut pour le Bot.
     * Initialise les coordonnées à (0, 0).
     */
    public Bot() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructeur pour initialiser le Bot à des coordonnées spécifiques.
     *
     * @param x Coordonnée x initiale.
     * @param y Coordonnée y initiale.
     */
    public Bot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Définit la position du bot.
     *
     * @param x Nouvelle coordonnée x.
     * @param y Nouvelle coordonnée y.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la coordonnée x actuelle du bot.
     *
     * @return Coordonnée x.
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la coordonnée y actuelle du bot.
     *
     * @return Coordonnée y.
     */
    public int getY() {
        return y;
    }

    /**
     * Déplace le bot vers une nouvelle position si elle est valide.
     *
     * @param grid La grille sur laquelle le bot se déplace.
     * @param newX Nouvelle coordonnée x.
     * @param newY Nouvelle coordonnée y.
     */
    public void moveTo(char[][] grid, int newX, int newY) {
        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '.') {
            // Met à jour la grille : libère l'ancienne position
            grid[x][y] = '.';

            // Met à jour les coordonnées du bot
            x = newX;
            y = newY;

            // Place le bot sur la nouvelle position
            grid[x][y] = 'B';
        } else {
            System.out.println("Invalid move: target cell is either occupied or out of bounds.");
        }
    }
}
