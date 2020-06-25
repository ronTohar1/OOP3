public class Wall extends Tile  {

    public static final char charValue='#';
    public Wall() {
        super(charValue);
    }

    @Override
    protected void Tick() {

    }

    @Override
    public boolean accept(Visitor visitor) {
        return visitor.swap(this);
    }
}
