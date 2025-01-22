
import java.util.Scanner;

public class Grid {

    public char[][] grid;

    public Grid(){
        grid = new char[10][11];
        initializeGrid();
    }

    /**
     * Function for initialize de grid (btw its useless, we can put this code inside 'public grid' nevermind)
     */
    private void initializeGrid() {

        //Moin d'oppération :
        grid = new char[][] {
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '#', '#', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '#', '.', '#', '.', '.' },
                { '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }
        };
        //Bonne vielle époque :
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                grid[i][j] = '.';
//            }
//        }
    }

    /**
     * Function for spawn player in the grid
     * @param player
     */
    public void place(Players player) {
        grid[player.getX()][player.getY()] = 'J';
    }

    /**
     * function for display the grid in cli
     */
    public void displayGrid(Players[] players) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                boolean isPlayerHere = false;

                for (Players player : players) {
                    if (player.getX() == i && player.getY() == j) {
                        // Print the player's symbol with their color
                        System.out.print(player.getColor() + "J" + "\u001B[0m" + " ");
                        isPlayerHere = true;
                        break;
                    }
                }

                if (!isPlayerHere) {
                    // Print the default grid cell
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


    /**
     * Function for destroy case
     */
    public void destroy(){
    boolean Choice = true;
        //Request the player to enter a coordinates
        Scanner scanner = new Scanner(System.in);
        
        //As long as Choice is true, we continue
        while (Choice == true){
            
            System.out.print("Enter coordinates to destroy boxes like 'x,y ': ");
            String coords = scanner.next();

            //Split the coordinates
            String[] parts = coords.split(",");
            short CoordX = Short.parseShort(parts[0]);
            short CoordY = Short.parseShort(parts[1]);
            
            //Check whether the coordinates are outside the table 
            if (CoordX >= 0 && CoordX < grid.length && CoordY >= 0 && CoordY < grid[0].length) {
                //Check if the box is empty
                if (grid[CoordX][CoordY] == '.') {
                    grid[CoordX][CoordY] = '#'; 
                    System.out.println("Box destroyed!");
                    Choice = false; 
                } else {
                    System.out.println("The case is occupied ;) Try again!");
                }
            } else {
                System.out.println("Coordinates out of bounds :( Try again !");
            }
        }
    }
}
