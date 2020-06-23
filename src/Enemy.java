abstract class Enemy extends Unit {

    private int experience;
    protected MoveObserver mover;

    public Enemy(char charValue,MoveObserver mover) {
        super(charValue);
        this.mover=mover;
    }

    public abstract void EnemyMove(Player player);

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
