import java.util.Arrays;

public class Player extends Creature {

    public Player() {
    }

    public Player(int attack, int protection, int maxHealth, int[] damage) {
        super(attack, protection, maxHealth, damage);
    }

    @Override
    public void die() {
        this.state = State.DIE;
        System.out.println("Игрок мертв...");
    }

    @Override
    public String toString() {
        return "Player{" +
                "атака=" + attack +
                ", защита=" + protection +
                ", здоровье=" + currentHealth +
                ", исцеления=" + healing +
                ", урон=" + Arrays.toString(damage) +
                '}';
    }
}
