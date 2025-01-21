
import java.util.Scanner;

public class Grid {

    public char[][] grid;

    public Grid(){
        grid = new char[11][10]; //li / col
        initializeGrid();
    }

    //Cette fonction est peu être pas si utile que ça les frr
    private void initializeGrid() {

        //Moin d'oppération :
        grid = new char[][] {
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
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
    public void place(Players player) {
        grid[player.getX()][player.getY()] = 'J';
    }

    public void displayGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Case destruction function
     * @param x
     * @param y
     */
    public void destroy(short x, short y){
    boolean Choice = true;
        //Request the player to enter a coordinates
        Scanner scanner = new Scanner(System.in);
        
        //As long as Choice is true, we continue
        while (Choice == true){
            System.out.print("Enter coordinates to destroy boxes: ");
            int CoordX = scanner.nextInt();
            int CoordY = scanner.nextInt();

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
