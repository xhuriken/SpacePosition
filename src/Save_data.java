


public class Save_data{

    public void initdata(){
        char[][] grid = new char[10][11];
        Players[] players = new Players[2];

        players[0] = new Players("didi", (short) 5, (short) 6);
        players[1] = new Players("dede", (short) 6, (short) 6);

        grid = new char[10][11];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '.'; 
            }
        }
        grid[0][0] = 'X';
        grid[1][0] = 'X';
        
        System.out.println("Joueur 1 : " + players[0].GetName() + " X : " + players[0].GetX() + " Y : " + players[0].GetY());
        System.out.println("Joueur 2 : " + players[1].GetName() + " X : " + players[1].GetX() + " Y : " + players[1].GetY());
    }
}