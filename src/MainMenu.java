import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class MainMenu {

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

    // Method to draw the title
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

    // Method to draw the spaceship
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

    // Method to display the main menu
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
        System.out.println("                                           5." + RED + "Quit\n" + RESET);
        System.out.println("                 ============================================================== ");
    }

    // Method to simulate playing the game
    private static void playGame(boolean isGameLoaded) {

        if (isGameLoaded) {
            System.out.println("Game loaded successfully.");
            boolean gameRunning = true;
            int currentPlayerIndex = 0;
            int nbPlayers = 2;
            Game game = new Game((byte) nbPlayers,isGameLoaded);
            while (gameRunning) {
                // Display the grid
                game.getGrid().displayGrid();
                // Get the current player
                Players currentPlayer = game.getPlayers()[currentPlayerIndex];
                System.out.println(currentPlayer.getX() + " " + currentPlayer.getY());
                // Move the player
    
                currentPlayer.move(currentPlayer.getX(), currentPlayer.getY(), game.getGrid().grid, currentPlayer, currentPlayer.getName(), game.getPlayers());
                game.getGrid().displayGrid();
                // Destroy a square
                game.getGrid().destroy();
    
                // Check if the game is over
                //(we need to create isGameOver)
    //            if (isGameOver(game)) {
    //                gameRunning = false;
    //                System.out.println("\nGame Over! " + currentPlayer.getName() + " wins!");
    //            } else {
    //                // Move to the next player
    //                currentPlayerIndex = (currentPlayerIndex + 1) % nbPlayers;
    //            }
                //Skip to next player
                currentPlayerIndex = (currentPlayerIndex + 1) % nbPlayers;
            }
        } 
        else {
            System.out.println("Starting a new game...");
        Scanner sc = new Scanner(System.in);
        // Ask the number of players
        System.out.printf("How many players? (2 to 4):");
        int nbPlayers = sc.nextInt();
        // Initialize the game
        Game game = new Game((byte) nbPlayers,isGameLoaded);
        boolean gameRunning = true;
        int currentPlayerIndex = 0;
        while (gameRunning) {
            // Display the grid
            game.getGrid().displayGrid();

            // Get the current player
            Players currentPlayer = game.getPlayers()[currentPlayerIndex];
            System.out.println("\n" + currentPlayer.getName() + "'s turn!");

            System.out.println(currentPlayer.getX() + " " + currentPlayer.getY());
            // Move the player

            currentPlayer.move(currentPlayer.getX(), currentPlayer.getY(), game.getGrid().grid, currentPlayer, currentPlayer.getName(), game.getPlayers());

            game.getGrid().displayGrid();

            // Destroy a square
            game.getGrid().destroy();

            // Check if the game is over
            //(we need to create isGameOver)
//            if (isGameOver(game)) {
//                gameRunning = false;
//                System.out.println("\nGame Over! " + currentPlayer.getName() + " wins!");
//            } else {
//                // Move to the next player
//                currentPlayerIndex = (currentPlayerIndex + 1) % nbPlayers;
//            }
            //Skip to next player
            currentPlayerIndex = (currentPlayerIndex + 1) % nbPlayers;
        }
    }
    }

    // Method to display game rules
    private static void showRules() {
        final String PURPLE = "\u001B[35m";
        final String RESET = "\u001B[0m";

        System.out.println("                 ========================= GAME RULES ========================== \n");
        System.out.println("                         1." + PURPLE + "Move your UFO to an adjacent square." + RESET);
        System.out.println("                         2." + PURPLE + "Drop a meteor on a square of the board." + RESET);
        System.out.println("                         3." + PURPLE + "Block all opponents to win!\n" + RESET);
        System.out.println("                 ================================================================== ");
    }

    // Method to display credits
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
    // Method to wait for user input before returning to the main menu
    private static void waitForReturnToMenu() {
        System.out.println("\nPress any key to return to the menu...");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }

    public static String[] PlayerNames(int numberOfPlayers) {
        Scanner sc = new Scanner(System.in);
        String[] playerNames = new String[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.printf("Enter the name for player %d: ", i + 1);
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
    // Method to load a sound file
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

    // Method to adjust the volume of the sound
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
