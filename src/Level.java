import javafx.geometry.Pos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Level {
    public Tile[][] levelBoard;
    public List<Enemy> currEnemies;
    public Position PlayerPos;

    public Level(List<String> lvl, Map<Character, Supplier<Enemy>> enemies, Player player,GameController gc){
        int height=lvl.size();
        int width=lvl.get(0).length();
        levelBoard= new Tile[height][width];
        currEnemies=new LinkedList<Enemy>();
        int rowNum=0;
        KillerObserver killer= new KillerObserver(gc);
        MoveObserver mover= new MoveObserver(gc);
        for(String row: lvl){
            for(int i=0;i<row.length();i++){
                char curr=row.charAt(i);
                Tile toAdd;

                switch (curr){
                    case Empty.charValue:toAdd=new Empty();break;
                    case Wall.charValue: toAdd=new Wall();break;
                    case Player.charValue: toAdd= player;PlayerPos=new Position(rowNum,i);break;
                    default: {
                        Enemy e=enemies.get(curr).get();
                        currEnemies.add(e);
                        e.SetKillerObserver(killer);
                        e.SetMover(mover);
                        toAdd=e;
                    };
                }
                toAdd.SetPosition(rowNum,i);
                levelBoard[rowNum][i]=toAdd;
            }
            rowNum++;
        }
    }

    private Enemy getEnemyByChar(char charValue,Enemy[] enemies){
        for(Enemy e : enemies){
            if(e.character==charValue)
                return e;
        }
        return null;
    }
}
