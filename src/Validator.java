public class Validator{
    public static boolean checkAttack(int attack) {
        return attack >= 1 && attack <= 30;
    }
    public static boolean checkProtection(int protection) {
        return protection >= 1 && protection <= 30;
    }
    public static boolean checkHealth(int health) {
        return health > 0;
    }
    public static boolean checkDamage(int[] damage) {
        for (int i = 0; i != damage.length-1; i++) {
            if (damage[i] < 1) {
                return false;
            }
        }
        return true;
    }
}
