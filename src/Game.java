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

    private void InitializePlayer() {
        if (nbPlayer >= 2) {
            players[0] = new Players("Joueur 1", (short) 5, (short) 6);
            players[1] = new Players("Joueur 2", (short) 6, (short) 6);
        }
        for (Players player : players) {
            grid.place(player);
        }
    }

    //function endgame
    public boolean endgame(Players currentPlayers , char[][] grid) {
        
        int cordx = currentPlayers.GetX();
        int cordy = currentPlayers.GetY();


        if (grid[cordx + 1][cordy] != '.' & grid[cordx - 1][cordy] != '.' & grid[cordx][cordy + 1] != '.' & grid[cordx][cordy - 1] != '.') {
            System.out.println("Vous ne pouvez plus bouger");
        }return true;
    }  
}
