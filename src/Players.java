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

    public short getX(){
        return x;
    }

    public short getY(){
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

    public void move(short x, short y, char[][] grid, Players currentPlayer) {
        Scanner sc = new Scanner(System.in);
        boolean validMove = false;

        while (!validMove) {
            System.out.println("Veuillez entrer 'z' ou 'q' ou 's' ou 'd' :");
            String input = sc.nextLine().toLowerCase();

            short newX = currentPlayer.x;
            short newY = currentPlayer.y;

            switch (input) {
                case "z": newX -= 1; break; // Move up
                case "q": newY -= 1; break; // Move left
                case "s": newX += 1; break; // Move down
                case "d": newY += 1; break; // Move right
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
    //Fonction move(positionX, positionY, grille, joueurCourant)
//    Tant que le déplacement n'est pas valide :
//    Afficher "Entrez une direction : 'z' (haut), 'q' (gauche), 's' (bas), 'd' (droite)"
//    Lire la direction (input)
//
//    Initialiser nouvellePositionX = positionX
//    Initialiser nouvellePositionY = positionY
//
//    Selon la direction :
//    Si 'z' (haut) :
//    nouvellePositionY = positionY - 1
//    Si 'q' (gauche) :
//    nouvellePositionX = positionX - 1
//    Si 's' (bas) :
//    nouvellePositionY = positionY + 1
//    Si 'd' (droite) :
//    nouvellePositionX = positionX + 1
//    Sinon :
//    Afficher "Entrée invalide, essayez encore"
//    Recommencer la boucle
//
//    Si nouvellePositionX et nouvellePositionY sont dans les limites de la grille ET la case correspondante est vide ('.') :
//    Mettre à jour la grille pour effacer l'ancienne position du joueur (grille[positionX][positionY] = '.')
//    Mettre à jour la position du joueur (joueurCourant.positionX = nouvellePositionX, joueurCourant.positionY = nouvellePositionY)
//    Mettre à jour la grille avec la nouvelle position du joueur (grille[nouvellePositionX][nouvellePositionY] = 'J')
//    Afficher "Déplacement réussi"
//    Terminer la boucle
//    Sinon :
//    Afficher "Déplacement invalide, essayez encore"
//    Fin Fonction

}