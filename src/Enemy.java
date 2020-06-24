abstract class Enemy extends Unit {

    protected MoveObserver mover;
    protected IEnemyMove enemyMover;
    private int visionRange;

    public Enemy(char charValue,int visionRange) {
        super(charValue);
    }

    public void SetMover(MoveObserver mover){
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
