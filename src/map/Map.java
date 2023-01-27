package map;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import main.Game;

public class Map {
    private Tile[] tiles = new Tile[10];
    private int[][] map;
    private Game game;

    public Map(Game game) {
        this.game = game;
        this.initializeTiles();
        this.map = new int[this.game.worldCols][this.game.worldRows];
        this.initializeMap("/maps/Text.txt");
    }

    public void initializeMap(String path) {

    }

    public void initializeTiles() {
        try {
            this.tiles[0] = new Tile();
            this.tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GrassTile.png"));
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int tileSize = this.game.tileSize;

        g2.drawImage(this.tiles[0].image, 0,0, tileSize, tileSize, null);
    }
}
