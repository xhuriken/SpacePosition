
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
        grid[player.GetX()][player.GetY()] = 'J';
    }


    /**
     * Case destruction function
     * @param x
     * @param y
     */
    public void destroyed (short x, short y){
    boolean Choice = true;
        //Request the player to enter a coordinates
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter coordinates to destroy boxes: ");
        int CoordXY = scanner.nextInt();

        //debug
        System.out.println(CoordXY);

        //As long as Choice is true, we continue
        while (Choice == true){
            if (CoordXY == ','){
                CoordXY = '#';
            }else{
                System.out.println("The case is occupied");
            }
        }
    }
}
