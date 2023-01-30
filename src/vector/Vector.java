package vector;

public class Vector {
    private int x;
    private int y;

    public Vector() {

    }

    public Vector(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "{ X: " + this.getX() + " Y: " + this.getY() + " }";
    }
}
