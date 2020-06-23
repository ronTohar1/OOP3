abstract class Enemy extends Unit {

    private int experience;

    public Enemy(char charValue) {
        super(charValue);
    }

    @Override
    public boolean swap(Player player) {
        Fight(player);
        return false;
    }

    @Override
    public boolean accept(Tile tile) {
       return tile.swap(this);
    }
}
