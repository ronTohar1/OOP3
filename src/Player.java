import jdk.nashorn.internal.ir.WhileNode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Player extends Unit {

    private static final char charValue='@';
    protected int level;
    private ISurroundings surroundings;

    public Player(int health,int attack,int defense,String name) {
        super('@');
    }

    public void SetSurroundings(ISurroundings surroundings){
        this.surroundings=surroundings;
    }

    @Override
    public String toString() {
        return null;
    }

    protected abstract String LevelUp();
    protected abstract void CastAbility();

    protected void UponLevelUp(){
        int minForLevelUp=50*level;
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
            String levelUpString=LevelUp();
            minForLevelUp=50*level;
            int healthDif=this.unitHealth.amount-prevHealth;
            int AttackDif=this.attackPoints-prevAttack;
            int DefenseDif=this.defensePoints-prevDefence;
            String msg=this.name+" reached level "+this.level+": +"+healthDif+" health, +"+AttackDif+" attack points, +"+DefenseDif+" defense points \n"+levelUpString;
            HandleMessage(msg);
        }

    }



    protected List<Enemy> GetSurroundings(int range){
        List<Enemy> tiles = this.surroundings.GetSurroundings(range);
    }
    private void AddExperience(int exp){
        experience+=exp;
        if(experience>=50*level)
            UponLevelUp();
    }
    @Override
    protected void Die(){
        this.character='X';
        killer.Kill(this);
    }

    protected void  Fight(int maxDamage,Enemy toAttack){
        if(super.Fight(maxDamage,toAttack)){
            AddExperience(toAttack.experience);
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
    public boolean swap(Enemy enemy) {
        this.Fight(enemy);
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
