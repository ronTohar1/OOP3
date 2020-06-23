public interface  Visitor {
    boolean swap(Enemy enemy);
    boolean swap(Player player);
    boolean swap(Empty empty);
    boolean swap(Wall wall);
}
