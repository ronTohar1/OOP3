abstract class Enemy extends Unit {

    protected MoveObserver mover;
    protected IEnemyMove enemyMover;

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

    public void Die(){
        killer.Kill(this);
    }

    public void Tick(){
        enemyMover.MakeMove(this);
    }
    @Override
    public boolean accept(Tile tile) {
       return tile.swap(this);
    }
}
