package vector;

/*
Denna fil är en superklass för vector objekt. Ett vector objekt används för att ange data i form av hastighet, rotation positon osv
 */

public class Vector {
    private int x;
    private int y;

    public Vector() {

    }

    public Vector(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public void multiply(int factor) {
        this.x = this.x * factor;
        this.y = this.y * factor;
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
    public boolean equals(Object other) {
        if (other == this) return true;

        if (!(other instanceof Vector)) return false;

        Vector newOther = (Vector)other;

        return this.x == newOther.x && this.y == newOther.y;
    }

    @Override
    public String toString() {
        return "Vector: { X: " + this.getX() + " Y: " + this.getY() + " }";
    }
}
