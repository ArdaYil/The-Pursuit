package util;

/*
Denna fil är till för en klass för att skapa array objekt. Detta ger ett abrrakt lager över vanliga arrays.
Denna klass är faktiskt inkomplett och har inte all funktionalitet som jag ville att den skulle ha. Men för att kompensera
så har jag en annan klass för länkade listor som har viktig funktionalitet array klassen inte har. Exempelvis så fungerar
generic typeparameters, objekt klassen används inte utan den generiska typ parametern avnänds. Man kan även loopa igenom
den länkade listan.
 */

public class Array<T> {
    private static final int defaultLength = 10;
    private Object[] array;
    public int length;

    public Array() {
        this.array = new Object[defaultLength];
        this.length = 0;
    }

    public Array(int maxCount) {
        this.array = new Object[maxCount];
        this.length = 0;
    }

    public void push(Object element) {
        this.array[length] = element;
        this.length++;
    }

    public Object[] getArray() {
        return this.array;
    }
}
