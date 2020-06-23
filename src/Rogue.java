import java.util.List;

public class Rogue extends Player {

    FanOfKnives fanOfKnives;
    public Rogue(int cost){
        super();
        fanOfKnives=new FanOfKnives(cost);
    }

    @Override
    protected void LevelUp() {
        int additionalAttack=3*level;
        AddToAttack(additionalAttack);
        abilityLevelUp();
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
        if(fanOfKnives.currentEnergy<fanOfKnives.cost){}
            HandleMessage("Not enough energy to use special ability");
        else{
            List<Enemy> enemies= GetSurroundings(fanOfKnives.attackRange);
            //Fighting each enemy.
            enemies.forEach(super::Fight);
        }

    }
}

class FanOfKnives{
    private int maxEnergy=100;
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

    protected void LevelUp(){
        LevelUpCurrentEnergy();
    }

    protected void AddToCurrentEnergy(int amount){
        currentEnergy=Math.min(currentEnergy+10,maxEnergy);
    }

    protected void Tick(){
        int AdditionalEnergy=10;
       AddToCurrentEnergy(AdditionalEnergy);
    }
}