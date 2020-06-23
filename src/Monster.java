public class Monster extends Enemy {

    private int visionRange;

    public Monster(char charValue,int visionRange) {
        super(charValue);
        this.visionRange=visionRange;
    }

    @Override
    protected Enemy CanFight() {
        return this;
    }


    @Override
    public void EnemyMove(Player player) {
        int dx=position.getDx(player.position);
        int dy=position.getDy(player.position);
        if(position.Range(player.position)<visionRange)
        {
            if(Math.abs(dx)>Math.abs(dy)) {
                if (dx > 0)
                    mover.MoveLeft(this);
                else
                    mover.MoveRight(this);
            }
            else{
                if(dy>0)
                    mover.MoveUp(this);
                else
                    mover.MoveDown(this);

            }
        }
        else
        {
            int num=RollDice(3);
            switch(num){
                case 0: mover.MoveUp(this);
                case 1: mover.MoveDown(this);
                case 2: mover.MoveLeft(this);
                case 3: mover.MoveRight(this);
            }
        }
    }
}
