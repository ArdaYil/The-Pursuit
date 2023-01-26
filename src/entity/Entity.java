package entity;

public class Entity {
    private int x;
    private int y;
    private int speed;
    private String direction;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSpeed() {
        return this.speed;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void walkX() {
        this.setX(this.getX() + this.getSpeed());
    }

    public void walkY() {
        this.setY(this.getY() + this.getSpeed());
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
