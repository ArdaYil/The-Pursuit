package entity;

import main.Game;
import vector.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MartMerchant extends Entity {
    private BufferedImage image;

    public MartMerchant(Game game) {
        super(game);

        this.initializeImage();
    }

    private void initializeImage() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/staticEntities/MartMerchant.png"));
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int tileSize = this.game.tileSize;

        Vector2D drawPosition = this.game.map.getDrawingPosition(this.position, this.game.player);
        System.out.println(drawPosition.toString());
        g2.drawImage(this.image, drawPosition.getX(), drawPosition.getY(), tileSize, tileSize, null);
    }
}