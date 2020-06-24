public abstract class Tile implements Visitor, Visited {

    protected char character;
    protected Position position;

    public Tile(char charValue) {
        character = charValue;

    }

    //distance between two tiles.
    public double range(Tile other) {
        return position.Range(other.position);
    }

    public boolean swapTwoTiles(Tile tile) {
        return tile.accept(this);
    }

    @Override
    public boolean swap(Wall wall) {
        return false;
    }

    @Override
    public boolean swap(Player player) {
        return false;
    }

    @Override
    public boolean swap(Enemy enemy) {
        return false;
    }

    @Override
    public boolean swap(Empty empty) {
        return false;
    }

    public String toString() {
        return null;
    }

    protected void switchPositions(Tile other) {
        Position temp = position;
        position = other.position;
        other.position = temp;
    }


    protected abstract void Tick();
}
