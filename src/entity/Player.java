package entity;

import main.Game;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
    Game game;

    public Player() {
        this.setDefaultValues();
    }

    public void setPlayerImages() {
        try {
            this.up1 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterUp1"));
            this.up2 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterUp2"));
            this.up3 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterUp3"));
            this.down1 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterDown1"));
            this.down2 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterDown2"));
            this.down3 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterDown3"));
            this.left1 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterLeft1"));
            this.left2 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterLeft2"));
            this.left3 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterLeft3"));
            this.down1 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterRight1"));
            this.down2 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterRight2"));
            this.down3 = ImageIO.read(getClass().getResourceAsStream("/player/CharacterRight3"));
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //g2.drawImage();
    }

    private void setDefaultValues() {
        this.setX(100);
        this.setY(100);
        this.setSpeed(3);
    }
}
