import java.util.List;
import java.util.stream.Collectors;

public class GameController implements IMover,IKiller{

    private GameBoard board;
    private Player player;
    List<Enemy> enemies;
    MessageHandler msgHandler= MessageHandler.GetInstance();

    public GameController(){
        IEnemyMove enemyMove=((enemy)-> enemy.EnemyMove(player));
        ISurroundings PlayerSurroundings=((range) -> {return enemies.stream().filter(e->e.position.Range(player.position)<range).collect(Collectors.toList());});

    }

    //Stops the game.
    private void EndGame(){
        msgHandler.HandleMessage("");
    }
    public void MoveUp(Unit unit) {
        Position pos2 = new Position(unit.position.getX(), unit.position.getY() + 1);
        board.Swap(unit.position, pos2);
        Tick();
    }

    public void MoveDown(Unit unit) {
        Position pos2 = new Position(unit.position.getX(), unit.position.getY() - 1);
        board.Swap(unit.position, pos2);
        Tick();
    }

    public void MoveLeft(Unit unit) {
        Position pos2 = new Position(unit.position.getX() - 1, unit.position.getY());
        board.Swap(unit.position, pos2);
        Tick();
    }

    public void MoveRight(Unit unit) {
        Position pos2 = new Position(unit.position.getX() + 1, unit.position.getY());
        board.Swap(unit.position, pos2);
        Tick();
    }

    public void MoveUp() {
        MoveUp(player);
    }

    public void MoveDown() {
        MoveDown(player);
    }

    public void MoveLeft() {
        MoveLeft(player);
    }

    public void MoveRight() {
        MoveRight(player);
    }

    public void CastAbility() {
        Tick();
    }

    public void DoNothing() {
        Tick();
    }

    //Making the player ticks, then , each enemy performs a move.
    private void Tick() {
        player.Tick();
        enemies.forEach(Tile::Tick);
    }

    @Override
    public void Kill(Player player) {
        EndGame();
    }

    @Override
    public void Kill(Enemy enemy) {
        enemies.remove(enemy);
        board.Kill(enemy.position);
        if(enemies.isEmpty())
            LevelUp();
    }
    private void LevelUp(){}
}
