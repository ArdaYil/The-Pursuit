package util;

/*
Denna fil är en enum eller enumeration för riktning. Anledningen till varför jag inte bara har en string för att
jobba med riktningar är för att stringar kan anta i princip alla värden medans denna enumeration endast kan andta dessa
fyra värden. Man kan alltås se till att riktningen för spelaren exempelvis endast är ett av dessa värden och inget annat.
Slutligen är det java standard att använda enumerations vid sådana här tillfällen.
 */

public enum Direction {
    UP,
    LEFT,
    RIGHT,
    DOWN
}
