public class Trap extends Enemy{


    private int visibilityTime;
    private int invisibilityTime;
    private int tickCount;
    private boolean visible;

    public Trap(char charValue,int visibilityTime,int invisibilityTime) {
        super(charValue);
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
        if(position.Range(player.position)<2)
            Fight(player);
    }


}
