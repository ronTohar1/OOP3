import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Player extends Unit {

    private static final char charValue='@';
    protected int level;
    private ISurroundings surroundings;

    public Player(int experience) {
        super('@');
    }

    @Override
    public String toString() {
        return null;
    }

    protected abstract void LevelUp();
    protected abstract void CastAbility();

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
    }



    protected List<Enemy> GetSurroundings(int range){
        List<Tile> tiles = this.surroundings.GetSurroundings(position, range);
        //Perform CanFight on each object in the surroundings list.
        List<Enemy> unitsList = (tiles.stream().map(Tile::CanFight).filter(Objects::nonNull)).collect(Collectors.toList());
        return unitsList;
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
        this.Fight(attackPoints,enemy);
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
