import java.util.Arrays;

public class Monster extends Creature {

    public Monster() {}

    public Monster(int attack, int protection, int maxHealth, int[] damage) {
        super(attack, protection, maxHealth, damage);
    }

    @Override
    public void die() {
        this.state = State.DIE;
        System.out.println("Монстр мертв...");
    }

    @Override
    public String toString() {
        return "Monster{" +
                "атака=" + attack +
                ", защита=" + protection +
                ", здоровье=" + currentHealth +
                ", исцеления=" + healing +
                ", урон=" + Arrays.toString(damage) +
                '}';
    }
}
