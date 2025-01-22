
import java.util.Scanner;

public class Grid {

    public char[][] grid;

    public Grid(){
        grid = new char[10][11];
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


     /**
      * Pseudo code de la fonction destroy
      * Il faut demander au joueur de rentrer des coordonnées (ex : a2)
      * Recup les coordonnées dans un string
      * boucle for pour split le string
      * quand i = 2, on recupere la valeur de x
      *
      */
    public void destroy(){
    boolean Choice = true;
        //Request the player to enter a coordinates
        Scanner scanner = new Scanner(System.in);
        int CoordXi;
        int CoordYi;
        short CoordXg = 0;
        short CoordYg = 0;
        //As long as Choice is true, we continue
        while (Choice == true){
            
            System.out.print("Enter coordinates to destroy boxes like 'a2': ");
            String coords = scanner.next();

            //Split the coordinates
            if (coords.length() == 2){
                //Check if the first character is a letter
                if(Character.isLetter(coords.charAt(0))){
                    //Get the first character
                    char CoordXChar = coords.charAt(0);
                    if(Character.isDigit(coords.charAt(1))){
                        //Get the second character
                        char CoordYChar = coords.charAt(1);

                        //sends one character per variable
                        CoordXi = CoordXChar % 97 ;
                        CoordYi = CoordYChar % 48 ;

                        CoordXg = (short) CoordXi ;
                        CoordYg = (short) CoordYi ;

                        System.err.println(CoordXg);
                        System.err.println(CoordYg);

                    }else{
                        System.err.println("Vos coordonnées ne finissent pas par un chiffre");
                    }
                }else{
                    System.err.println("Vos coordonnées ne commence pas par une lettre");
                }
            }else{
                System.err.println("Chaine de caractère trop longue");
            }



            
            //Check whether the coordinates are outside the table 
            if (CoordXg >= 0 && CoordXg < grid.length && CoordYg >= 0 && CoordYg < grid[0].length) {
                //Check if the box is empty
                if (grid[CoordXg][CoordYg -1] == '.') {
                    grid[CoordXg][CoordYg -1] = '#'; 
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
