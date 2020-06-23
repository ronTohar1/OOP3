import com.sun.xml.internal.bind.v2.TODO;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Warrior extends Player {
    private AvengersShield avengersShield;

    public Warrior(int abilityCooldown) {
        super();
        avengersShield = new AvengersShield(abilityCooldown);
    }

    @Override
    protected void LevelUp() {
        int additionalHealth = 5 * level;
        int additionalAttack = 2 * level;
        int additionalDefense = level;
        AddToHealthPool(additionalHealth);
        AddToAttack(additionalAttack);
        AddToDefense(additionalDefense);
        abilityLevelUp();
    }

    @Override
    private void abilityLevelUp() {
        avengersShield.LevelUp();
    }

    @Override
    protected void Tick() {
        avengersShield.OnTick();
    }

    @Override
    public void CastAbility() {
        if (avengersShield.remainingCooldown > 0){}
            HandleMessage("The special ability is still on cooldown");
        List<Enemy> surroundings=GetSurroundings(avengersShield.attackRange);
        avengersShield.ResetCooldown();//Resets the special ability cooldown
        if (!surroundings.isEmpty()) {
            int rand = RollDice(enemiesList.size() - 1);
            int damage = (int) (0.1 * unitHealth.pool);
            surroundings.get(rand).SubtractCurrentHealth(damage);
        }
        avengersShield.remainingCooldown = avengersShield.abilityCooldown;
        int AdditionalCurrHealth=10 * defensePoints;
        AddToCurrentHealth(10 * defensePoints);
    }

}


class AvengersShield {

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

    protected void LevelUp() {
        ResetCooldown();
    }

    public AvengersShield() {
    }

    public void OnTick() {
        if (remainingCooldown > 0)
            remainingCooldown -= 1;
    }
}
