import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class MainMenu {
    public static boolean withBot = true;
    /**
     * Function for start the menu
     */
    public static void setupMenu(){
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
            if (scanner.hasNextLine()){
                String choice = scanner.nextLine();
                // Handle the user's choice
                switch (choice) {
                    case "1":
                        Main.isFinish = false;
                        Main.playGame(false);
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
                        break;
                    case "6":
                        Main.gameRunning = false;
                        System.out.println("                 Thanks for playing. See you next time!");
                        System.exit(0);
                        quit = true;
                        break;
                    default:
                        System.out.println("                 Invalid choice. Please try again.");
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
    public static void drawTitle() {
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
                "                                ⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀  ⢀⣠⠴⠶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀ ⣠⠞⠉⠀⠀⠀ ⠀⠾⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠀ ⣠⡤⠶⢾⠃⠀⠀⠀⠀⠀⠀⠀  ⠈⠛⠈⣷⠶⠤⣄⡀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⢀⡴⠋⢡⣖⡆⢹⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢀⡾  ⢰⣲⡌⠙⢦⡀⠀⠀\n" +
                "                                ⠀⢸⡁⠀⠀⠉⢁⡤⡝⠳⢤⣤⣄⣀⣀⣀⣤⡤⠶⢫⡤⡌⠉⠀⠀⠀   ⡷⠀⠀\n" +
                "⠀                                ⠈⠳⣄⠀⠀⠈⠛⠁ ⠀⣞⡶⠀⡴⢦⠀⢴⣳⠀⠈⠛⠁⠀⠀   ⣀⡾⠁⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠈⠙⠶⢤⣄⣀⠀⠀⠀  ⠙⠋⠀⠀⠁  ⠀⣀⣀⣤⠴⠛⠁⠀⠀⠀⠀\n" +
                "⠀                                ⠀⠀⠀⠀⠀⠀⠀⠀  ⠉⠉⠛⠛⠒⠒⠒⠒⠛⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" + RESET);
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
        final String YELLOW = "\u001B[33m";
        final String RESET = "\u001B[0m";
        System.out.println("                 =========================== LOAD ============================ \n");

        int compteur = 1;
        File fichier;
        String color;

        while (true) {
            fichier = new File("joueurs_" + compteur + ".bin");
            if (compteur % 2 == 0) {
                color = BLUE;
            } else {
                color = YELLOW;
            }
            if (fichier.exists()) {
                System.out.println(color + "[" + compteur + " : joueurs_" + compteur + ".bin]" + RESET);
            } else {
                break;
            }
            compteur++;
        }
        // Display the quit option
        System.out.println("" + color + "[" + compteur + " : quit]" + RESET);
        System.out.println("                 ================================================================== ");

        Scanner scannerChoice = new Scanner(System.in);
        if (scannerChoice.hasNextLine()) {
            String choice = scannerChoice.nextLine().trim();
            System.out.println("You entered: " + choice);

            try {
                if (choice.isEmpty()) {
                    System.out.println("Invalid input. Please enter a valid number or 'quit'.");
                    showLoad();
                } else if (choice.equalsIgnoreCase("quit") || Integer.parseInt(choice) == compteur) {
                    displayMainMenu();
                } else {
                    int choiceNumber = Integer.parseInt(choice);
                    if (choiceNumber > 0 && choiceNumber < compteur) {
                        Load_data.Load_data(choice);
                        Main.playGame(true);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                        showLoad();
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'quit'.");
                showLoad();
            }
        }
    }

    private static void showScore(){
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";
        System.out.println("                 =========================== SCORES ============================ \n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want the scores in ascending or descending order?");
        System.out.println("["+YELLOW+"1 "+RESET+": "+BLUE+"Ascending]"+RESET);
        System.out.println("["+YELLOW+"2"+RESET+" : "+BLUE+"Descending]"+RESET);
        System.out.print("Enter your choice: ");

        // Create a new instance of the fast_sorting class
        fast_sorting sorter = new fast_sorting();
        // Retrieve the scoreboard
        String[][] scoresArray = sorter.Datascore();


        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            sorter.quickSort(scoresArray, 0, scoresArray.length - 1, false);
            for (String[] entry : scoresArray) {
                System.out.println("                                        "+BLUE + entry[0] + RESET + " : " + YELLOW + entry[1] + RESET);

            }
            System.out.println("                 ================================================================== ");
        } else if (choice.equals("2")) {
            sorter.quickSort(scoresArray, 0, scoresArray.length - 1,true);
            for (String[] entry : scoresArray) {
                System.out.println("                                        "+BLUE + entry[0] + RESET + " : " + YELLOW + entry[1] + RESET);

            }
            System.out.println("                 ================================================================== ");
        } else {
            System.out.println("Invalid choice. Please try again.");
            showScore();
        }


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
