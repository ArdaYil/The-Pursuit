package util;

public class Array{
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
