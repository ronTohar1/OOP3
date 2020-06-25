import com.sun.xml.internal.ws.resources.HandlerMessages;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GameController implements IMover,IKiller{

    private GameBoard board;
    private Player player;
    private List<Enemy> levelEnemies;
    private List<Level> Levels;
    private int currLevelNum=0;
    private MessageHandler msgHandler= MessageHandler.GetInstance();
    private boolean gameInProgress;


    public GameController(Player player, Map<Character, Supplier<Enemy>> enemies, List<List<String>> levels){
        //Setting lambda expression to player for its surroundings (this lambda filters enemies in a given range).
        ISurroundings PlayerSurroundings=((range) -> {return levelEnemies.stream().filter(e->e.getPosition().Range(player.getPosition())<range).collect(Collectors.toList());});
        player.SetSurroundings(PlayerSurroundings);
        //Setting fields.
        this.player=player;
        gameInProgress=true;
        this.Levels=BuildLevels(levels,enemies,player);
        //Initial level is 0;
        LevelUp();
        PrintBoard();

    }

    //Gets a sorted array of levels of type string and converts them into a list of level objects.
    private List<Level> BuildLevels(List<List<String>> levels,Map<Character, Supplier<Enemy>> enemies,Player player){
        List<Level> returnLevels= new LinkedList<Level>();
        for(List<String> lvl: levels){
            Level l= new Level(lvl,enemies,player,this);
            returnLevels.add(l);
        }
        return returnLevels;
    }
    //Stops the game, player lost.
    private void LoseGame(){
        msgHandler.HandleMessage("Game Over");
        EndGame();
    }
    //Game finished and the player won.
    private void GameWon(){
        msgHandler.HandleMessage("You Won!");
        EndGame();
    }
    //When the game has finished.
    private void EndGame(){
        gameInProgress=false;
    }

    public boolean isGameInProgress(){return gameInProgress;}

    public void MoveUp(Unit unit) {
        Position pos2 = new Position(unit.position.getX()-1, unit.position.getY());
        board.Swap(unit, pos2);
    }

    public void MoveDown(Unit unit) {
        Position pos2 = new Position(unit.position.getX()+1, unit.position.getY());
        board.Swap(unit, pos2);
    }

    public void MoveLeft(Unit unit) {
        Position pos2 = new Position(unit.position.getX(), unit.position.getY()-1);
        board.Swap(unit, pos2);
    }

    public void MoveRight(Unit unit) {
        Position pos2 = new Position(unit.position.getX(), unit.position.getY()+1);
        board.Swap(unit, pos2);
    }

    public void MoveUp() {
        MoveUp(player);
        Tick();
    }

    public void MoveDown() {
        MoveDown(player);
        Tick();
    }

    public void MoveLeft() {
        MoveLeft(player);
        Tick();
    }

    public void MoveRight() {
        MoveRight(player);
        Tick();
    }

    public void CastAbility() {
        player.CastAbility();
        Tick();
    }

    public void DoNothing() {
        Tick();
    }

    //Making the player ticks, then , each enemy performs a move.
    private void Tick() {
        player.Tick();
        levelEnemies.forEach(Tile::Tick);
        //Turn ended, printing board.
        PrintBoard();
    }

    private void PrintBoard(){
        String boardPrint= board.PrintBoard();
        msgHandler.HandleMessage(boardPrint);
        msgHandler.HandleMessage(player.Describe());
    }
    @Override
    public void Kill(Player player) {
        LoseGame();
    }

    @Override
    public void Kill(Enemy enemy) {
        levelEnemies.remove(enemy);
        board.Kill(enemy.position);
        if(levelEnemies.isEmpty())
            LevelUp();
    }

    //Leveling up if there are levels left, or else, wins the game.
    private void LevelUp(){
        if(Levels.isEmpty())
            GameWon();
        else{
            currLevelNum++;
            msgHandler.HandleMessage("Level: "+currLevelNum);
            Level currLevel=Levels.remove(0);
            //Setting the new enemies of the current level and the current new board.
            board=new GameBoard( currLevel.levelBoard);
            levelEnemies=currLevel.currEnemies;
            //Setting the player's new position on the current board.
            Position playerPos=currLevel.PlayerPos;
            player.SetPosition(playerPos.getX(),playerPos.getY());

            //Setting the labmda expression of the current enemies.
            IEnemyMove enemyMove=((enemy)-> enemy.EnemyMove(player));
            levelEnemies.stream().forEach(p->p.SetEnemyMove(enemyMove));

            msgHandler.HandleMessage("You have reached a new map! (map level: "+currLevelNum+")");

        }
    }
}
