public class KillerObserver {
    private IKiller killer;
    public KillerObserver(IKiller killer)
    {
        this.killer=killer;
    }

    public void Kill(Player player) {
        killer.Kill(player);
    }
    public void Kill(Enemy enemy) {
        killer.Kill(enemy);
    }
}
