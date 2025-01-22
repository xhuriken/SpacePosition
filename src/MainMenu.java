import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {


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
            System.out.print("                 Enter your choice: ");
            String choice = scanner.nextLine();

            // Handle the user's choice
            switch (choice) {
                case "1":
                    playGame();
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
                case "4":
                    quit = true;
                    System.out.println("Thanks for playing. See you next time!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
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
        System.out.println("                                           4." + RED + "Quit\n" + RESET);
        System.out.println("                 ============================================================== ");
    }

    /**
     * Function for start the game
     */
    private static void playGame() {
        Scanner sc = new Scanner(System.in);

        boolean choice = true;
        int nbPlayers = 0;
        while (choice) {
            try {
                // Ask the number of players
                System.out.print("                 How many players? (2 to 4): ");

                // Read user input
                nbPlayers = sc.nextInt();

                // Check if the number is within the valid range
                if (nbPlayers >= 2 && nbPlayers <= 4) {
                    choice = false; // Valid input, exit the loop
                } else {
                    System.out.println("                 Please enter a number between 2 and 4.");
                }
            } catch (Exception e) {
                System.out.println("                 Invalid input. Please enter a number between 2 and 4.");
                sc.nextLine(); // Clear the invalid input
            }
        }


        // Initialize the game
        Game game = new Game((byte) nbPlayers);

        boolean gameRunning = true;
        Random rand = new Random();
        int currentPlayerIndex = 0;
        currentPlayerIndex = (currentPlayerIndex + rand.nextInt(nbPlayers));
        //System.out.println(currentPlayerIndex);

        game.getGrid().displayGrid(game.getPlayers());

        while (gameRunning) {
            // Display the grid
            Main.clearScreen();
            // Get the current player
            Players currentPlayer = game.getPlayers()[currentPlayerIndex];
            System.out.println("\n                 " + currentPlayer.getName() + "'s turn!");

            //System.out.println(currentPlayer.getX() + " " + currentPlayer.getY());
            // Move the player

            currentPlayer.move(currentPlayer.getX(), currentPlayer.getY(), game.getGrid().grid, currentPlayer);

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

            //Skip to next player
            currentPlayerIndex = (currentPlayerIndex + 1) % nbPlayers;

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
