package map;

/*
    Denna fil är en klass som inehåller statiska metoder för matematiska beräkningar.
 */

public class MathUtil {

    // Metod för slumptal mellan intervall.

    public static int random(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }
}
