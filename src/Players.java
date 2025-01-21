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

    public short GetX(){
        return x;
    }

    public short GetY(){
        return y;
    }

    public void Mouve(short y , short x, Players players){ 

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the direction you want to move: ");
        String direction = sc.nextLine();

        // if (direction == "top") {
        //     y += ;
        // } else if (direction == "left") {
        //     x += ;
        // } else if (direction == "right") {
        //     x += right;
        // } else if (direction == "bottom") {
        //     y += bottom;
        // }
    
}
}