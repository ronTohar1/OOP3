public class Trap extends Enemy{


    private int visibilityTime;
    private int invisibilityTime;
    private int tickCount;
    private boolean visible;
    private static int vRange=2;

    public Trap(char charValue,int health,int attack,int defense,String name,int experience,int visibilityTime,int invisibilityTime) {
        super(health,attack,defense,name,charValue,vRange,experience);
        tickCount=0;
        visible=true;
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
    }

    @Override
    public void EnemyMove(Player player) {
        visible=tickCount<visibilityTime;
        if(tickCount==visibilityTime+invisibilityTime)
            tickCount=0;
        else
            tickCount++;
        if(position.Range(player.position)<visionRange)
            Fight(player);
    }

    @Override
    public String toString() {
        if(!visible)
            return Empty.charValue +"";
        else
            return character+"";
    }


}
