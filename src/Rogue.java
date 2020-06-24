import java.util.List;

public class Rogue extends Player {

    FanOfKnives fanOfKnives;
    public Rogue(int health,int attack,int defense,String name,int cost){
        super(health,attack,defense,name);
        fanOfKnives=new FanOfKnives(cost);
    }

    @Override
    protected String LevelUp() {
        int additionalAttack=3*level;
        AddToAttack(additionalAttack);
        return abilityLevelUp();
    }

    @Override
    private void abilityLevelUp() {
        fanOfKnives.LevelUp();
    }

    @Override
    protected void Tick() {
        fanOfKnives.Tick();
    }

    @Override
    public void CastAbility() {
        if(fanOfKnives.currentEnergy<fanOfKnives.cost) {
            HandleMessage("Not enough energy to use special ability");
        }
        else{
            List<Enemy> enemies= GetSurroundings(fanOfKnives.attackRange);
            //Fighting each enemy.
            enemies.forEach(p-> Fight(attackPoints,p));
        }

    }
}

class FanOfKnives{
    private final int maxEnergy=100;
    protected int cost;
    protected int currentEnergy;
    protected int attackRange;
    public FanOfKnives(int cost){
        this.cost=cost;
        currentEnergy=maxEnergy;
    }

    private void LevelUpCurrentEnergy(){
        currentEnergy=maxEnergy;
    }

    protected String LevelUp(){
        LevelUpCurrentEnergy();
        return "";
    }

    protected void AddToCurrentEnergy(int amount){
        currentEnergy=Math.min(currentEnergy+10,maxEnergy);
    }

    protected void Tick(){
        int AdditionalEnergy=10;
       AddToCurrentEnergy(AdditionalEnergy);
    }
}