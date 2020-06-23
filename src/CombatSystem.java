import java.util.Random;

public class CombatSystem {

    private static final CombatSystem cb= new CombatSystem();

    private CombatSystem(){

    }

    //Returns the amount of damage that was done, or 0 else.
    public int Fight(int attackPoints,int defencePoints){
        int attackAmount=RollDice(attackPoints);
        int defenceAmount=RollDice(defencePoints);
        if(attackAmount>defenceAmount)
            return attackAmount;
        return 0;
    }

    public static CombatSystem getInstance(){
         return cb;
    }

    private int RollDice(int maxToRoll){
        Random rnd= new Random();
        return rnd.nextInt(maxToRoll+1);

    }
}
