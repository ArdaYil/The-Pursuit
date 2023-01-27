package entity;

import main.Game;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
    Game game;

    public Player() {
        this.setDefaultValues();
        this.setPlayerImages();
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
            this.down1 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterRight1.png"));
            this.down2 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterRight2.png"));
            this.down3 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterRight3.png"));
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = this.up1;

        int tileSize = this.game.tileSize;
        int drawX = this.game.screenWidth/2 - tileSize/2;
        int drawY = this.game.screenHeight/2 - tileSize/2;

        g2.drawImage(image, drawX, drawY, tileSize, tileSize, null);
    }

    private void setDefaultValues() {
        this.setX(100);
        this.setY(100);
        this.setSpeed(3);
    }
}
