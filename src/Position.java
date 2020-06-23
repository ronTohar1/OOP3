public class Position
{
    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double Range(Position other){
        return Math.sqrt(Math.pow(x-other.x,2)+Math.pow(y-other.y,2));
    }
}
