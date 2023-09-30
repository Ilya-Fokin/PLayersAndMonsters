public class Creature implements Fight {
    protected int attack;
    protected int protection;
    protected int maxHealth;
    protected double currentHealth;
    public int healing = 4;
    protected int[] damage;
    protected State state;


    public Creature() {}

    public Creature(int attack, int protection, int maxHealth, int[] damage) {
            this.attack = attack;
            this.protection = protection;
            this.maxHealth = maxHealth;
            this.currentHealth = maxHealth;
            this.damage = damage;
            this.state = State.ALIVE;
    }

    @Override
    public void doHealing() {
        if (currentHealth == maxHealth) {
            System.out.println("У Вас максимальное здоровье");
        } if (healing == 0) {
            System.out.println("Запасы лексира исчерпаны");
        } if (currentHealth < maxHealth) {
            currentHealth = currentHealth + maxHealth*0.3;
            if (currentHealth >= maxHealth) {
                currentHealth = maxHealth;
            }
            System.out.println("Ваше здоровье восполнено - " + currentHealth);
            healing--;
        }
    }

    @Override
    public void die() {}

    @Override
    public Creature doFight(Creature creature) throws InterruptedException {
        int attackModifier = calculateAttackModifier(creature);
        for (int i = 0; i < attackModifier; i++) {
            int result = (int) ((Math.random() * 6) + 1);
            System.out.println("--------------------\nВыпал кубик - " + result);
            if (result == 5 || result == 6) {
                System.out.println("Атакуем существо!!!");
                int damage = this.damage[(int) ((Math.random() * this.damage.length))];
                System.out.println("Нанесённый урон " + damage + "\n--------------------");
                creature.currentHealth = creature.currentHealth - damage;
                break;
            }
            Thread.sleep(1000);
        }
        if (creature.currentHealth < 1) {
            creature.die();
        }
        return creature;
    }

    public int calculateAttackModifier(Creature creature) {
        int attackModifier = this.attack - creature.protection + 1;
        if (attackModifier < 0) {
            attackModifier = 1;
        }
        return attackModifier;
    }
}
