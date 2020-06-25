import jdk.nashorn.internal.ir.WhileNode;

import java.util.List;


public abstract class Player extends Unit {

    public static final char charValue='@';
    protected int level;
    private ISurroundings surroundings;
    private int experience;
    private final int levelUpMultiplier=50;

    public Player(int health,int attack,int defense,String name) {
        super(health,attack,defense,name,charValue);
        this.level=0;
        this.experience=0;
    }

    protected abstract String DescribeSub();
    
    @Override
    public String DescribeMe() {
        String msg="Level: "+level+", Expereience: "+experience+", "+DescribeSub();
        return msg;
    }

    public void SetSurroundings(ISurroundings surroundings){
        this.surroundings=surroundings;
    }

    protected abstract String LevelUp();
    protected abstract void CastAbility();

    protected void UponLevelUp(){
        int minForLevelUp=levelUpMultiplier*level;
        while(experience>=minForLevelUp) {
            level++;
            experience = experience - minForLevelUp;
            int prevHealth=this.unitHealth.amount;
            int prevAttack=this.attackPoints;
            int prevDefence=this.defensePoints;
            int additionalHealth = 10 * level;
            int additionalAttack = 4 * level;
            int additionalDefense = level;
            AddToAttack(additionalAttack);
            AddToDefense(additionalDefense);
            AddToHealthPool(additionalHealth);
            FillCurrentHealth();
            String levelUpString=LevelUp();//Getting a level up string from the sub classes, and executing the sub classes level up methods.
            minForLevelUp=50*level;
            int healthDif=this.unitHealth.amount-prevHealth;
            int AttackDif=this.attackPoints-prevAttack;
            int DefenseDif=this.defensePoints-prevDefence;
            String msg=GetName()+" reached level "+this.level+": +"+healthDif+" health, +"+AttackDif+" attack points, +"+DefenseDif+" defense points \n"+levelUpString;
            HandleMessage(msg);
        }
    }

    protected List<Enemy> GetSurroundings(int range){
        List<Enemy> tiles = this.surroundings.GetSurroundings(range);
        return tiles;
    }

    private void AddExperience(int exp){
        experience+=exp;
        if(experience>=levelUpMultiplier*level)
            UponLevelUp();
    }
    @Override
    protected void Die(){
        this.character='X';
        killer.Kill(this);
    }

    @Override
    public boolean swap(Enemy enemy) {

        if(this.Fight(enemy)){
            HandleMessage(GetName()+" has gained "+enemy.GetXpValue()+" XP by killing "+enemy.GetName()+"\n");

            AddExperience(enemy.GetXpValue());
        }
        return false;
    }

    protected void  Fight(int maxDamage,Enemy toAttack){
        if(super.Fight(maxDamage,toAttack)){
            HandleMessage(GetName()+" has gained "+toAttack.GetXpValue()+" XP by killing "+toAttack.GetName()+"\n");
            AddExperience(toAttack.GetXpValue());
        }
    }


    protected  void AddToHealthPool(int amountToAdd) {
        unitHealth.pool += amountToAdd;
    }

    protected  void FillCurrentHealth(){unitHealth.amount=unitHealth.pool;}

    protected  void AddToCurrentHealth(int amountToAdd) {
        unitHealth.amount += amountToAdd;
        if (unitHealth.amount + amountToAdd > unitHealth.pool)
            unitHealth.amount = unitHealth.pool;
    }

    protected void AddToAttack(int toAdd) {
        attackPoints += toAdd;
    }

    protected void AddToDefense(int toAdd) {
        defensePoints += toAdd;
    }





    @Override
    public boolean accept(Visitor visitor) {
        return visitor.swap(this);
    }
}
