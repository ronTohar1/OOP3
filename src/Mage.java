import java.util.List;
import java.util.stream.Collectors;

public class Mage extends Player {

    private Blizzard blizzard;
    public Mage(int manaPool,int manaCost,int abilityRange,int histCount,int spellPower) {
        super();
        blizzard = new Blizzard(manaPool,manaCost,abilityRange,histCount,spellPower);

    }

    @Override
    protected void LevelUp() {
        blizzard.LevelUp(this.level);
    }

    @Override
    protected void Tick() {
        blizzard.OnTick(this.level);
    }

    @Override
    public void CastAbility() {
        if(blizzard.currentMana<blizzard.manaCost){}
            HandleMessage("Not enough mana to use the special ability");
        else{
            blizzard.SubtractCurrentMana(blizzard.manaCost);
            int hits=0;
            List<Enemy> enemies=GetSurroundings(blizzard.abilityRange);
            while(hits<blizzard.histCount && !enemies.isEmpty()){
                int randomEnemyPos=RollDice(enemies.size()-1);
                super.Fight(blizzard.spellPower,enemies.get(randomEnemyPos));
                hits++;
            }
        }
    }
}

class Blizzard  {
    protected int manaPool;
    protected int currentMana;
    protected int manaCost;
    protected int spellPower;
    protected int abilityRange;
    protected int histCount;

    public Blizzard(int manaPool,int manaCost,int abilityRange,int histCount,int spellPower) {
        this.manaPool=manaPool;
        currentMana=manaPool/4;
        this.manaCost=manaCost;
        this.abilityRange=abilityRange;
        this.histCount=histCount;
        this.spellPower=spellPower;

    }

    private void OnTickCurrentMana(int level){
        currentMana=Math.min(manaPool,currentMana+level);
    }

    public void OnTick(int level) {
        OnTickCurrentMana(level);
    }


    private void LevelUpManaPool(int level){
        int additionalManaPool=25*level;
        manaPool+=additionalManaPool;
    }
    private void LevelUpCurrentMana(){
        currentMana=Math.min(currentMana+(manaPool/4),manaPool);
    }

    private void LevelUpSpellPower(int level){
        int additionalSpellPower=10*level;
        spellPower+=additionalSpellPower;
    }
    protected void LevelUp(int level){
        LevelUpManaPool(level);
        LevelUpCurrentMana();
        LevelUpSpellPower(level);
    }

    protected void AddToCurrentMana(int amount){
        currentMana=Math.min(currentMana+amount,manaPool);

    }

    protected void SubtractCurrentMana(int amount){
        currentMana=Math.max(currentMana-amount,0);
    }
    public boolean AbilityCast() {

    }
}