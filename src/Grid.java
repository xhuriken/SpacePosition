
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

    /**
     * Fonction pour obtenir la taille de la grille (nombre de lignes).
     * @return nombre de lignes dans la grille.
     */
    public int getSize() {
        return grid.length;
    }

    /**
     * Function for spawn player in the grid
     * @param player
     */
    public void place(Players player) {
        grid[player.getX()][player.getY()] = 'J';
    }

    /**
     * Function for spawn player in the grid
     * @param bot
     */
    public void placeBot(Bot bot) {
        grid[bot.getX()][bot.getY()] = 'B';
    }

    /**
     * function for display the grid in cli
     */
    public void displayGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isCellEmpty(int x, int y) {
        // Vérifiez si la case est vide (par exemple, avec '.' pour les cases libres)
        return this.grid[x][y] == '.';
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
