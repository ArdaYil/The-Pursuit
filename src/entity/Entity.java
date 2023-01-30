package entity;

import java.awt.*;
import main.Game;
import map.Tile;
import vector.Vector2D;

public class Entity {
    private int speed;
    protected final int collisionBoxSize = 20;
    private String direction;
    protected Rectangle collisionBox;
    protected boolean isColliding;
    protected Game game;
    public int screenX;
    public int screenY;
    public Vector2D position;

    public Entity(Game game) {
        this.position = new Vector2D();
        this.game = game;
        this.isColliding = false;
        this.screenX = this.game.screenWidth/2 - this.game.tileSize/2;
        this.screenY = this.game.screenHeight/2 - this.game.tileSize/2;
        this.initializeCollisionBox();
    }

    public int getSpeed() {
        return this.speed;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void walkX() {
        int factor = (this.direction == "left") ? -1 : 1;
        int speed = this.getSpeed() * factor;
        this.position.setX(this.position.getX() + speed);
    }

    public void walkY() {
        int factor = (this.direction == "up") ? -1 : 1;
        int speed = this.getSpeed() * factor;
        this.position.setY(this.position.getY() + speed);
    }

    public void initializeCollisionBox() {

    }

    public void manageCollision() {
        String direction = this.getDirection();

        if (direction == "up") {
            int xLeft = this.position.getX() + 15;
            int xRight = this.position.getX() + (this.game.tileSize/2);
            int yTop = this.position.getY() - (this.game.tileSize/2 - 30);

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
            int xLeft = this.position.getX() + 15;
            int xRight = this.position.getX() + (this.game.tileSize/2);
            int yBottom = this.position.getY() + (this.game.tileSize + 5);

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
            int xLeft = this.position.getX();
            int yTop = this.position.getY() - (this.game.tileSize/2 - 35);;
            int yBottom = this.position.getY() + (this.game.tileSize);

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
            int xRight = this.position.getX() + (this.game.tileSize/2 + 10);
            int yTop = this.position.getY() - (this.game.tileSize/2 - 35);;
            int yBottom = this.position.getY() + (this.game.tileSize);

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
