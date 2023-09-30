import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;
import java.io.Console;

public class Main {
    public static void main(String[] args) {
        List<Creature> creatures = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("""
                    ---------------------------------------------------------
                    Вы попали на поле битвы
                    Создайте новое существо или выберите из существующих

                    0. Создать существо""");
            if (!creatures.isEmpty()) {
                for (int i = 0; i < creatures.size(); i++) {
                    System.out.println((i+1) + ". " + creatures.get(i).toString());
                }
            }
            try {
                System.out.print("---------------------------\nВвод: ");
                int c = in.nextInt();
                if (c == 0) {
                    System.out.println("1. Создать игрока\n2. Создать монстра");
                    System.out.print("Ввод: ");
                    c = in.nextInt();
                    switch (c) {
                        case 1: creatures.add(createCreature(new Player())); break;
                        case 2: creatures.add(createCreature(new Monster()));
                    }
                } else {
                    if (creatures.get(c-1) != null) {
                        while (true) {
                            Creature creature = creatures.get(c-1);
                            System.out.println("""
                    
                    
                    
                    
                    ---------------------------------------------------------
                    Вы на поле битвы!!!
                    Ваше существо: """ + creature.toString() + """
                    \nМожете нанести удар противнику или исцелиться
  
                    -1. Сменить существо
                    0. Исцелиться
                    
                    (Для нанесения удара выберите соперника:
                    """);
                            for (int i = 0; i < creatures.size(); i++) {
                                if (creatures.get(i).equals(creature)) {
                                    continue;
                                }
                                System.out.println((i+1) + ". " + creatures.get(i).toString());
                            }
                            System.out.print("Ввод: ");
                            int k = in.nextInt();
                            if (k == -1) {
                                break;
                            }
                            if (k == 0) {
                                creature.doHealing();
                            }
                            else {
                                Creature creature1 = creature.doFight(creatures.get(k-1));
                                Thread.sleep(2000);
                                if (creature1.state == State.DIE) {
                                    creatures.remove(creature1);
                                }
                            }
                        }

                    }
                }
            } catch (IndexOutOfBoundsException | InputMismatchException | InterruptedException ex) {
                System.out.println("---------------------\nНеверный формат ввода!!!\n---------------------");
                in.nextLine();
            }
        }
    }

    public static Creature createCreature(Creature creature) {
        boolean valid = false;
        Player player = null;
        Monster monster = null;
        Scanner in = new Scanner(System.in);
        int attack = 0;
        int protection = 0;
        int health = 0;
        int[] damage1 = {};
        while (!valid) {
            try {
                System.out.print("\n\n\n\n\nАтака(1-30): ");
                attack = in.nextInt();
                if (!Validator.checkAttack(attack)) {
                    System.out.println("---------------------\nНеверный формат ввода!!!\n---------------------");
                    continue;
                }
                System.out.print("Защита(1-30): ");
                protection = in.nextInt();
                if (!Validator.checkProtection(protection)) {
                    System.out.println("---------------------\nНеверный формат ввода!!!\n---------------------");
                    continue;
                }
                System.out.print("Здоровье(0-N): ");
                health = in.nextInt();
                if (!Validator.checkHealth(health)) {
                    continue;
                }
                in.nextLine();
                System.out.print("Урон(Вводить через пробел): ");
                String[] damage = in.nextLine().split(" ");
                damage1 = new int[damage.length];
                for (int i = 0; i < damage.length; i++) {
                    damage1[i] = Integer.parseInt(damage[i]);
                }
                if (!Validator.checkDamage(damage1)) {
                    continue;
                }
            } catch (InputMismatchException | NumberFormatException ex) {
                System.out.println("---------------------\nНеверный формат ввода!!!\n---------------------");
                in.nextLine();
            }
            valid = true;
        }
        if (creature instanceof Player) {
            player = new Player(attack, protection, health, damage1);
            return player;
        }
        if (creature instanceof Monster) {
            monster = new Monster(attack, protection, health, damage1);
            return monster;
        } else return null;
    }
}
