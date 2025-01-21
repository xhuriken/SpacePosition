
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VideoAsciiPlayer {
    public static void main(String[] args) throws InterruptedException {
        String framePath = "path_to_frames/output"; // Chemin des frames
        int frameCount = 100; // Nombre total de frames
        int delay = 100; // Délai entre les frames (en ms)

        for (int i = 1; i <= frameCount; i++) {
            try {
                // Charger une frame ASCII
                String frame = Files.readString(Paths.get(framePath + String.format("%04d.txt", i)));
                System.out.print("\033[H\033[2J"); // Nettoyer le terminal
                System.out.flush();
                System.out.println(frame); // Afficher la frame
                Thread.sleep(delay); // Pause entre les frames
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement de la frame : " + i);
            }
        }
    }
}



