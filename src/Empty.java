public class Empty extends Tile {

    public static final char charValue ='.';

    public Empty(){ super(charValue);
    }

    @Override
    public void Tick() {
    }

    @Override
    public boolean accept(Visitor visitor) {
        return visitor.swap(this);
    }
}
