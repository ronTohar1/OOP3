abstract public class Unit extends Tile {

    protected CombatSystem cb;
    private String name;
    protected Health unitHealth;
    protected int attackPoints;
    protected int defensePoints;


    public Unit(char charValue,String name,int attackPoints,int defensePoints) {
        super(charValue);
        cb=CombatSystem.getInstance();
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
        this.name=name;
    }

    //Return true if dies.
    public boolean SubtractCurrentHealth(int amountToSubtract) {
        unitHealth.amount -= amountToSubtract;
        if (unitHealth.amount  <= 0) {
            unitHealth.amount = 0;
            return true;
        }
        return false;
    }



    @Override
    public boolean swap(Empty empty) {
        switchPositions(empty);
        return true;
    }

    protected abstract void Die();

    protected boolean Fight(Unit toAttack){
       if(toAttack.SubtractCurrentHealth(cb.Fight(this.attackPoints,toAttack.defensePoints)))
       {
           toAttack.Die();
       }
    }
}

class Health {
    int pool;
    int amount;
}