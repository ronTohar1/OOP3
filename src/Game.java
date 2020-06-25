import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class Game{

    public static void main(String[] args) {
        try{
            List<String> levelFiles= Files.list(Paths.get(args[0])).sorted().map(Path::toString).collect(Collectors.toList());
            List<List<String>> levels=new LinkedList<>();
            for (String levelPath: levelFiles){
                levels.add(Files.readAllLines(Paths.get(levelPath)));

            }
            GameManager gameManager= new GameManager(levels);
            gameManager.Start();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
