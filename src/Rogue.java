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

    }
}

class FanOfKnives{
    private int defaultCurrentEnergy=100;
    int cost;
    int currentEnergy;

    public FanOfKnives(int cost){
        this.cost=cost;
        currentEnergy=defaultCurrentEnergy;
    }

    private void LevelUpCurrentEnergy(){
        currentEnergy=defaultCurrentEnergy;
    }

    protected void LevelUp(){
        LevelUpCurrentEnergy();
    }

    private void OnTickCurrentEnergy(){
        currentEnergy=Math.min(currentEnergy+10,100);
    }

    protected void Tick(){
        OnTickCurrentEnergy();
    }
}