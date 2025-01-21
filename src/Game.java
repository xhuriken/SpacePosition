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
    private void InitializePlayer() {
        if (nbPlayer == 2) {
            players[0] = new Players("Joueur 1", (short) 5, (short) 6);
            players[1] = new Players("Joueur 2", (short) 6, (short) 6);
        }else if(nbPlayer == 3){
            players[0] = new Players("Joueur 1", (short) 5, (short) 6);
            players[1] = new Players("Joueur 2", (short) 6, (short) 6);
            players[2] = new Players("Joueur 3", (short) 4, (short) 6);
        }else{
            players[0] = new Players("Joueur 1", (short) 5, (short) 6);
            players[1] = new Players("Joueur 2", (short) 6, (short) 6);
            players[2] = new Players("Joueur 3", (short) 4, (short) 6);
            players[3] = new Players("Joueur 4", (short) 4, (short) 4);
        }
        for (Players player : players) {
            grid.place(player);
        }
    }

}
