import java.util.List;

public class Mage extends Player {

    private Blizzard blizzard;
    public Mage(int health,int attack,int defense,String name,int manaPool,int manaCost,int spellPower,int histCount,int abilityRange) {
        super(health,attack,defense,name);
        blizzard = new Blizzard(manaPool,manaCost,abilityRange,histCount,spellPower);

    }

    @Override
    protected String LevelUp() {
       return blizzard.LevelUp(this.level);
    }

    @Override
    protected void Tick() {
        blizzard.OnTick(this.level);
    }

    @Override
    public void CastAbility() {
        if(blizzard.currentMana<blizzard.manaCost) {
            HandleMessage(this.name + " tried to cast " + blizzard.name + " but the current mana (" + blizzard.currentMana + ") is less than the mana cost (" + blizzard.manaCost+")");
        }
        else{
            HandleMessage(this.name+" used "+ blizzard.name);
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
    protected String name="Blizzard";
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


    private int LevelUpManaPool(int level){
        int additionalManaPool=25*level;
        manaPool+=additionalManaPool;
        return additionalManaPool;
    }
    private void LevelUpCurrentMana(){
        currentMana=Math.min(currentMana+(manaPool/4),manaPool);
    }

    private int LevelUpSpellPower(int level){
        int additionalSpellPower=10*level;
        spellPower+=additionalSpellPower;
        return additionalSpellPower;
    }
    protected String LevelUp(int level){
        int Manadif=LevelUpManaPool(level);
        LevelUpCurrentMana();
        int spellpowerDif=LevelUpSpellPower(level);
        String msg="+"+Manadif+" to mana pool , +"+spellpowerDif+" spell power";
        return msg;
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