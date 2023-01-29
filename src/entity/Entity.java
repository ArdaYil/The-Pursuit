package entity;

import java.awt.*;
import main.Game;
import map.Tile;

public class Entity {
    private int x;
    private int y;
    private int speed;
    protected final int collisionBoxSize = 20;
    private String direction;
    protected Rectangle collisionBox;
    protected boolean isColliding;
    protected Game game;
    public int screenX;
    public int screenY;

    public Entity(Game game) {
        this.game = game;
        this.isColliding = false;
        this.screenX = this.game.screenWidth/2 - this.game.tileSize/2;
        this.screenY = this.game.screenHeight/2 - this.game.tileSize/2;
        this.initializeCollisionBox();
    }

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
        int factor = (this.direction == "left") ? -1 : 1;
        int speed = this.getSpeed() * factor;
        this.setX(this.getX() + speed);
    }

    public void walkY() {
        int factor = (this.direction == "up") ? -1 : 1;
        int speed = this.getSpeed() * factor;
        this.setY(this.getY() + speed);
    }

    public void initializeCollisionBox() {

    }

    public void manageCollision() {
        String direction = this.getDirection();

        if (direction == "up") {
            int xLeft = this.getX() + 15;
            int xRight = this.getX() + (this.game.tileSize/2);
            int yTop = this.getY() - (this.game.tileSize/2 - 30);

            int colLeft = xLeft / this.game.tileSize;
            int colRight = xRight / this.game.tileSize;
            int rowTop = yTop / this.game.tileSize;

            int tileLeftNum = this.game.map.map[colLeft][rowTop];
            int tileRightNum = this.game.map.map[colRight][rowTop];

            Tile tileLeft = this.game.map.tiles[tileLeftNum];
            Tile tileRight = this.game.map.tiles[tileRightNum];

            if (tileLeft.canCollide == true || tileRight.canCollide == true) {
                this.isColliding = true;
            }

            return;
        }

        if (direction == "down") {
            int xLeft = this.getX() + 15;
            int xRight = this.getX() + (this.game.tileSize/2);
            int yBottom = this.getY() + (this.game.tileSize + 5);

            int colLeft = xLeft / this.game.tileSize;
            int colRight = xRight / this.game.tileSize;
            int rowTop = yBottom / this.game.tileSize;

            int tileLeftNum = this.game.map.map[colLeft][rowTop];
            int tileRightNum = this.game.map.map[colRight][rowTop];

            Tile tileLeft = this.game.map.tiles[tileLeftNum];
            Tile tileRight = this.game.map.tiles[tileRightNum];

            if (tileLeft.canCollide == true || tileRight.canCollide == true) {
                this.isColliding = true;
            }
        }

        if (direction == "left") {
            int xLeft = this.getX();
            int yTop = this.getY() - (this.game.tileSize/2 - 35);;
            int yBottom = this.getY() + (this.game.tileSize);

            int colLeft = xLeft / this.game.tileSize;
            int rowTop = yTop / this.game.tileSize;
            int rowBottom = yBottom / this.game.tileSize;

            int tileTopNum = this.game.map.map[colLeft][rowTop];
            int tileBottomNum = this.game.map.map[colLeft][rowBottom];

            Tile tileTop = this.game.map.tiles[tileTopNum];
            Tile tileBottom = this.game.map.tiles[tileBottomNum];

            if (tileTop.canCollide == true || tileBottom.canCollide == true) {
                this.isColliding = true;
            }
        }
        if (direction == "right") {
            int xRight = this.getX() + (this.game.tileSize/2 + 10);
            int yTop = this.getY() - (this.game.tileSize/2 - 35);;
            int yBottom = this.getY() + (this.game.tileSize);

            int colRight = xRight / this.game.tileSize;
            int rowTop = yTop / this.game.tileSize;
            int rowBottom = yBottom / this.game.tileSize;

            int tileTopNum = this.game.map.map[colRight][rowTop];
            int tileBottomNum = this.game.map.map[colRight][rowBottom];

            Tile tileTop = this.game.map.tiles[tileTopNum];
            Tile tileBottom = this.game.map.tiles[tileBottomNum];

            if (tileTop.canCollide == true || tileBottom.canCollide == true) {
                this.isColliding = true;
            }
        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
