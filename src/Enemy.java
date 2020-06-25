abstract class Enemy extends Unit {

    protected MoveObserver mover;//Moving the enemy up ,down,left or right.
    protected IEnemyMove enemyMove;//Lambda expression->Performing an enemyMove and gives the player as an argument.
    protected int visionRange;
    private int xpValue;

    public Enemy(int health,int attack,int defense,String name,char charValue,int visionRange,int experienceValue) {
        super(health,attack,defense,name,charValue);
        this.visionRange=visionRange;
        this.xpValue=experienceValue;

    }

    public int GetXpValue(){
        return this.xpValue;
    }
    public void SetEnemyMove(IEnemyMove enemyMove){this.enemyMove =enemyMove;}
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
        enemyMove.MakeMove(this);
    }
    @Override
    public boolean accept(Visitor visitor) {
       return visitor.swap(this);
    }

    @Override
    public String DescribeMe() {
        return "Vision Range: "+visionRange;
    }


}
