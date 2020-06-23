public class Mage extends Player {

    private Blizzard blizzard;
    public Mage(int manaPool,int manaCost,int abilityRange,int histCount,int spellPower) {
        super();
        blizzard = new Blizzard(manaPool,manaCost,abilityRange,histCount,spellPower);

    }

    @Override
    protected void LevelUp() {
        abilityLevelUp();
    }

    @Override
    private void abilityLevelUp() {
        blizzard.LevelUp(this.level);
    }

    @Override
    protected void Tick() {
        blizzard.OnTick(this.level);
    }

    @Override
    public void CastAbility() {

    }
}

class Blizzard  {
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int abilityRange;
    private int histCount;

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

    public void AbilityCast() {

    }
}