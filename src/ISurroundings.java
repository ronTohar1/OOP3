import java.util.List;

public interface ISurroundings {

    //Should return the surroundings of the player
    public List<Enemy> GetSurroundings(int range);
}
