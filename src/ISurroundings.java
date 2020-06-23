import java.util.List;

public interface ISurroundings {

    public List<Tile> GetSurroundings(Position pos, int range);
}
