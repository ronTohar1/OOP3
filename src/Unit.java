import java.util.Random;

abstract public class Unit extends Tile {

    private MessageHandler msgHandler=MessageHandler.GetInstance();
    protected CombatSystem cb;
    protected String name;
    protected Health unitHealth;
    protected int attackPoints;
    protected int defensePoints;
    protected int experience;
    protected KillerObserver killer;

    protected abstract void Die();

    public Unit(KillerObserver killer, char charValue, String name, int attackPoints, int defensePoints) {
        super(charValue);
        cb=CombatSystem.getInstance();
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
        this.name=name;
        this.killer=killer;
    }

    //Return true if dies.
    public boolean SubtractCurrentHealth(int amountToSubtract) {
        unitHealth.amount -= amountToSubtract;
        if (unitHealth.amount  <= 0) {
            unitHealth.amount = 0;
            Die();
            return true;
        }
        return false;
    }

    protected void HandleMessage(String msg){
        msgHandler.HandleMessage(msg);
    }



    @Override
    public boolean swap(Empty empty) {
        switchPositions(empty);
        return true;
    }


    //Default fight option, using the basic attackPoints;
    protected boolean Fight(Unit toAttack){
       return Fight(attackPoints,toAttack);

    }
    protected boolean Fight(int MaxDamage,Unit toAttack){
        HandleMessage(name+" engaged in combat with "+toAttack.name);
        int attackAmount=RollDice(attackPoints);
        HandleMessage(name+" rolled "+attackAmount+" attack damage");
        int defenceAmount=RollDice(toAttack.defensePoints);
        HandleMessage(toAttack.name+" rolled "+defenceAmount+" defence points");
        int damage=attackAmount-defenceAmount;
        boolean died=false;
        if(damage>0){
            died=toAttack.SubtractCurrentHealth(damage);
            HandleMessage(name+" has dealt "+damage+" damage to "+toAttack.name);
        }
        return died;

    }


    protected int RollDice(int maxToRoll){
        Random rnd= new Random();
        return rnd.nextInt(maxToRoll+1);

    }
}

class Health {
    int pool;
    int amount;
}