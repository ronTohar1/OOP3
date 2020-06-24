import com.sun.xml.internal.bind.v2.TODO;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Warrior extends Player {
    private AvengersShield avengersShield;

    public Warrior(int health,int attack,int defense,String name,int abilityCooldown) {
        super(health,attack,defense,name);
        avengersShield = new AvengersShield(abilityCooldown);
    }

    @Override
    protected String LevelUp() {
        int additionalHealth = 5 * level;
        int additionalAttack = 2 * level;
        int additionalDefense = level;
        AddToHealthPool(additionalHealth);
        AddToAttack(additionalAttack);
        AddToDefense(additionalDefense);
        return abilityLevelUp();
    }

    @Override
    private String abilityLevelUp() {
        return avengersShield.LevelUp();
    }

    @Override
    protected void Tick() {
        avengersShield.OnTick();
    }

    @Override
    public void CastAbility() {
        if (avengersShield.remainingCooldown > 0) {
            HandleMessage(this.name + " tried to cast " + avengersShield.name + " but the remaining cooldown is " + avengersShield.remainingCooldown);
        }
        else{
            HandleMessage(this.name+" used "+avengersShield.name);
            //Healing
            int AdditionalCurrHealth = 10 * defensePoints;
            AddToCurrentHealth(AdditionalCurrHealth);
            HandleMessage(this.name +" healed for "+ AdditionalCurrHealth);
            //Fighting
            List<Enemy> enemyList = GetSurroundings(avengersShield.attackRange);
            avengersShield.ResetCooldown();//Resets the special ability cooldown
            if (!enemyList.isEmpty()) {
                int rand = RollDice(enemyList.size() - 1);
                // TODO: 23/06/2020 check if need to cast to int.
                int damage = (int) (0.1 * unitHealth.pool);
                Unit randEnemy= enemyList.get(rand);
                super.Fight(damage,randEnemy);
            }
            avengersShield.remainingCooldown = avengersShield.abilityCooldown;

        }
    }

}


class AvengersShield {

    protected String name="Avengers Shield";
    int abilityCooldown;
    int remainingCooldown;
    final int attackRange = 3;

    public AvengersShield(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    private void SetAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }


    protected void ResetCooldown() {
        remainingCooldown = 0;
    }

    private void DecreaseRemainingCooldown() {
        if (remainingCooldown > 0)
            remainingCooldown -= 1;
    }

    protected String LevelUp() {
        ResetCooldown();
        return "";
    }

    public AvengersShield() {
    }

    public void OnTick() {
        if (remainingCooldown > 0)
            remainingCooldown -= 1;
    }
}
