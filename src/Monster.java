public class Monster extends Enemy {


    public Monster(char charValue,int health,int attack,int defense,String name,int visionRange,int experience) {
        super(health,attack,defense,name,charValue,visionRange,experience);
    }
    @Override
    public void EnemyMove(Player player) {
        int dx=position.getDx(player.position);
        int dy=position.getDy(player.position);
        if(position.Range(player.position)<visionRange)
        {
            if(Math.abs(dx)>Math.abs(dy)) {
                if (dx > 0)
                    mover.MoveUp(this);
                else
                    mover.MoveDown(this);
            }
            else{
                if(dy>0)
                    mover.MoveLeft(this);
                else
                    mover.MoveRight(this);

            }
        }
        else
        {
            int num=RollDice(3);
            switch(num){
                case 0: mover.MoveUp(this); break;
                case 1: mover.MoveDown(this);break;
                case 2: mover.MoveLeft(this);break;
                case 3: mover.MoveRight(this);break;
            }
        }
    }




}
