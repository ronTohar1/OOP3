public abstract class Player extends Unit {

    private static final char charValue='@';
    private int experience;
    protected int level;

    public Player() {
        super('@');
    }

    @Override
    public String toString() {
        return null;
    }
    protected abstract void LevelUp();



    protected void UponLevelUp(){
        int minForLevelUp=50*level;
        if(experience<minForLevelUp)
            throw new IllegalArgumentException("You cant level up yet");
        level++;
        experience=experience-minForLevelUp;
        int additionalHealth=10*level;
        int additionalAttack=4*level;
        int additionalDefense=level;

        AddToAttack(additionalAttack);
        AddToDefense(additionalDefense);
        AddToHealthPool(additionalHealth);
        FillCurrentHealth();

        LevelUp();
        abilityLevelUp();
    }

    protected void Die(){

    }


    protected  void AddToHealthPool(int amountToAdd) {
        if (amountToAdd < 1)
            throw new IllegalArgumentException("Health Pool can only be increased");

        unitHealth.pool += amountToAdd;
    }

    protected  void FillCurrentHealth(){unitHealth.amount=unitHealth.pool;}

    protected  void AddToCurrentHealth(int amountToAdd) {
        unitHealth.amount += amountToAdd;
        if (unitHealth.amount + amountToAdd > unitHealth.pool)
            unitHealth.amount = unitHealth.pool;
    }

    protected  void AddToAttack(int toAdd) {
        attackPoints += toAdd;
    }

    protected    void AddToDefense(int toAdd) {
        defensePoints += toAdd;
    }


    @Override
    public boolean swap(Enemy enemy) {
        Fight(enemy);
        return false;
    }

    @Override
    public boolean swap(Empty empty) {
        switchPositions(empty);
        return true;
    }

    @Override
    public boolean accept(Tile tile) {
        return tile.swap(this);
    }
}
