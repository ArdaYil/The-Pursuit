package entity;

/*
    Denna fil är en klass för spelaren. Spelaren är en singleton vilket betyder att det bara finns en global
    instans av denna klass som används av hela applikationen. Denna klass innehåller all vesentlig information
    och funktionaltet för spelaren såsom position, rikting bas position, bilder, osv.
*/

import inventory.Inventory;
import main.Game;
import main.KeyboardInput;
import util.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    private static final int baseX = 123;
    private static final int baseY = 129;

    private BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
    private KeyboardInput keyInput;
    private int baseSpeed = 3;
    private int sprintSpeed = 10;
    private Inventory inventory;

    public Player(Game game, KeyboardInput keyInput) {
        super(game);

        this.inventory = new Inventory();
        this.setDefaultValues();
        this.setPlayerImages();
        this.keyInput = keyInput;
    }

    public void setPlayerImages() {
        try {
            this.up1 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterUp1.png"));
            this.up2 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterUp2.png"));
            this.up3 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterUp3.png"));
            this.down1 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterDown1.png"));
            this.down2 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterDown2.png"));
            this.down3 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterDown3.png"));
            this.left1 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterLeft1.png"));
            this.left2 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterLeft2.png"));
            this.left3 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterLeft3.png"));
            this.right1 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterRight1.png"));
            this.right2 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterRight2.png"));
            this.right3 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterRight3.png"));
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private BufferedImage getImageToDraw() {
        if (this.getDirection() == Direction.UP) {
            if (this.imageCount == 2) return this.up2;
            if (this.imageCount == 3) return this.up3;

            return this.up1;
        }

        if (this.getDirection() == Direction.DOWN) {
            if (this.imageCount == 2) return this.down2;
            if (this.imageCount == 3) return this.down3;

            return this.down1;
        }

        if (this.getDirection() == Direction.LEFT) {
            if (this.imageCount == 2) return this.left2;
            if (this.imageCount == 3) return this.left3;

            return this.left1;
        }

        if (this.imageCount == 2) return this.right2;
        if (this.imageCount == 3) return this.right3;

        return this.right1;
    }

    private void setDefaultValues() {
        this.setX(this.game.tileSize * this.baseX);
        this.setY(this.game.tileSize * this.baseY);
        this.setDirection(Direction.DOWN);
        this.setSpeed(this.baseSpeed);
    }

    public void initializeCollisionBox() {
        this.collisionBox = new Rectangle();
        this.collisionBox.width = this.collisionBoxSize;
        this.collisionBox.height = this.collisionBoxSize;
        this.collisionBox.x = this.screenX;// - collisionBoxSize/2;
        this.collisionBox.y = this.screenY;// - collisionBoxSize/2;
    }

    public void update() {
        if (this.keyInput.upPressed) {
            this.setDirection(Direction.UP);
        }

        else if (this.keyInput.downPressed) {
            this.setDirection(Direction.DOWN);
        }

        else if (this.keyInput.leftPressed) {
            this.setDirection(Direction.LEFT);
        }

        else if (this.keyInput.rightPressed) {
            this.setDirection(Direction.RIGHT);
        }

        this.setSpeed(this.baseSpeed);

        if (this.keyInput.sprintPressed) {
            this.setSpeed(this.sprintSpeed);
        }

        counter++;

        this.isColliding = false;
        this.manageCollision();

        if (keyInput.controlKeyPressed()) {
            if (counter >= 15) {
                counter = 0;
                imageCount++;

                if (imageCount >= 4) {
                    imageCount = 2;
                }
            }

            if (this.isColliding == true) return;

            Direction direction = this.getDirection();

            if (direction == Direction.UP || direction == Direction.DOWN)
                this.walkY();

            else if (direction == Direction.RIGHT || direction == Direction.LEFT)
                this.walkX();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = this.getImageToDraw();

        int tileSize = this.game.tileSize;

        g2.drawImage(image, this.screenX, this.screenY, tileSize, tileSize, null);
    }
}
