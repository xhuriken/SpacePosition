import java.util.Scanner;

public class Players {

    String name;
    short x;
    short y;

    Players(String name, short x, short y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public short GetX(){
        return x;
    }

    public short GetY(){
        return y;
    }

    //Function to move the player
    /**
     * 
     * @param y
     * @param x
     * @param players
     * 
     * Function that manages the player's movements 
     */

    public void Mouve(short y , short x, Players players , char[][] grid){ 
        String input;
        boolean Choice = true;
        //Request the player to enter a letter
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer 'z' ou 'q' ou 's' ou 'd' :");
        input = sc.nextLine().toLowerCase();
        //As long as Choice is true, we continue 
        while (Choice == true) {
        //check if the input is valid
        if (input.equals("z") || input.equals("q") || input.equals("s") || input.equals("d")) {

            if (input.equals("z")) {
                if (grid[x][y - 1] == '.') {
                    y -= 1;
                }
                else {
                    System.out.println("Vous ne pouvez pas aller ici");
                }
            } else if (input.equals("q")) {
                if (grid[x - 1][y] == '.') {
                    x -= 1;
                }
                else {
                    System.out.println("Vous ne pouvez pas aller ici");
                }
            } else if (input.equals("s")) {
                if (grid[x][y + 1] == '.') {
                    y += 1;
                }
                else {
                    System.out.println("Vous ne pouvez pas aller ici");
                }
            } else if (input.equals("d")) {
                if (grid[x + 1][y] == '.') {
                    x += 1;
                }
                else {
                    System.out.println("Vous ne pouvez pas aller ici");
                }
            }

        } else {
            System.out.println("Veuillez entrer une lettre valide");
        } 
        }
}
}