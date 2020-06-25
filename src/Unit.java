import java.lang.invoke.SwitchPoint;
import java.util.Random;

abstract public class Unit extends Tile implements Visitor {

    private MessageHandler msgHandler=MessageHandler.GetInstance();
    private String name;
    protected Health unitHealth;
    protected int attackPoints;
    protected int defensePoints;
    protected KillerObserver killer;

    protected abstract void Die();

    public boolean swapTwoTiles(Tile tile) {
        return tile.accept(this);
    }

    @Override
    public boolean swap(Wall wall) {
        return false;
    }

    @Override
    public boolean swap(Player player) {
        switchPositions(player);
        return true;
    }

    @Override
    public boolean swap(Enemy enemy) {
        switchPositions(enemy);
        return true;
    }

    @Override
    public boolean swap(Empty empty) {
        switchPositions(empty);
        return true;
    }

    public Unit(int health,int attackPoints,int defensePoints,String name,char charValue) {
        super(charValue);
        unitHealth=new Health(health);
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
        this.name=name;
    }

    protected  abstract String DescribeMe();

    public String Describe(){
       return  GetName()+":  Health: "+unitHealth.amount+"/"+unitHealth.pool+"" +
                ", Attack: "+attackPoints+" Defense: "+defensePoints+"\n, "+DescribeMe();
    }

    public void SetKillerObserver(KillerObserver killer)
    {this.killer=killer;}


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

    //Default fight option, using the basic attackPoints;
    protected boolean Fight(Unit toAttack){
        HandleMessage(name+" engaged in combat with "+toAttack.name);
        int attackAmount=RollDice(attackPoints);
        HandleMessage(name+" rolled "+attackAmount+" attack damage");
        return Fight(attackAmount,toAttack);

    }
    //Tries to deal that damage exactly.
    protected boolean Fight(int DamageToDeal,Unit toAttack){

        int defenceAmount=RollDice(toAttack.defensePoints);
        HandleMessage(toAttack.name+" rolled "+defenceAmount+" defence points");

        //Dealing the damage.
        int damage=DamageToDeal-defenceAmount;
        boolean died=false;
        if(damage>0){
            died=toAttack.SubtractCurrentHealth(damage);
            HandleMessage(name+" has dealt "+damage+" damage to "+toAttack.name);
        }
        else
            HandleMessage(this.name+" dealt no damage to "+toAttack.name);

        //Describing the information of the units after combat.
        HandleMessage(Describe());
        HandleMessage(toAttack.Describe());
        if(died)
            HandleMessage(this.name+" killed "+toAttack.name);
        else
            HandleMessage("");


        return died;

    }


    protected int RollDice(int maxToRoll){
        Random rnd= new Random();
        return rnd.nextInt(maxToRoll+1);

    }

    protected String GetName(){return name;}
}

class Health {
    int pool;
    int amount;
    public Health(int HP){
        pool=HP;
        this.amount=pool;
    }
}