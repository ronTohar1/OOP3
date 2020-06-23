public class Monster extends Enemy {

    private int visionRange;

    public Monster(char charValue,int visionRange) {
        super(charValue);
        this.visionRange=visionRange;
    }

    @Override
    protected void Tick() {

    }
}
