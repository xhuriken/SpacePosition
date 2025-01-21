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

    public String GetName(){
        return name;
    }
}
