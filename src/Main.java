import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //Initialize logic variable:
    static boolean gameRunning = true;
    static int currentPlayerIndex = 0;
    static int nbPlayers = 0;

    public static void main(String[] args) {
        MainMenu.setupMenu();
    }

    /**
     * Function for start the game
     */
    public static void playGame(boolean isGameLoaded) {

        if (isGameLoaded) {
            System.out.println("                 Game loaded successfully.");
            Game game = new Game((byte) nbPlayers, isGameLoaded);
            game.getGrid().displayGrid(game.getPlayers());
            while (gameRunning) {
                // Get the current player
                Players currentPlayer = game.getPlayers()[currentPlayerIndex];
                System.out.println("                 " + currentPlayer.getName() + "'s turn!");
                //System.out.println(currentPlayer.getX() + " " + currentPlayer.getY());
                // Move the player
                currentPlayer.move(currentPlayer.getX(), currentPlayer.getY(), game.getGrid().grid, currentPlayer, currentPlayer.getName(), game.getPlayers());
                game.getGrid().displayGrid(game.getPlayers());
                // Destroy a square
                game.getGrid().destroy();
                game.getGrid().displayGrid(game.getPlayers());
                game.endGameLogic(currentPlayer);
                currentPlayerIndex = (currentPlayerIndex + 1) % nbPlayers;
            }
        }
        else
        {
            System.out.println("                 Starting a new game...");
            Scanner sc = new Scanner(System.in);

            // Submenu for game mode selection
            System.out.println("                 Choose your game mode:");
            System.out.println("                   1. Player vs Player");
            System.out.println("                   2. Player vs Bot");
            System.out.print  ("                 Enter your choice: ");
            String modeChoice = sc.nextLine();

            // Validate the choice
            while (!modeChoice.equals("1") && !modeChoice.equals("2")) {
                System.out.println("                 Invalid choice. Please enter 1 or 2.");
                System.out.print  ("                 Enter your choice: ");
                modeChoice = sc.nextLine();
            }

            askPlayersQuantity(sc);

            Game game = new Game((byte) nbPlayers, isGameLoaded);
            // Determine if bot is included
            boolean hasBot = modeChoice.equals("2");

            if (hasBot) {
                game.getGrid().placeBotOnGrid();
            }

            Random rand = new Random();
            currentPlayerIndex = (currentPlayerIndex + rand.nextInt(nbPlayers));
            game.getGrid().displayGrid(game.getPlayers());

            int turnCounter = 0; // Compteur de tours pour suivre le moment où le bot doit jouer

            while (gameRunning) {

                // Obtenir le joueur actuel
                Players currentPlayer = game.getPlayers()[currentPlayerIndex];
                System.out.println("                 " + currentPlayer.getName() + "'s turn!");

                // Move the player
                currentPlayer.move(currentPlayer.getX(), currentPlayer.getY(), game.getGrid().grid, currentPlayer, currentPlayer.getName(), game.getPlayers());
                game.getGrid().displayGrid(game.getPlayers());

                // Destroy a square
                game.getGrid().destroy();
                game.getGrid().displayGrid(game.getPlayers());

                currentPlayerIndex = (currentPlayerIndex + 1) % nbPlayers;
                turnCounter++;
                // Vérifier si tous les joueurs ont joué leur tour
                if (turnCounter % nbPlayers <= 0 && hasBot) {
                    System.out.println("BotTour");
                    Bot currentBot = game.getBot();
                    Random random = new Random();
                    int gridSize = game.getGrid().getSize(); // Taille de la grille

                    boolean validMove = false;
                    int newX = currentBot.getX();
                    int newY = currentBot.getY();

                    // Générer un mouvement valide pour le bot
                    while (!validMove) {
                        int moveDistance = random.nextInt(3) + 1;
                        newX = currentBot.getX() + (random.nextBoolean() ? moveDistance : -moveDistance);
                        newY = currentBot.getY() + (random.nextBoolean() ? moveDistance : -moveDistance);

                        // Vérifier que les nouvelles coordonnées sont valides et que la case n'est pas occupée
                        if (newX >= 0 && newX < gridSize && newY >= 0 && newY < gridSize) {
                            char targetCell = game.getGrid().grid[newX][newY]; // Récupérer le contenu de la case cible
                            if (targetCell != '#' && targetCell != 'J') { // Vérifier que ce n'est pas un obstacle ou un joueur
                                validMove = true;
                            }
                        }
                    }
                    currentBot.moveTo(game.getGrid().grid, newX, newY);
                    //System.out.println("Bot moved to new position: (" + newX + ", " + newY + ")");
                }
                game.endGameLogic(currentPlayer);
            }
        }
    }

    /**
     * Ask the quantity of players in game.
     * @param sc
     */
    public static void askPlayersQuantity(Scanner sc){
        // Ask the number of players
        System.out.print("                 How many players? (2 to 4): ");
        // Initialize the game
        if (sc.hasNextInt()){
            nbPlayers = sc.nextInt();
        }
        else {
            System.out.println("                 Invalid number of players. Please enter a number between 2 and 4.");
            playGame(false);
        }

        if (nbPlayers < 2 || nbPlayers > 4) {
            System.out.println("                 Invalid number of players. Please enter a number between 2 and 4.");
            playGame(false);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream("/sounds/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}


//STORY 1:
//Faire un menu pour regrouper plusieurs fonctionalité (chacune dans une fonction quoi)
//STORY 2:
//Faire la fonction display_rules() -> Accecible depuis le menu dcp quoi enfaite tukapt
//STORY 3:
//GROSSE FONCTION PLAY()
//Elle appelleras plusieur sous fonction comme celle de la story 3 'initialize_game()'
//Le jeu attribue un pseudo aux deux joueurs et affiche le plateau de jeux.
// De manière aléatoire un joueur est désigné pour commencer à jouer.
//STORY 4:
//On réfléchiras à la meilleure optimisation de play, a mais on va essayer de faire en plein de petit bout de code different
//DONT CELUI DE LA STORY 4 LOL VOUS COMMENCEZ A CAPTER OU KOA LES BOYZ (je deviens fous je parle tout seul dans des commentaire donnez moi de l'argent)
//Move(player) -> fonction pour déplacer le joueur donné OUAIIII
//STORY 5:
//canMove(player) -> Verifier si le joueur peu encore bouger
//canMove seras ensuite appeler dans une fonction Win() pour savoir si c'est gagné quoi fait pas genre ta pas compris c'est ecrit dans le nom zbi
//STORY 6:
// Ca commence a être les ajout qui serve a rien donc j'ai la flm allez bisous jvous passe le salam quoi