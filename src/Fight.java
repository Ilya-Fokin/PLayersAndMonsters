public interface Fight {
    Creature doFight(Creature creature) throws InterruptedException;
    void doHealing();
    void die();
}
