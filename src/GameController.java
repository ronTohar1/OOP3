import javafx.geometry.Pos;

public class GameController {

    private GameBoard board;
    private Player player;

    public void MoveUp() {
        Position pos2 = new Position(player.position.getX(), player.position.getY() + 1);
        board.Swap(player.position, pos2);
        Tick();
    }

    public void MoveDown() {
        Position pos2 = new Position(player.position.getX(), player.position.getY() - 1);
        board.Swap(player.position, pos2);
        Tick();
    }

    public void MoveLeft() {
        Position pos2 = new Position(player.position.getX() - 1, player.position.getY());
        board.Swap(player.position, pos2);
        Tick();
    }

    public void MoveRight() {
        Position pos2 = new Position(player.position.getX() + 1, player.position.getY());
        board.Swap(player.position, pos2);
        Tick();
    }

    public void CastAbility() {
        Tick();
    }

    public void DoNothing() {
        Tick();
    }

    private void Tick() {
        board.Tick();
    }
}
