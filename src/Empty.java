public class Empty extends Tile {


    public Empty(){
        super('.');
    }
    @Override
    public String toString() {
return null;
    }

    @Override
    public void Tick() {

    }

    @Override
    public boolean swap(Enemy enemy) {
        swap(enemy);
        return true;
    }

    @Override
    public boolean swap(Player player) {
        swap(player);
        return true;
    }
    @Override
    public boolean accept(Tile tile) {
        return tile.swap(this);
    }
}
