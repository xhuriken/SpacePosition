import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

    public class MainMenu {

        public static void main(String[] args) {

            Clip ambiance = chargerSon("Theme.wav");

            if (ambiance != null) {
                reglerVolume(ambiance, 0.5f);
                ambiance.loop(Clip.LOOP_CONTINUOUSLY);
            }

            Scanner scanner = new Scanner(System.in);
            boolean quit = false;

            while (!quit) {
                // Afficher le menu principal
                afficherMenu();

                // Lire le choix de l'utilisateur
                System.out.print("Entrez votre choix : ");
                String choix = scanner.nextLine();

                // Traiter le choix
                switch (choix) {
                    case "1":
                        jouerAuJeu();
                        RetourMenu();
                        break;
                    case "2":
                        afficherRegles();
                        RetourMenu();
                        break;
                    case "3":
                        afficherCredits();
                        RetourMenu();
                        break;
                    case "4":
                        quit = true;
                        System.out.println("Merci d'avoir joué. À bientôt !");
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                        break;
                }

                // Ligne vide pour lisibilité
                System.out.println();
            }

            if (ambiance != null) {
                ambiance.stop();
                ambiance.close();
            }

            scanner.close();
        }

        // Méthode pour afficher le menu principal
        private static void afficherMenu() {

            final String RESET = "\u001B[0m";
            final String RED = "\u001B[31m";
            final String GREEN = "\u001B[32m";
            final String YELLOW = "\u001B[33m";
            final String BLUE = "\u001B[34m";
            final String PURPLE = "\u001B[35m";
            final String CYAN = "\u001B[36m";

            System.out.println("=== MENU PRINCIPAL === \n");
            System.out.println( GREEN + "⠀⠀⠀ ⣀⣴⣢⣉⣉⠉⠉⠒⠤⡀⠀⣀⣀⣀⣀⣀⠀\n" +
                                        "⠀⢀⣼⣿⣿⣿⣿⣿⣿⣶⣶⣦⣿⠏⠹⣿⣿⡟⣰⣿\n" +
                                        "⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⣠⣽⠳⠆⣰⣿⠃\n" +
                                        "⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢁⡘⣿⣿⢂⣾⣿⡃⠀\n" +
                                        "⢸⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣨⠻⢆⣴⣿⠟⠻⡷⠀\n" +
                                        "⠀⢿⣿⣿⣿⠿⠋⠁⣿⣿⣿⣇⣴⣿⠟⠁⠀⠀⠀⠀ \n" +
                                        "⠀⣼⡉⣉⣴⣦⡼⣷⠉⣩⣿⣿⣟⠁⠀⠀⠀⠀⠀⠀ \n" +
                                        "⣸⣿⣿⣿⠿⠿⣣⣴⣿⡿⠋⠙⠿⠃⠀⠀⠀⠀⠀⠀ \n" +
                                        "⡿⢟⣋⣭⣶⣿⡿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  \n" +
                                        "⢿⡿⠿⠟⠋⢿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  \n" + RESET);
            System.out.println("1. " + CYAN + "Jeu" + RESET);
            System.out.println("2. " + GREEN + "Règles" + RESET);
            System.out.println("3. " + YELLOW + "Crédits" + RESET);
            System.out.println( "4. " + RED + "Quitter" + RESET);
            System.out.println("======================");
        }

        // Méthode pour simuler l'action de jouer au jeu
        private static void jouerAuJeu() {
            System.out.println("Vous avez choisi de jouer au jeu !");
            System.out.println("Bonne chance et amusez-vous bien !");
        }

        // Méthode pour afficher les règles du jeu
        private static void afficherRegles() {
            System.out.println("=== RÈGLES DU JEU ===");
            System.out.println("1. Suivez les instructions affichées.");
            System.out.println("2. Respectez les règles pour gagner.");
            System.out.println("3. Amusez-vous !");
            System.out.println("=====================");
        }

        // Méthode pour afficher les crédits
        private static void afficherCredits() {
            System.out.println("=== CRÉDITS ===\n");
            System.out.println("Développé par :\n[Célestin HONVAULT]\n[Mathéo Beaudet]\n[Clement Seurin Le Goffic]\n[Mateis Bourlet]\n");

            System.out.println("Projet éducatif.");
            System.out.println("================");
        }

        private static void RetourMenu() {
            System.out.println("\nAppuyez sur une touche pour retourner au menu...");
            try {
                System.in.read();
            } catch (IOException e) {
                System.out.println("Erreur lors de la lecture de l'entrée.");
            }
        }

        private static Clip chargerSon(String cheminFichier) {
            try {
                File fichierAudio = new File(cheminFichier);
                if (fichierAudio.exists()) {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(fichierAudio);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    return clip;
                } else {
                    System.out.println("Le fichier audio " + cheminFichier + " n'existe pas.");
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println("Erreur lors du chargement du son : " + e.getMessage());
            }
            return null;
        }

        private static void reglerVolume(Clip clip, float volume) {
            if (clip != null) {
                try {
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                    // Convertir le volume en décibels
                    float min = gainControl.getMinimum(); // Volume minimum en dB
                    float max = gainControl.getMaximum(); // Volume maximum en dB
                    float range = max - min;

                    // Calcul du volume en dB
                    float dB = min + (range * volume); // volume entre 0.0 (silence) et 1.0 (max)
                    gainControl.setValue(dB);
                } catch (IllegalArgumentException e) {
                    System.out.println("Le contrôle du volume n'est pas pris en charge sur ce système.");
                }
            }
        }

    }


