package entity;

import main.Game;
import main.KeyboardInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    private BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
    private Game game;
    private KeyboardInput keyInput;
    private int screenX;
    private int screenY;
    private int imageCount = 1;
    private int counter = 0;

    public Player(KeyboardInput keyInput) {
        this.setDefaultValues();
        this.setPlayerImages();
        this.keyInput = keyInput;

        this.screenX = this.game.screenWidth/2 - this.game.tileSize/2;
        this.screenY = this.game.screenHeight/2 - this.game.tileSize/2;
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
        if (this.getDirection() == "up") {
            if (this.imageCount == 2) return this.up2;
            if (this.imageCount == 3) return this.up3;

            return this.up1;
        }

        if (this.getDirection() == "down") {
            if (this.imageCount == 2) return this.down2;
            if (this.imageCount == 3) return this.down3;

            return this.down1;
        }

        if (this.getDirection() == "left") {
            if (this.imageCount == 2) return this.left2;
            if (this.imageCount == 3) return this.left3;

            return this.left1;
        }

        if (this.imageCount == 2) return this.right2;
        if (this.imageCount == 3) return this.right3;

        return this.right1;
    }

    private void setDefaultValues() {
        this.setX(this.game.tileSize * 10);
        this.setY(this.game.tileSize * 10);
        this.setDirection("up");
        this.setSpeed(3);
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

    public void update() {
        if (this.keyInput.upPressed) {
            this.setDirection("up");
            this.walkY();
        }

        else if (this.keyInput.downPressed) {
            this.setDirection("down");
            this.walkY();
        }

        else if (this.keyInput.leftPressed) {
            this.setDirection("left");
            this.walkX();
        }

        else if (this.keyInput.rightPressed) {
            this.setDirection("right");
            this.walkX();
        }

        counter++;

        if (!keyInput.controlKeyPressed()) return;

        if (counter >= 15) {
            counter = 0;
            imageCount++;

            if (imageCount >= 4) {
                imageCount = 1;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = this.getImageToDraw();

        int tileSize = this.game.tileSize;

        g2.drawImage(image, this.screenX, this.screenY, tileSize, tileSize, null);
    }
}
