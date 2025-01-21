import java.util.Scanner;

public class mouvement {
    
    short top = 1; 
    short left = 1;
    short right = -1;
    short bottom = -1;

    mouvement(short top, short left, short right, short bottom, short x, short y) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the direction you want to move: ");
        String direction = sc.nextLine();

        if (direction == "top") {
            y += top;
        } else if (direction == "left") {
            x += left;
        } else if (direction == "right") {
            x += right;
        } else if (direction == "bottom") {
            y += bottom;
        }
        
    }
}
