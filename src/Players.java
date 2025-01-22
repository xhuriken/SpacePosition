import java.util.Scanner;
import java.io.Serializable;

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
    public void move(short x, short y, char[][] grid, Players currentPlayer) {
        Scanner sc = new Scanner(System.in);
        boolean validMove = false;

        while (!validMove) {
            System.out.println("Veuillez entrer 'z' ou 'q' ou 's' ou 'd' :");
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
