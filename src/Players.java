import java.io.Serializable;
import java.util.Scanner;

public class Players implements Serializable {
    String name;
    short x;
    short y;

    Players(String name, short x, short y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    public String getName() {
        return name;
    }


//    /**
//     * Function for move players.
//     * @param y
//     * @param x
//     */
//    public void move(short x , short y, char[][] grid, Players currentPlayer){
//        String input;
//        boolean Choice = true;
//        //Request the player to enter a letter
//        //As long as Choice is true, we continue (for input misstaks)
//        while (Choice == true) {
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Veuillez entrer 'z' ou 'q' ou 's' ou 'd' :");
//            input = sc.nextLine().toLowerCase();
//            //check if the input is valid
//            if (input.equals("z") || input.equals("q") || input.equals("s") || input.equals("d")) {
//
//                if (input.equals("z")) {
//                    if (grid[x][y + (byte) 1] == '.') {
//                        currentPlayer.y += 1;
//                        Choice = false;
//                    }
//                    else {
//                        System.out.println("Vous ne pouvez pas aller ici");
//                    }
//                } else if (input.equals("q")) {
//                    if (grid[x - 1][y] == '.') {
//                        currentPlayer.x -= 1;
//                        Choice = false;
//
//                    }
//                    else {
//                        System.out.println("Vous ne pouvez pas aller ici");
//                    }
//                } else if (input.equals("s")) {
//                    if (grid[x][y - 1] == '.') {
//                        currentPlayer.y -= 1;
//                        Choice = false;
//
//                    }
//                    else {
//                        System.out.println("Vous ne pouvez pas aller ici");
//                    }
//                } else if (input.equals("d")) {
//                    if (grid[x + 1][y] == '.') {
//                        currentPlayer.x += 1;
//                        Choice = false;
//
//                    }
//                    else {
//                        System.out.println("Vous ne pouvez pas aller ici");
//                    }
//                }
//
//            } else {
//                System.out.println("Veuillez entrer une lettre valide");
//            }
//        }
//    }

    public void move(short x, short y, char[][] grid, Players currentPlayer,String name, Players[] players) {
        Scanner sc = new Scanner(System.in);
        boolean validMove = false;

        while (!validMove) {
            System.out.println("Please enter 'z', 'q', 's', or 'd' to move\n'quit' to quit\n'qs' to save and quit");
            String input = sc.nextLine().toLowerCase();

            short newX = currentPlayer.x;
            short newY = currentPlayer.y;

            switch (input) {
                case "z":
                    newX -= 1;
                    break; // Move up
                case "q":
                    newY -= 1;
                    break; // Move left
                case "s":
                    newX += 1;
                    break; // Move down
                case "d":
                    newY += 1;
                    break; // Move right
                case "qs":
                    Save_data save = new Save_data(players, grid );
                    save.save();
                    MainMenu.SetupMenu();
                case "quit":
                    MainMenu.SetupMenu();
                default:
                    System.out.println("Veuillez entrer une lettre valide");
                    continue;
            }
            //Check if the new position is valid
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '.') {
                // Update the grid: clear old position
                grid[currentPlayer.x][currentPlayer.y] = '.';
                // Update player position
                currentPlayer.x = newX;
                currentPlayer.y = newY;
                // Mark the new position in the grid
                grid[newX][newY] = 'J';
                validMove = true;
                System.out.println("Move successful!");
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}
