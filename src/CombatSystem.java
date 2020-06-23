import java.util.Random;

public class CombatSystem {

    private static final CombatSystem cb= new CombatSystem();

    private CombatSystem(){

    }

    //Returns the amount of damage that was done, or 0 else.


    public static CombatSystem getInstance(){
         return cb;
    }


}
