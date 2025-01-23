import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {
    public static boolean withBot = true;
    /**
     * Function for start the menu
     */
    public static void SetupMenu(){
        // Load and play background music
        Clip ambiance = loadSound("Theme.wav");

        if (ambiance != null) {
            setVolume(ambiance, 0.5f); // Set volume to 50%
            ambiance.loop(Clip.LOOP_CONTINUOUSLY); // Loop continuously
        }

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            // Display the main menu
            drawTitle();
            displayMainMenu();
            // Get user's choice
            System.out.print("Enter your choice: ");
            if (scanner.hasNextLine()){
                String choice = scanner.nextLine();
                // Handle the user's choice
                switch (choice) {
                    case "1":
                        playGame(false);
                        waitForReturnToMenu();
                        break;
                    case "2":
                        showRules();
                        waitForReturnToMenu();
                        break;
                    case "3":
                        showCredits();
                        waitForReturnToMenu();
                        break;
                    case"4":
                        showLoad();
                        break;
                    case "5":
                        showScore();
                        waitForReturnToMenu();
                    case "6":
                        quit = true;
                        System.out.println("Thanks for playing. See you next time!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
            // Add a blank line for readability
            System.out.println();
        }

        // Stop and close background music
        if (ambiance != null) {
            ambiance.stop();
            ambiance.close();
        }

        scanner.close();
    }

    /**
     * Function for show the game Title in cli
     */
    private static void drawTitle() {
        final String RESET = "\u001B[0m";
        final String YELLOW = "\u001B[33m";

        System.out.println(YELLOW + " ______  ______ ______  ______  ______       ______ ______  ______  __  ______ __  ______  __   __    \n" +
                "/\\  ___\\/\\  == /\\  __ \\/\\  ___\\/\\  ___\\     /\\  == /\\  __ \\/\\  ___\\/\\ \\/\\__  _/\\ \\/\\  __ \\/\\ \"-.\\ \\   \n" +
                "\\ \\___  \\ \\  _-\\ \\  __ \\ \\ \\___\\ \\  __\\     \\ \\  _-\\ \\ \\/\\ \\ \\___  \\ \\ \\/_/\\ \\\\ \\ \\ \\ \\/\\ \\ \\ \\-.  \\  \n" +
                " \\/\\_____\\ \\_\\  \\ \\_\\ \\_\\ \\_____\\ \\_____\\    \\ \\_\\  \\ \\_____\\/\\_____\\ \\_\\ \\ \\_\\\\ \\_\\ \\_____\\ \\_\\\\\"\\_\\ \n" +
                "  \\/_____/\\/_/   \\/_/\\/_/\\/_____/\\/_____/     \\/_/   \\/_____/\\/_____/\\/_/  \\/_/ \\/_/\\/_____/\\/_/ \\/_/ \n" +
                "                                                                                                      " + RESET);
    }

    /**
     * Function for show the logo in cli
     */
    private static void drawSpaceship() {
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";

        System.out.println(GREEN +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⠀  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀  ⢀⣠⠴⠶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀ ⣠⠞⠉⠀⠀⠀ ⠀⠾⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠀ ⣠⡤⠶⢾⠃⠀⠀⠀⠀⠀⠀⠀  ⠈⠛⠈⣷⠶⠤⣄⡀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⢀⡴⠋⢡⣖⡆⢹⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢀⡾  ⢰⣲⡌⠙⢦⡀⠀⠀\n" +
                "                                ⠀⢸⡁⠀⠀⠉⢁⡤⡝⠳⢤⣤⣄⣀⣀⣀⣤⡤⠶⢫⡤⡌⠉⠀⠀⠀   ⡷⠀⠀\n" +
                "⠀                                ⠈⠳⣄⠀⠀⠈⠛⠁ ⠀⣞⡶⠀⡴⢦⠀⢴⣳⠀⠈⠛⠁⠀⠀   ⣀⡾⠁⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠈⠙⠶⢤⣄⣀⠀⠀⠀  ⠙⠋⠀⠀⠁  ⠀⣀⣀⣤⠴⠛⠁⠀⠀⠀⠀\n" +
                "⠀                                ⠀⠀⠀⠀⠀⠀⠀⠀  ⠉⠉⠛⠛⠒⠒⠒⠒⠛⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀                ⠀                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     \n" +
                "⠀⠀⠀⠀⠀⠀                ⠀⠀                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     \n" + RESET);
    }

    /**
     * function for show the mainmenu option in cli
     */
    private static void displayMainMenu() {
        final String CYAN = "\u001B[36m";
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RED = "\u001B[31m";

        drawSpaceship(); // Draw the spaceship
        System.out.println("                ========================= MAIN MENU ========================= \n");
        System.out.println("                                           1." + CYAN + "Play Game" + RESET);
        System.out.println("                                           2." + GREEN + "Rules" + RESET);
        System.out.println("                                           3." + YELLOW + "Credits" + RESET);
        System.out.println("                                           4." + YELLOW + "Load" + RESET);
        System.out.println("                                           5." + CYAN + "Scores" + RESET);
        System.out.println("                                           6." + RED + "Quit\n" + RESET);
        System.out.println("                 ============================================================= ");
    }

    /**
     * Function for start the game
     */
    private static void playGame(boolean isGameLoaded) {

        if (isGameLoaded) {

            System.out.println("Game loaded successfully.");
            boolean gameRunning = true;
            int currentPlayerIndex = 0;
            int nbPlayers = 2;
            Game game = new Game((byte) nbPlayers, isGameLoaded);

            while (gameRunning) {
                // Display the grid
                Main.clearScreen();
                // Get the current player
                Players currentPlayer = game.getPlayers()[currentPlayerIndex];
                System.out.println("\n                 " + currentPlayer.getName() + "'s turn!");
                //System.out.println(currentPlayer.getX() + " " + currentPlayer.getY());

                // Move the player
                currentPlayer.move(currentPlayer.getX(), currentPlayer.getY(), game.getGrid().grid, currentPlayer, currentPlayer.getName(), game.getPlayers());
                game.getGrid().displayGrid(game.getPlayers());
                // Destroy a square
                game.getGrid().destroy();
                game.getGrid().displayGrid(game.getPlayers());

                // Check if the current player is stuck
                if (game.endgame(currentPlayer, game.getGrid().grid)) {
                    System.out.println(                 currentPlayer.getName() + " is stuck and out of the game!");
                    game.removePlayer(currentPlayer); // Remove the player from the game
                    nbPlayers--;

                    // Check if only one player remains
                    if (nbPlayers == 1) {
                        gameRunning = false;
                        System.out.println("\n                 Game Over! The winner is " + game.getPlayers()[0].getName() + "!");
                        break;
                    }
                }

                currentPlayerIndex = (currentPlayerIndex + 1) % nbPlayers;
            }
        } else {
            System.out.println("                 Starting a new game...");
            Scanner sc = new Scanner(System.in);


            // Submenu for game mode selection
            System.out.println("                 Choose your game mode:");
            System.out.println("                   1. Player vs Player");
            System.out.println("                   2. Player vs Bot");
            System.out.print("                 Enter your choice: ");
            String modeChoice = sc.nextLine();

            // Validate the choice
            while (!modeChoice.equals("1") && !modeChoice.equals("2")) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
                System.out.print("                 Enter your choice: ");
                modeChoice = sc.nextLine();
            }

            // Ask the number of players
            System.out.print("                 How many players? (2 to 4): ");
            int nbPlayers = sc.nextInt();
            sc.nextLine(); // Consume newline
        // Initialize the game
        Game game = new Game((byte) nbPlayers, isGameLoaded);

        // Determine if bot is included
        boolean hasBot = modeChoice.equals("2");

        if (hasBot) {
            Game.placeBotOnGrid();
        }

        boolean gameRunning = true;
        Random rand = new Random();
        int currentPlayerIndex = 0;
        currentPlayerIndex = (currentPlayerIndex + rand.nextInt(nbPlayers));
        game.getGrid().displayGrid(game.getPlayers());

        int turnCounter = 0; // Compteur de tours pour suivre le moment où le bot doit jouer

        while (gameRunning) {
            // Afficher la grille
            game.getGrid().displayGrid(game.getPlayers());

            // Obtenir le joueur actuel
            Players currentPlayer = game.getPlayers()[currentPlayerIndex];
            System.out.println("\n" + currentPlayer.getName() + "'s turn!");

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
                    System.out.println("Bot moved to new position: (" + newX + ", " + newY + ")");
                }
                // Check if the current player is stuck
                if (game.endgame(currentPlayer, game.getGrid().grid)) {
                    System.out.println(                 currentPlayer.getName() + " is stuck and out of the game!");
                    game.removePlayer(currentPlayer); // Remove the player from the game
                    nbPlayers--;
                    // Check if only one player remains
                    if (nbPlayers == 1) {
                        gameRunning = false;
                        System.out.println("\n                 Game Over! The winner is " + game.getPlayers()[0].getName() + "!");
                        break;
                    }
                }
            }
        }
    }

    /**
     * Function for show Rules in cli
     */
    private static void showRules() {
        final String PURPLE = "\u001B[35m";
        final String RESET = "\u001B[0m";
        System.out.println("                 ========================= GAME RULES ========================== \n");
        System.out.println("                         1." + PURPLE + "Move your UFO to an adjacent square." + RESET);
        System.out.println("                         2." + PURPLE + "Drop a meteor on a square of the board." + RESET);
        System.out.println("                         3." + PURPLE + "Block all opponents to win!\n" + RESET);
        System.out.println("                 ================================================================== ");
    }

    /**
     * Function for show Credit in cli
     */
    private static void showCredits() {
        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";
        System.out.println("                 =========================== CREDITS ============================ \n");
        System.out.println("                                       Developed by:");
        System.out.println("                                       " + BLUE +"[Célestin HONVAULT]");
        System.out.println("                                       [Mathéo Beaudet]");
        System.out.println("                                       [Clement Seurin Le Goffic]");
        System.out.println("                                       [Mateis Bourlet]");
        System.out.println("                                       [Steve Jobs]\n" + RESET);
        System.out.println("                                       Educational project.");
        System.out.println("                 ================================================================== ");
    }

    private static void showLoad() {
        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";
        System.out.println("                 =========================== LOAD ============================ \n");
        int compteur = 1;
        File fichier;
        String color = null; // Declare color variable
        fichier = new File("joueurs" + "_" + compteur + "." + "bin" );
        while (true) {
            // File name
            fichier = new File("joueurs_" + compteur + ".bin");
                if (compteur % 2 == 0){
                    color =  "\u001B[34m";
                }
                else{
                    color = "\u001B[33m";
                }
            if (fichier.exists()) { // Check if the file exists
                System.out.println("" + color + "["+compteur+" : joueurs"+"_" + compteur + ".bin]"+RESET);
            } else {
                break; // Stop the loop if the file does not exist
            }
            compteur++; // Increment the counter
        }
        System.out.println(""+color+ "["+compteur+" : quit]"+RESET);
        System.out.println("                 ================================================================== ");
        Scanner scannerChoice = new Scanner(System.in);
        if (scannerChoice.hasNextLine()){
            String choice = scannerChoice.nextLine();
            System.out.println("You entered: " + choice);
            try {
                if (choice.equals("quit")) {
                    MainMenu.displayMainMenu();
                } else if (Integer.parseInt(choice) < compteur) {
                    Load_data.Load_data(choice);
                    playGame(true);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (AssertionError e) {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showScore(){
        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";
        // Create a new instance of the fast_sorting class
        fast_sorting sorter = new fast_sorting();
        // Retrieve the scoreboard
        String[][] scoresArray = sorter.Datascore();
        // Sort the table by score
        sorter.quickSort(scoresArray, 0, scoresArray.length - 1);  // Correction ici

        //Display data [player][Scores]
        System.out.println("                 =========================== SCORES ============================ \n");
        // display table
        for (String[] entry : scoresArray) {
            System.out.println(entry[0] + " : " + entry[1]);
        }
        System.out.println("                 ================================================================== ");
    }

    /**
     * Function for wait input tu return
     */
    private static void waitForReturnToMenu() {
        System.out.println("\nPress any key to return to the menu...");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }

    /**
     * Function for ask Players name and use it into game
     * @param numberOfPlayers
     * @return String[]
     */
    public static String[] PlayerNames(int numberOfPlayers) {
        Scanner sc = new Scanner(System.in);
        String[] playerNames = new String[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.printf("                 Enter the name for player %d: ", i + 1);
            String name = sc.nextLine().trim(); // Read and trim whitespace

            // Validate the name length
            while (name.length() < 3 || name.length() > 10) {
                if (name.isEmpty()) {
                    System.out.println("                 Name cannot be empty.");
                } else if (name.length() < 3) {
                    System.out.println("                 Name is too short. It must be at least 3 characters.");
                } else if (name.length() > 10) {
                    System.out.println("                 Name is too long. It must be no more than 10 characters.");
                }
                System.out.printf("                 Enter the name for player %d: ", i + 1);
                name = sc.nextLine().trim();
            }
            // Store the valid name
            playerNames[i] = name;
        }
        return playerNames;
    }


    /**
     * Function for load Sound
     * @param filePath
     * @return Clip
     */
    private static Clip loadSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (audioFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                return clip;
            } else {
                System.out.println("Audio file " + filePath + " does not exist.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading sound: " + e.getMessage());
        }
        return null;
    }

    /**
     * Function for set the volume music
     * @param clip
     * @param volume
     */
    private static void setVolume(Clip clip, float volume) {
        if (clip != null) {
            try {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float min = gainControl.getMinimum();
                float max = gainControl.getMaximum();
                float range = max - min;
                float dB = min + (range * volume);
                gainControl.setValue(dB);
            } catch (IllegalArgumentException e) {
                System.out.println("Volume control is not supported on this system.");
            }
        }
    }
}
