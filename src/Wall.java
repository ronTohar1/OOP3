public class Wall extends Tile  {


    public Wall(char charValue) {
        super(charValue);
    }

    @Override
    protected void Tick() {

    }


    @Override
    public boolean accept(Tile tile) {
        return tile.swap(this);
    }
}
