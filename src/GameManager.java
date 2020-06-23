import sun.misc.GC;

public class GameManager {

    private GameController gameController;

    public void MoveUp() {
        gameController.MoveUp();
    }

    public void MoveDown() {
        gameController.MoveDown();
    }

    public void MoveLeft() {
        gameController.MoveLeft();
    }

    public void MoveRight() {
        gameController.MoveRight();
    }

    public void CastAbility() {
        gameController.CastAbility();
    }

    public void DoNothing() {
        gameController.DoNothing();
    }

}
