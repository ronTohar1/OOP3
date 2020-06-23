public class Warrior extends Player {

    private AvengersShield avengersShield;
    public Warrior(int abilityCooldown) {
        super();
        avengersShield= new AvengersShield(abilityCooldown);
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

    }

}


class AvengersShield  {

     int abilityCooldown;
     int remainingCooldown;

    public AvengersShield(int abilityCooldown)
    {
        this.abilityCooldown=abilityCooldown;

    }

     private void SetAbilityCooldown(int abilityCooldown){
         this.abilityCooldown=abilityCooldown;
     }


     private void ResetCooldown(){
         remainingCooldown=0;
     }
     private void DecreaseRemainingCooldown(){
         if(remainingCooldown>0)
             remainingCooldown-=1;
     }

     void LevelUp(){
         ResetCooldown();
     }
    public AvengersShield(){}

    public void OnTick() {
        if (remainingCooldown >0)
            remainingCooldown -= 1;
    }

    public void AbilityCast() {

    }
}
