import java.io.Serializable;
import java.util.Scanner;

public class Players implements Serializable {

    String name;
    short x;
    short y;
    String color;

    Players(String name, short x, short y,String color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Function for get the X pos of an player
     * @return short
     */
    public short getX() {
        return x;
    }

    /**
     * Function for get the Y pos of an player
     * @return short
     */
    public short getY() {
        return y;
    }
    /**
     * Function for get the player's name
     * @return short
     */
    public String getName() {
        return name;
    }

    /**
     * Function for get the player's color
     * @return short
     */
    public String getColor() {
        return color;
    }

    /**
     * Function for move player
     * @param x
     * @param y
     * @param grid
     * @param currentPlayer
     */
    public void move(short x, short y, char[][] grid, Players currentPlayer, String name, Players[] players) {
        Scanner sc = new Scanner(System.in);
        boolean validMove = false;

        while (!validMove) {
            System.out.print  ("                 Enter your move (Z, Q, S, D):");
            System.out.print  ("                 Or type 'qs' to save and quit the game.");
            System.out.println("                 or type 'quit' to quit the game.");
            System.out.print  ("                 Type... :");
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
                    Save_data save = new Save_data(players, grid);
                    save.save();
                    MainMenu.setupMenu();
                    Main.gameRunning = false;
                    break;
                case "quit":
                    MainMenu.setupMenu();
                    Main.gameRunning = false;
                    break;
                default:
                    System.out.println("                 Please enter a valid move.");
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
                System.out.println("                 Move successful!");
            } else {
                System.out.println("                 Invalid move. Try again.");
            }
        }
    }
}
