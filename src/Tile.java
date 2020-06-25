public abstract class Tile implements Visited {

    protected char character;
    protected Position position;

    public Tile(char charValue) {
        character = charValue;
    }

    public Position getPosition(){
        return position;
    }

    public void SetPosition(int x, int y){
        position=new Position(x,y);
    }

    //distance between two tiles.
    public double range(Tile other) {
        return position.Range(other.position);
    }




    public String toString() {
        return character+"";
    }

    protected void switchPositions(Tile other) {
        Position temp = position;
        position = other.position;
        other.position = temp;
    }

    protected abstract void Tick();
}
