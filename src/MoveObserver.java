public class MoveObserver {

    IMover mover;
    public void MoveUp(Unit unit){
        mover.MoveUp(unit);
    }
    public void MoveDown(Unit unit){
        mover.MoveDown(unit);
    }
    public void MoveLeft(Unit unit){
        mover.MoveLeft(unit);
    }
    public void MoveRight(Unit unit){
        mover.MoveRight(unit);
    }
    public MoveObserver(IMover mover){
        this.mover=mover;
    }

}
