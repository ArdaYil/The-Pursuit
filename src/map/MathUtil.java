package map;

public class MathUtil {
    public static int random(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }
}
